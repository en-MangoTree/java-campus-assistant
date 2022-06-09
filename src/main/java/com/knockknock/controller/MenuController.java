package com.knockknock.controller;

import com.knockknock.entity.Menu;
import com.knockknock.service.MenuService;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @CreateTime: 2022-05-14 00:10
 * @Description:
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Menu menu){
        return menuService.insert(menu);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return menuService.delete(id);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Menu menu){
        return menuService.update(menu);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryInfo queryInfo){
        return menuService.findPage(queryInfo);
    }

    @GetMapping("/findParent")
    public Result findParent(){
        return menuService.findParent();
    }

}
