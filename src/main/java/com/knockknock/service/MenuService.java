package com.knockknock.service;

import com.knockknock.entity.Menu;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;

/**
 * @CreateTime: 2022-05-13 23:52
 * @Description:
 */
public interface MenuService {

    /**
     * 增
     * @param menu
     * @return
     */
    Result insert(Menu menu);

    /**
     *删
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 改
     * @param menu
     * @return
     */
    Result update(Menu menu);

    /**
     * 分页查询
     * @param queryInfo 页码 页面大小 查询内容
     * @return
     */
    Result findPage(QueryInfo queryInfo);

    /**
     * 查询父级菜单
     * @return
     */
    Result findParent();
}
