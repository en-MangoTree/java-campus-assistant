package com.knockknock.controller;

import com.knockknock.entity.Sort;
import com.knockknock.service.SortService;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @CreateTime: 2022-06-09 22:06
 * @Description:
 */
@RestController
@RequestMapping("/sort")
public class SortController {

    @Autowired
    private SortService sortService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Sort sort){
        return sortService.insert(sort);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return sortService.delete(id);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Sort sort){
        return sortService.update(sort);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryInfo queryInfo){
        return sortService.findPage(queryInfo);
    }

}
