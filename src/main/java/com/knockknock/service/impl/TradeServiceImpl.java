package com.knockknock.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.knockknock.entity.Trade;
import com.knockknock.mapper.TradeMapper;
import com.knockknock.service.TradeService;
import com.knockknock.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime: 2022-06-09 21:14
 * @Description:
 */
@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Result insert(Trade trade) {
        tradeMapper.insert(trade);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("添加交易数据成功！");
    }

    @Override
    public Result delete(Long id) {
        tradeMapper.delete(id);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("删除交易数据成功！");
    }

    @Override
    public Result update(Trade trade) {
        tradeMapper.update(trade);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("修改交易数据成功！");
    }

    @Override
    public Result findPage(QueryInfo queryInfo) {
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<Trade> page = tradeMapper.findPage(queryInfo.getQueryString());
        long total = page.getTotal();
        List<Trade> result = page.getResult();
        return PageResult.pageResult(total, result);
    }

}
