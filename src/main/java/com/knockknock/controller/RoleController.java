package com.knockknock.controller;

import com.knockknock.entity.Role;
import com.knockknock.service.RoleService;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @CreateTime: 2022-05-14 00:10
 * @Description:
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Role role){
        return roleService.insert(role);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return roleService.delete(id);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Role role){
        return roleService.update(role);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryInfo queryInfo){
        return roleService.findPage(queryInfo);
    }

}
