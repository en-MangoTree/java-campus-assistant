package com.knockknock.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果
 */

@Data
public class Result implements Serializable {

    /**
     * 响应标识（是否成功）
     */
    private boolean flag;

    /**
     * 响应信息
     */
    private String message;

    /**
     *响应数据
     */
    private Object data;


    public Result() {
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    /**
     * 响应成功信息与结果数据
     * @param message
     * @param data
     * @return
     */
    public static Result success(String message, Object data){
        return new Result(true,message,data);
    }

    /**
     * 响应成功信息
     * @param message
     * @return
     */
    public static Result success(String message) {
        return new Result(true,message);
    }

    /**
     * 响应失败结果
     * @param message
     * @return
     */
    public static Result fail(String message){
        return new Result(true,message);
    }
}
