package com.knockknock.config.security;

import com.knockknock.config.security.contents.SecurityContents;
import com.knockknock.config.security.handler.JwtAccessDeniedHandler;
import com.knockknock.config.security.handler.JwtAuthenticationEntryPoint;
import com.knockknock.config.security.handler.JwtAuthenticationFilter;
import com.knockknock.config.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @CreateTime: 2022-05-25 13:56
 * @Description: SpringSecurity配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    /**
     * 配置白名单（资源无权限依然可以访问）
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers(SecurityContents.WHITE_LIST);
    }

    /**
     * security核心配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        使用jwt，关闭跨域攻击
        http.csrf().disable();
//        关闭session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        除白名单外资源，请求需要进行认证之后才能访问
        http.authorizeRequests().anyRequest().authenticated();
//        关闭缓存
        http.headers().cacheControl();
//        token过滤器，校验token
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        没有登陆、没有权限访问资源自定义返回结果
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);

    }

    /**
     * 自定义登陆逻辑配置（配置到security中进行认证）
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
