package com.knockknock.entity;

import lombok.Data;

/**
 * @CreateTime: 2022-06-09 20:45
 * @Description: 交易实体类
 */
@Data
public class Trade {

    private Long id;

    private String title;

    private String images;

    private String description;

    private String price;

    private Sort sort;

    private boolean status;

}
