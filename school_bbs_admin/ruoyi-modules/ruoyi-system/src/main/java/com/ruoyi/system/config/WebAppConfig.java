package com.ruoyi.system.config;
 
import org.springframework.context.annotation.Configuration;
 
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
 
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
 
    @Resource
    private MyInterceptor interceptor;
 
    /**
     * 添加拦截规则
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
 
        List<String> patterns = new ArrayList<>();

        registry.addInterceptor(interceptor)
                .addPathPatterns("/**") //所有的请求都要拦截。
                .excludePathPatterns(patterns); //将不需要拦截的接口请求排除在外
    }
}