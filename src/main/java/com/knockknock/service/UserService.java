package com.knockknock.service;

import com.knockknock.entity.User;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;
import com.knockknock.vo.LoginVo;

/**
 * @CreateTime: 2022-05-25 16:21
 * @Description:
 */
public interface UserService {

    /**
     * 登陆接口
     * @param loginVo
     * @return 返回token，用token获取资源
     */
    Result login(LoginVo loginVo);

    /**
     * 增
     * @param user
     * @return
     */
    Result insert(User user);

    /**
     *删
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 改
     * @param user
     * @return
     */
    Result update(User user);

    /**
     * 分页查询
     * @param queryInfo 页码 页面大小 查询内容
     * @return
     */
    Result findPage(QueryInfo queryInfo);

}
