package com.knockknock.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @CreateTime: 2022-05-05 22:51
 * @Description: 分页结果
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResult extends Result implements Serializable {

    /**
     *总记录数
     */
    private long total;

    /**
     * 分页数据
     */
    private List<?> rows;

    /**
     * 查询结果
     * @param total
     * @param list
     */
    public PageResult(long total, List<?> list){
        this.setFlag(true);
        this.setMessage("分页查询成功");
        this.total = total;
        this.rows = list;
    }

    /**
     * 返回分页数据
     * @param total 总条数
     * @param list 分页数据列表
     * @return
     */
    public static Result pageResult(long total, List<?> list){
        return new PageResult(total, list);
    }
}
