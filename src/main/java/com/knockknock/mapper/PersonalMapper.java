package com.knockknock.mapper;

import com.github.pagehelper.Page;
import com.knockknock.entity.Trade;
import org.apache.ibatis.annotations.Select;

/**
 * @CreateTime: 2022-06-11 16:05
 * @Description: 个人管理
 */
public interface PersonalMapper {

    /**
     * 根据当前登陆用户id查询名下的所有交易
     * @param userId
     * @return
     */
    @Select("select * from tb_trade where id in (select trade_id from users_trades where user_id = #{userId})")
    Page<Trade> findPersonalTrade(Long userId);

    /**
     * 根据当前登陆用户id查询名下的所有收藏
     * @param userId
     * @return
     */
    @Select("select * from tb_trade where id in (select trade_id from users_collections where user_id = #{userId})")
    Page<Trade> findCollection(Long userId);
}
