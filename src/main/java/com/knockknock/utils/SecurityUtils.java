package com.knockknock.utils;

import com.knockknock.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @CreateTime: 2022-05-09 14:46
 * @Description: 获取当前登陆用户基本信息
 */
public class SecurityUtils {

    /**
     * 中security主体信息获取用户信息
     * @return
     */
    public static User getUser(){
        User user =  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        防止密码返回至前端泄漏
        user.setPassword(null);
//        用户名登陆时获取忽略返回，创建一个新的字段并把用户名赋值于它返回
        user.setLoginName(user.getUsername());
        return user;
    }

    public static String getUsername(){
        return getUser().getUsername();
    }

    public static Long getUserId(){
        return getUser().getId();
    }
}
