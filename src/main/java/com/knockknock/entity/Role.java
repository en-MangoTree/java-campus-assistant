package com.knockknock.entity;

import lombok.Data;

import java.util.List;

/**
 * @CreateTime: 2022-05-07 22:38
 * @Description:
 */
@Data
public class Role {

    private Long id;

    private String label;

    private String code;

    private boolean status;

    /**
     * 角色对应的菜单
     */
    private List<Menu> menus;

    /**
     * 角色对应的权限
     */
    private List<Permission> permissions;

}
