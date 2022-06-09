package com.knockknock.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.knockknock.entity.Permission;
import com.knockknock.mapper.PermissionMapper;
import com.knockknock.service.PermissionService;
import com.knockknock.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime: 2022-05-13 23:55
 * @Description:
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Result insert(Permission permission) {
        permissionMapper.insert(permission);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("添加权限数据成功！");
    }

    @Override
    public Result delete(Long id) {
        permissionMapper.delete(id);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("删除权限数据成功！");
    }

    @Override
    public Result update(Permission permission) {
        permissionMapper.update(permission);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("修改权限数据成功！");
    }

    @Override
    public Result findPage(QueryInfo queryInfo) {
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<Permission> page = permissionMapper.findPage(queryInfo.getQueryString());
        long total = page.getTotal();
        List<Permission> result = page.getResult();
        return PageResult.pageResult(total, result);
    }

    @Override
    public Result findAll() {
        return Result.success("查询所有权限成功", permissionMapper.findAll());
    }
}
