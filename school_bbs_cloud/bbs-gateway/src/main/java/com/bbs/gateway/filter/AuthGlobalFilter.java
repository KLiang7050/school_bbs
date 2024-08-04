package com.bbs.gateway.filter;

import com.alibaba.fastjson2.JSON;
import com.bbs.common.core.constant.TokenConstants;
import com.bbs.common.core.domain.R;
import com.bbs.common.core.utils.JwtUtils;
import com.bbs.common.redis.CacheConstants.UserCacheKey;
import com.bbs.gateway.config.IgnoreWhiteProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AuthGlobalFilter implements GlobalFilter {

    @Autowired
    IgnoreWhiteProperties ignoreWhite;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest();

        String url = req.getURI().getPath();

        // 校验白名单
        for (String whiteURL : ignoreWhite.getWhites()) {
            if (url.contains(whiteURL)) {
                return chain.filter(exchange);
            }
        }

        String token = getToken(req);
        if (StringUtils.isEmpty(token)) {
            return unauthorizedResponse(exchange, "未携带令牌");
        }
        Claims claims = null;
        try {
            claims = JwtUtils.parseToken(token);
            if (claims == null) {
                throw new MalformedJwtException("");
            }
        } catch (MalformedJwtException e) {
            return unauthorizedResponse(exchange, "令牌已过期或验证不正确");
        }
        String userkey = JwtUtils.getUserKey(claims);
        String key = UserCacheKey.userInfoKey(userkey);
        boolean islogin = redisTemplate.hasKey(key);
        if (!islogin) {
            return unauthorizedResponse(exchange, "登录状态已过期");
        }
        String userid = JwtUtils.getUserId(claims);
        String username = JwtUtils.getUserName(claims);
        if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username)) {
            return unauthorizedResponse(exchange, "令牌验证失败");
        }

        return chain.filter(exchange);
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json");
        DataBuffer dataBuffer = response.bufferFactory().wrap(
                JSON.toJSONBytes(R.fail(HttpStatus.UNAUTHORIZED.value(), msg)));
        return response.writeWith(Mono.just(dataBuffer));
    }

    private String getToken(ServerHttpRequest req) {
        String token = req.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
        if (!StringUtils.isEmpty(token) && token.startsWith(TokenConstants.PREFIX)) {
            token = token.replaceFirst(TokenConstants.PREFIX, "");
        }
        return token;
    }
}
