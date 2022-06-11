package com.knockknock.service;

import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;

/**
 * @CreateTime: 2022-06-11 16:19
 * @Description:
 */
public interface PersonalService {

    /**
     * 根据当前登陆用户id查询名下的所有交易
     * @param queryInfo
     * @return
     */
    Result findPersonalTrade(QueryInfo queryInfo);

    /**
     * 根据当前登陆用户id查询名下的所有收藏
     * @param queryInfo
     * @return
     */
    Result findCollection(QueryInfo queryInfo);
}
