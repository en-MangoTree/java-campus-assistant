package com.knockknock.mapper;


import com.github.pagehelper.Page;
import com.knockknock.entity.Menu;

import java.util.List;

/**
 * @CreateTime: 2022-05-13 23:28
 * @Description: 权限数据
 */
public interface MenuMapper {

    /**
     * 增
     * @param menu
     */
    void insert(Menu menu);

    /**
     * 删
     * @param id
     */
    void delete(Long id);

    /**
     * 改
     * @param menu
     */
    void update(Menu menu);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<Menu> findPage(String queryString);

    /**
     * 查询父级菜单
     * @return
     */
    List<Menu> findParent();

}
