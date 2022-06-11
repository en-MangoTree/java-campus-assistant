package com.knockknock.controller;

import com.knockknock.service.PersonalService;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateTime: 2022-06-11 16:38
 * @Description:
 */
@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @PostMapping("/mytrade")
    public Result findPersonalTrade(@RequestBody QueryInfo queryInfo) {
        return personalService.findPersonalTrade(queryInfo);
    }

    @PostMapping("/mycollection")
    public Result findCollection(@RequestBody QueryInfo queryInfo) {
        return personalService.findCollection(queryInfo);
    }
}
