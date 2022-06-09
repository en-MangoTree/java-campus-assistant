package com.knockknock.mapper;

import com.github.pagehelper.Page;
import com.knockknock.entity.Sort;

/**
 * @CreateTime: 2022-06-09 21:05
 * @Description:
 */
public interface SortMapper {

    /**
     * 增
     * @param sort
     */

    void insert(Sort sort);

    /**
     * 删
     * @param id
     */
    void delete(Long id);

    /**
     * 改
     * @param sort
     */
    void update(Sort sort);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<Sort> findPage(String queryString);

}
