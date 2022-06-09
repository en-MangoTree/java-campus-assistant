package com.knockknock.config.security.handler;

import com.knockknock.config.security.service.UserDetailsServiceImpl;
import com.knockknock.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @CreateTime: 2022-05-08 17:04
 * @Description: token认证，接口访问前过滤
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 请求前获取请求信息
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

//        获取token
        String header = request.getHeader(tokenHeader);
//        判断token是否存在
        if(null != header && header.startsWith(tokenHead)){
//            拿到token主体
            String token = header.substring(tokenHead.length());
//            根据token获取用户名
            String username = tokenUtils.getUsernameByToken(token);
//            token存在，但没有登陆信息
            if(null != username && null == SecurityContextHolder.getContext().getAuthentication()){
//                没有登陆信息，直接登陆
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                判断token是否有效
                if(!tokenUtils.isExpiration(token) && username.equals(userDetails.getUsername())){
//                    刷新security中的用户信息
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
//        过滤器放行
        chain.doFilter(request,response);

    }
}
