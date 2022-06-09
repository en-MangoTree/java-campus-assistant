package com.knockknock.service;

import com.knockknock.entity.Sort;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;

/**
 * @CreateTime: 2022-06-09 21:11
 * @Description: 分类业务层
 */
public interface SortService {

    /**
     * 增
     * @param sort
     * @return
     */
    Result insert(Sort sort);

    /**
     *删
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 改
     * @param sort
     * @return
     */
    Result update(Sort sort);

    /**
     * 分页查询
     * @param queryInfo 页码 页面大小 查询内容
     * @return
     */
    Result findPage(QueryInfo queryInfo);

}
