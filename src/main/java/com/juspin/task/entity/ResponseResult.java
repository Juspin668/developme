package com.juspin.task.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Description: 统一返回结果封装
 *
 * @author juspin
 * @since 2025/1/26
 */
@Getter
@Setter
public class ResponseResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    /**
     * Status code.
     */
    private int code;

    /**
     * Return data
     */
    private T data;

    /**
     * Tip massages.
     */
    private String message;

    private ResponseResult(ReturnCode returnCode, T data) {
        this.code = returnCode.getCode();
        this.data = data;
        this.message = returnCode.getMessage();
    }

    private ResponseResult(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /**
     * Response failure with status code and massages.
     *
     * @param <T> The type of the data.
     * @return The response result.
     */
    public static <T> ResponseResult<T> nOk(ReturnCode returnCode) {
        return new ResponseResult<>(returnCode, null);
    }

    /**
     * Response failure with custom status code and massages.
     *
     * @param <T> The type of the data.
     * @param code The return code.
     * @param data The response data.
     * @param message The tip massages.
     * @return The response result.
     */
    public static <T> ResponseResult<T> nOk(Integer code, T data, String message){
        return new ResponseResult<>(code, data, message);
    }

    /**
     * Response ok.
     *
     * @param responseBody The response body.
     * @return The final response.
     */
    public static Object ok(Object responseBody) {
        if (responseBody instanceof ResponseResult) {
            return responseBody;
        } else {
            return ResponseResult.success(responseBody);
        }
    }

    private static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ReturnCode.SUCCESS, data);
    }
}
