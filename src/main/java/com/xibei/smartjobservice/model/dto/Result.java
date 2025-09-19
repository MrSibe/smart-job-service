package com.xibei.smartjobservice.model.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 统一响应结果
 */
@Data
public class Result<T> implements Serializable {
    private String code;
    private T data;
    private String msg;

    public Result() {
    }

    public Result(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Result<T> success() {
        return new Result<>("200", null, "操作成功");
    }

    public static <T> Result<T> success(T data) {
        return new Result<>("200", data, "操作成功");
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>("200", null, msg);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>("200", data, msg);
    }

    public static <T> Result<T> error(String code, String msg) {
        return new Result<>(code, null, msg);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>("500", null, msg);
    }

    public static <T> Result<T> error() {
        return new Result<>("500", null, "系统异常");
    }
}