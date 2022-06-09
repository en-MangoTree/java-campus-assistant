package com.knockknock.service;

import com.knockknock.entity.Permission;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;

/**
 * @CreateTime: 2022-05-13 23:52
 * @Description:
 */
public interface PermissionService {

    /**
     * 增
     * @param permission
     * @return
     */
    Result insert(Permission permission);

    /**
     *删
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 改
     * @param permission
     * @return
     */
    Result update(Permission permission);

    /**
     * 分页查询
     * @param queryInfo 页码 页面大小 查询内容
     * @return
     */
    Result findPage(QueryInfo queryInfo);

    /**
     * 查询所有权限
     * @return
     */
    Result findAll();
}
