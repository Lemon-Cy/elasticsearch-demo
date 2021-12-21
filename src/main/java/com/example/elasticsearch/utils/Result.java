package com.example.elasticsearch.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 统一响应json
 * </p>
 *
 * @author ChenYu
 * @since 2021/4/29 17:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    /**
     * 成功
     */
    private boolean success;

    private Integer code;

    /**
     * 消息
     */
    private String message;

    /**
     * 结果数据
     */
    private T data;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(true, 200, "success", data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(false, code, message, null);
    }

    public static <T> Result<T> fail(Integer code, String message,T data) {
        return new Result<>(false, code, message, data);
    }
}
