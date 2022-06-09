package com.knockknock.mapper;

import com.github.pagehelper.Page;
import com.knockknock.entity.Trade;

/**
 * @CreateTime: 2022-06-09 20:49
 * @Description:
 */
public interface TradeMapper {

    /**
     * 增
     * @param trade
     */

    void insert(Trade trade);

    /**
     * 删
     * @param id
     */
    void delete(Long id);

    /**
     * 改
     * @param trade
     */
    void update(Trade trade);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<Trade> findPage(String queryString);

}
