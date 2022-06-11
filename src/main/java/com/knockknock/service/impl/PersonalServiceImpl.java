package com.knockknock.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.knockknock.entity.Trade;
import com.knockknock.entity.User;
import com.knockknock.mapper.PersonalMapper;
import com.knockknock.service.PersonalService;
import com.knockknock.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime: 2022-06-11 16:21
 * @Description:
 */
@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private PersonalMapper personalMapper;

    User user = new User();

    @Override
    public Result findPersonalTrade(QueryInfo queryInfo) {
        //获取当前登陆用户信息
        user = SecurityUtils.getUser();
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<Trade> page = personalMapper.findPersonalTrade(user.getId());
        long total = page.getTotal();
        List<Trade> result = page.getResult();
        return PageResult.pageResult(total, result);
    }

    @Override
    public Result findCollection(QueryInfo queryInfo) {
        user = SecurityUtils.getUser();
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<Trade> page = personalMapper.findCollection(user.getId());
        long total = page.getTotal();
        List<Trade> result = page.getResult();
        return PageResult.pageResult(total, result);
    }
}
