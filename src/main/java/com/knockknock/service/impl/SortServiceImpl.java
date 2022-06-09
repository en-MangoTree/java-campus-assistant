package com.knockknock.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.knockknock.entity.Sort;
import com.knockknock.mapper.SortMapper;
import com.knockknock.service.SortService;
import com.knockknock.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @CreateTime: 2022-06-09 21:13
 * @Description:
 */
@Service
public class SortServiceImpl implements SortService {

    @Autowired
    private SortMapper sortMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Result insert(Sort sort) {
        sortMapper.insert(sort);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("添加分类数据成功！");
    }

    @Override
    public Result delete(Long id) {
        sortMapper.delete(id);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("删除分类数据成功！");
    }

    @Override
    public Result update(Sort sort) {
        sortMapper.update(sort);
        redisUtils.delKey("userInfo_" + SecurityUtils.getUsername());
        return Result.success("修改分类数据成功！");
    }

    @Override
    public Result findPage(QueryInfo queryInfo) {
        PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
        Page<Sort> page = sortMapper.findPage(queryInfo.getQueryString());
        long total = page.getTotal();
        List<Sort> result = page.getResult();
        return PageResult.pageResult(total, result);
    }

}
