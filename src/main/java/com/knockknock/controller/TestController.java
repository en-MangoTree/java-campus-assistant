package com.knockknock.controller;

import com.knockknock.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateTime: 2022-05-06 23:58
 * @Description:
 */
@RestController
public class TestController {

    @PreAuthorize("hasAuthority('USER_INSERT')")
    @RequestMapping("/test")
    public Result test(){
        return Result.success("信息返回成功", "Hello,world！");
    }
}
