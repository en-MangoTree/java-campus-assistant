package com.knockknock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @CreateTime: 2022-05-25 13:58
 * @Description: 跨域请求配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//允许访问路径
                .allowedOrigins("http://localhost:8801", "null") //请求来源
                .allowedMethods("POST", "GET", "PUT", "DELETE")//允许跨域访问的方法
                .maxAge(3600)//最大响应时间
                .allowCredentials(true);//允许携带参数
    }
}
