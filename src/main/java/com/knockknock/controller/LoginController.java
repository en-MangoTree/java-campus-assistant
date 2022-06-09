package com.knockknock.controller;

import com.knockknock.service.UserService;
import com.knockknock.utils.RedisUtils;
import com.knockknock.utils.Result;
import com.knockknock.utils.SecurityUtils;
import com.knockknock.vo.LoginVo;
import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @CreateTime: 2022-05-25 16:29
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        return userService.login(loginVo);
    }

    @GetMapping("/getInfo")
    public Result getUserInfo(Principal principal){
        if(null == principal){
            return Result.fail("请登录！");
        }
        return Result.success("获取用户成功", SecurityUtils.getUser());
    }

    @GetMapping("/logout")
    public Result logout(){
//        清除缓存
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return Result.success("退出成功");
    }

}
