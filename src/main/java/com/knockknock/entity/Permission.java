package com.knockknock.entity;

import lombok.Data;

/**
 * @CreateTime: 2022-05-07 22:43
 * @Description:
 */
@Data
public class Permission {

    private Long id;

    private String label;

    private String code;

    private boolean status;
}
