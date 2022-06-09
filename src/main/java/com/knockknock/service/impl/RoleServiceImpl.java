package com.knockknock.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.knockknock.entity.Menu;
import com.knockknock.entity.Permission;
import com.knockknock.entity.Role;
import com.knockknock.mapper.RoleMapper;
import com.knockknock.service.RoleService;
import com.knockknock.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateTime: 2022-05-17 17:05
 * @Description:
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Transactional
    @Override
    public Result insert(Role role) {
        Role existence = roleMapper.findByLabel(role.getLabel());
        if(null != existence) {
            return Result.fail("该角色已存在！");
        }
        roleMapper.insert(role);
        if(role.getPermissions().size() > 0) {
//            添加权限信息
            role.getPermissions().forEach(item -> roleMapper.insertPermission(role.getId(), item.getId()));
        }
        if(role.getMenus().size() > 0) {
//            添加菜单信息
            role.getMenus().forEach(item -> {
                roleMapper.insertMenu(role.getId(), item.getId());
            });
        }
        redisUtils.delKey("userInfo_", SecurityUtils.getUsername());
        return Result.success("添加角色成功！");
    }

    @Override
    public Result delete(Long id) {
        List<Menu> parent = roleMapper.findParentMenuById(id);
        List<Menu> children = new ArrayList<>();
        parent.forEach(item -> children.addAll(roleMapper.findChildrenMenu(item.getId(), id)));
        if(parent.size() > 0 || children.size() > 0){
            return Result.fail("删除失败，请先删除角色对应下的菜单！");
        }
        if(roleMapper.findPermissionById(id).size() > 0){
            return Result.fail("删除失败，请先删除角色对应下的权限！");
        }
        roleMapper.delete(id);
        redisUtils.delKey("userInfo_", SecurityUtils.getUsername());
        return Result.success("删除成功");
    }

    @Transactional
    @Override
    public Result update(Role role) {
        roleMapper.update(role);
        if(role.getPermissions().size() > 0) {
            roleMapper.deletePermissionById(role.getId());
            role.getPermissions().forEach(item -> roleMapper.insertPermission(role.getId(), item.getId()));
        }
        if(role.getMenus().size() > 0) {
            roleMapper.deleteMenuById(role.getId());
            role.getMenus().forEach(item -> {
                roleMapper.insertMenu(role.getId(), item.getId());
                item.getChildren().forEach(children -> roleMapper.insertMenu(role.getId(), children.getId()));
            });
        }
        redisUtils.delKey("userInfo_", SecurityUtils.getUsername());
        return Result.success("修改角色成功！");
    }

    @Override
    public Result findPage(QueryInfo queryInfo) {
        PageHelper.startPage(queryInfo.getPageNumber(),queryInfo.getPageSize());
        Page<Role> page = roleMapper.findPage(queryInfo.getQueryString());
        long total = page.getTotal();
        List<Role> result = page.getResult();
        result.forEach(item -> {
            List<Menu> parentMenu = roleMapper.findParentMenuById(item.getId());
            parentMenu.forEach(parent -> {
                List<Menu> childrenMenu = roleMapper.findChildrenMenu(parent.getId(), item.getId());
                parent.setChildren(childrenMenu);
            });
            item.setMenus(parentMenu);
            List<Permission> permission = roleMapper.findPermissionById(item.getId());
            item.setPermissions(permission);
        });
        return PageResult.pageResult(total, result);
    }
}
