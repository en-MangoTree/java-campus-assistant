package com.knockknock.mapper;

import com.github.pagehelper.Page;
import com.knockknock.entity.Permission;

import java.util.List;

/**
 * @CreateTime: 2022-05-13 23:28
 * @Description: 权限数据
 */
public interface PermissionMapper {

    /**
     * 增
     * @param permission
     */
    void insert(Permission permission);

    /**
     * 删
     * @param id
     */
    void delete(Long id);

    /**
     * 改
     * @param permission
     */
    void update(Permission permission);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<Permission> findPage(String queryString);

    /**
     * 查询所有权限
     * @return
     */
    List<Permission> findAll();
}
