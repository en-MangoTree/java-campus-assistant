package com.knockknock.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.knockknock.entity.Menu;
import com.knockknock.mapper.MenuMapper;
import com.knockknock.service.MenuService;
import com.knockknock.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime: 2022-05-16 20:43
 * @Description:
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Result insert(Menu menu) {
        menuMapper.insert(menu);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("添加菜单数据成功！");
    }

    @Override
    public Result delete(Long id) {
        menuMapper.delete(id);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("删除菜单数据成功！");
    }

    @Override
    public Result update(Menu menu) {
        menuMapper.update(menu);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("修改查单数据成功！");
    }

    @Override
    public Result findPage(QueryInfo queryInfo) {
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<Menu> page = menuMapper.findPage(queryInfo.getQueryString());
        long total = page.getTotal();
        List<Menu> result = page.getResult();
        return new PageResult(total, result);
    }

    @Override
    public Result findParent() {
        return Result.success("查询父级菜单成功", menuMapper.findParent());
    }
}
