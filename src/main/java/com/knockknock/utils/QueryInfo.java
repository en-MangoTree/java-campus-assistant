package com.knockknock.utils;

import lombok.Data;

/**
 * @CreateTime: 2022-05-05 22:56
 * @Description: 分页查询
 */
@Data
public class QueryInfo {

    /**
     * 第几页
     */
    private Integer pageNumber;

    /**
     *一页数据条数
     */
    private Integer pageSize;

    /**
     *查询内容
     */
    private String queryString;
}
