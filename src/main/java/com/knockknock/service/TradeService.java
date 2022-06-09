package com.knockknock.service;

import com.knockknock.entity.Trade;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;

/**
 * @CreateTime: 2022-06-09 21:12
 * @Description: 交易业务层
 */
public interface TradeService {

    /**
     * 增
     * @param trade
     * @return
     */
    Result insert(Trade trade);

    /**
     *删
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 改
     * @param trade
     * @return
     */
    Result update(Trade trade);

    /**
     * 分页查询
     * @param queryInfo 页码 页面大小 查询内容
     * @return
     */
    Result findPage(QueryInfo queryInfo);
}
