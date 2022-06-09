package com.knockknock.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @CreateTime: 2022-05-25 14:21
 * @Description: token工具类
 * 作用：生成通token，用户获取token访问资源
 */
@Component
public class TokenUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    /**
     * 传入用户信息，生成token
     * @param details
     * @return 返回生成的token
     */
    public String generateToken(UserDetails details){
        Map<String,Object> map = new HashMap<>(2);
        map.put("username", details.getUsername());
        map.put("create", new Date());
        return this.generateJwt(map);
    }

    /**
     * 根据荷载信息去生成token
     * @param map
     * @return
     */
    public String generateJwt(Map<String,Object> map){
        return Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512,secret)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * 根据token获取荷载信息
     * @param token
     * @return
     */
    public Claims getTokenBody(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 根据token获取用户名
     * @param token
     * @return
     */
    public String getUsernameByToken(String token){
        return (String) this.getTokenBody(token).get("username");
    }

    /**
     * 根据token判断当前时间内，token是否过期
     * @param token
     * @return
     */
    public boolean isExpiration(String token){
        return this.getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * 刷新token令牌
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = this.getTokenBody(token);
        claims.setExpiration(new Date());
        return this.generateJwt(claims);
    }

}
