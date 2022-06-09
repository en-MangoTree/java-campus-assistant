package com.knockknock.controller;

import com.knockknock.entity.Permission;
import com.knockknock.service.PermissionService;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @CreateTime: 2022-05-14 00:10
 * @Description:
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Permission permission){
        return permissionService.insert(permission);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryInfo queryInfo){
        return permissionService.findPage(queryInfo);
    }

    @GetMapping("/findAll")
    public Result findAll(){
        return permissionService.findAll();
    }

}
