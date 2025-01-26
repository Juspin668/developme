package com.juspin.task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description: 全局统一返回状态码，时间关系只规划部分简单场景
 *
 * @author juspin
 * @since 2025/1/26
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ReturnCode {

    SUCCESS(200, "Success."),
    INVALID_PARAM(400, "Invalid parameters."),
    FORBIDDEN_OPERATION(403, "Forbidden operation."),
    NOT_FOUND(404, "Not Found."),
    INTERNAL_ERROR(500, "Internal service error. Please contact the administrator."),
    // 业务异常
    DUPLICATE_CREATION(3001, "Duplicate creation."),
    OBJECT_NOT_EXIST(3002, "The operation object does not exist.");

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

}
