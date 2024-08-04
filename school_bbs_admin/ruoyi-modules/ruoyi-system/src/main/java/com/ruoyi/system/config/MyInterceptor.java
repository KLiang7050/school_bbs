package com.ruoyi.system.config;

import com.ruoyi.common.core.constant.TokenConstants;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

@Component
public class MyInterceptor implements HandlerInterceptor {

    private static final Pattern APP_PATTERN = Pattern.compile("/app");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取token
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX)) {
            token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
        }

        String datasource = DynamicDataSourceProvider.DEFAULT_DATASOURCE;
        if (token != null && !token.isEmpty()) {

            // 判断请求URL是否包含 "/app"
            String requestUri = request.getRequestURI();
            if (APP_PATTERN.matcher(requestUri).find()) {
                datasource = JwtUtils.getUserDatasource(token);
            }
        }
        // 初始化ThreadLocal，以防万一
        DynamicDataSourceContextHolder.clearDataSourceType();
        // 在ThreadLocal中存储自己线程的数据
        DynamicDataSourceContextHolder.setDataSourceType(datasource);


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        DynamicDataSourceContextHolder.clearDataSourceType();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
