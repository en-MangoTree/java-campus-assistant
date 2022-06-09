package com.knockknock.entity;

import lombok.Data;

import java.util.List;

/**
 * @CreateTime: 2022-05-07 22:40
 * @Description:
 */
@Data
public class Menu {

    private Long id;

    private String path;

    private String icon;

    private String title;

    private String component;

    private Long parentId;

    private boolean status;

    private List<Menu> children;

}
