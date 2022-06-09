package com.knockknock.mapper;

import com.github.pagehelper.Page;
import com.knockknock.entity.Menu;
import com.knockknock.entity.Permission;
import com.knockknock.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @CreateTime: 2022-05-13 23:28
 * @Description: 角色数据
 */
public interface RoleMapper {

    /**
     * 增
     * @param role
     */
    void insert(Role role);

    /**
     * 删
     * @param id
     */
    void delete(Long id);

    /**
     * 改
     * @param role
     */
    void update(Role role);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<Role> findPage(String queryString);

    /**
     * 根据角色id查询菜单
     * @param roleId
     * @return
     */
    List<Menu> findParentMenuById(@Param("roleId") Long roleId);

    /**
     * 根据父级菜单id与角色id查询子级菜单
     * @param parentId
     * @param roleId
     * @return
     */
    List<Menu> findChildrenMenu(@Param("parentId") Long parentId, @Param("roleId") Long roleId);

    /**
     * 根据角色id查询权限信息
     * @param roleId
     * @return
     */
    List<Permission> findPermissionById(@Param("roleId") Long roleId);

    /**
     * 根据角色id删除对应权限
     * @param roleId
     */
    void deletePermissionById(@Param("roleId") Long roleId);

    /**
     * 根据角色id删除对应菜单
     * @param roleId
     */
    void deleteMenuById(@Param("roleId") Long roleId);

    /**
     * 添加角色的权限信息
     * @param roleId
     * @param permissionId
     */
    void insertPermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    /**
     * 添加角色的菜单信息
     * @param roleId
     * @param menuId
     */
    void insertMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    /**
     * 根据label查询角色是否存在
     * @param label
     * @return
     */
    Role findByLabel(String label);
}
