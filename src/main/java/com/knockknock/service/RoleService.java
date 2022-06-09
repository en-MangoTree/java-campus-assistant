package com.knockknock.service;

import com.knockknock.entity.Role;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;

/**
 * @CreateTime: 2022-05-13 23:52
 * @Description:
 */
public interface RoleService {

    /**
     * 增
     * @param role
     * @return
     */
    Result insert(Role role);

    /**
     *删
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 改
     * @param role
     * @return
     */
    Result update(Role role);

    /**
     * 分页查询
     * @param queryInfo 页码 页面大小 查询内容
     * @return
     */
    Result findPage(QueryInfo queryInfo);
}
