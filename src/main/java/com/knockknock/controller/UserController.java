package com.knockknock.controller;

import com.knockknock.entity.User;
import com.knockknock.service.UserService;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @CreateTime: 2022-05-14 00:10
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/insert")
    public Result insert(@RequestBody User user){
        return userService.insert(user);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user){
        return userService.update(user);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryInfo queryInfo){
        return userService.findPage(queryInfo);
    }

}
