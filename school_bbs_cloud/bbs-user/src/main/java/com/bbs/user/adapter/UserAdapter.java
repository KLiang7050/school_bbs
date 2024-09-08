package com.bbs.user.adapter;

import com.bbs.common.core.domain.R;
import com.bbs.common.core.exception.BizException;
import com.bbs.user.domain.LoginBody;
import com.bbs.user.domain.RegisterBody;
import com.bbs.user.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserAdapter extends UserServiceImpl implements UserTarget {

    @Value("${gitee.callBack}")
    private String callBack;
    @Value("${gitee.client.id}")
    private String giteeClientId;
    @Value("${gitee.client.secret}")
    private String giteeSecret;
    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.url.tokenPath}")
    private String giteeTokenPath;
    @Value("${gitee.url.userPath}")
    private String giteeUserPath;
    @Value("${gitee.url.host}")
    private String giteeHost;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    @Override
    public R<HashMap<String, Object>> giteeLogin(String code, String state) {
        if (!giteeState.equals(state)) {
            throw new UnsupportedOperationException("Invalid state!");
        }

        try {
            Map<String, String> tokenQuerys = new HashMap<String, String>();
            tokenQuerys.put("grant_type", "authorization_code");
            tokenQuerys.put("code", code);
            tokenQuerys.put("client_id", giteeClientId);
            tokenQuerys.put("redirect_uri", callBack);
            tokenQuerys.put("client_secret", giteeSecret);
            HttpClient client = HttpClients.createDefault();
            String url = "https://gitee.com/oauth/token?grant_type=authorization_code&client_id=5e4d3c1414615b7a65a7d493baf17b44cafc46b6ff4b96d7c595c6defa2d3b4e&client_secret=c567ced4949d8a640fe9fbb250ae11832695e5d6fa226b2cdc06441bef9f1e9c&redirect_uri=http://localhost:9880/user/3rd/gitee&code=".concat(code);
            HttpPost http = new HttpPost(url);
            HttpResponse tokenHttpRes = client.execute(http);
            // HttpResponse tokenHttpRes = HttpUtils.doPost(giteeHost, giteeTokenPath, null, new HashMap<String, String>(), tokenQuerys, new HashMap<String, String>());
            String tokenHTTPJSON = EntityUtils.toString(tokenHttpRes.getEntity());
            ObjectMapper mapper = new ObjectMapper();
            if (tokenHttpRes.getStatusLine().getStatusCode() != 200)
                throw new RuntimeException("get token error:" + tokenHttpRes.getStatusLine().getStatusCode());
            Map tokenRes = mapper.readValue(tokenHTTPJSON, Map.class);
            String token = String.valueOf(tokenRes.get("access_token"));

            Map<String, String> userQuerys = new HashMap<String, String>();
            HttpClient client2 = HttpClients.createDefault();
            String url2 = "https://gitee.com/api/v5/user?access_token=".concat(token);
            HttpGet http2 = new HttpGet(url2);
            HttpResponse userHttpRes = client2.execute(http2);
            // HttpResponse userHttpRes = HttpUtils.doGet(giteeHost, giteeUserPath, null, new HashMap<String, String>(), userQuerys);
            userQuerys.put("access_token", token);
            if (userHttpRes.getStatusLine().getStatusCode() != 200)
                throw new RuntimeException("get user info error:" + userHttpRes.getStatusLine().getStatusCode());
            HttpEntity userEntity = userHttpRes.getEntity();
            Map userRes = mapper.readValue(EntityUtils.toString(userEntity), Map.class);
            String userName = giteeUserPrefix.concat(String.valueOf(userRes.get("name")));
            return R.ok(autoRegister3rdAndLogin(userName));
        } catch (Exception e) {
            throw new RuntimeException("gitee login error——" + e);
        }
    }

    private HashMap<String, Object> autoRegister3rdAndLogin(String userName) throws BizException {
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername(userName);
        loginBody.setPassword(userName);
        if (!super.checkUserExists(userName)) {
            return super.login(loginBody);
        }

        RegisterBody registerBody = new RegisterBody();
        registerBody.setPassword(userName);
        registerBody.setUsername(userName);
        super.register(registerBody);
        return super.login(loginBody);
    }
}
