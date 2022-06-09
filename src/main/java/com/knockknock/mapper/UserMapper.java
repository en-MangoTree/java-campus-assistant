package com.knockknock.mapper;

import com.github.pagehelper.Page;
import com.knockknock.entity.Menu;
import com.knockknock.entity.Permission;
import com.knockknock.entity.Role;
import com.knockknock.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @CreateTime: 2022-05-25 15:07
 * @Description:
 */
public interface UserMapper {

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User findByUsername(@Param("userName") String userName);

    /**
     * 根据用id查询用户角色
     * @param userId
     * @return
     */
    Role findRole(@Param("userId") Long userId);

    /**
     * 根据用户id查询菜单
     * @param userId
     * @return
     */
    List<Menu> findMenus(@Param("userId") Long userId);

    /**
     * 根据用户id和父级id查询子级菜单
     * @param id 父级id
     * @param userId 用户id
     * @return
     */
    List<Menu> findChildrenMenus(@Param("id") Long id, @Param("userId") Long userId);

    /**
     * 根据用户id查询权限数据
     * @param userId
     * @return
     */
    List<Permission> findPermissions(@Param("userId") Long userId);

    /**
     * 增
     * @param user
     */
    void insert(User user);

    /**
     * 删
     * @param id
     */
    void delete(Long id);

    /**
     * 改
     * @param user
     */
    void update(User user);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<User> findPage(String queryString);

    /**
     * 添加用户拥有的角色
     * @param userId
     * @param roleId
     */
    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    /**
     * 删除用户拥有的角色
     * @param userId
     */
    void deleteUserRole(@Param("userId") Long userId);

}
