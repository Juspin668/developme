package com.juspin.task.entity;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Description: The model of the transaction entity.
 *
 * @author juspin
 * @since 2025/1/26
 */
@Getter
@Setter
@NoArgsConstructor
public class Transaction implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    /**
     * 唯一ID(系统生成)
     */
    private Long id;

    /**
     * 用户ID(非空,字符长度[1,36])
     */
    @NotNull
    @Size(min = 1, max = 36)
    private String owner;

    /**
     * 创建时的时间戳(系统生成）
     */
    private Long createTime;

    /**
     * 转出账户(非空，字符长度[1,36])
     */
    @NotNull
    @Size(min = 1, max = 36)
    private String fromAccount;

    /**
     * 转入账户(非空，字符长度[1,36])
     */
    @NotNull
    @Size(min = 1, max = 36)
    private String toAccount;

    /**
     * 交易金额，单次交易[0.01,50000]
     */
    @NotNull
    @DecimalMin("0.01")
    @DecimalMax("50000")
    private Long amount;

    /**
     * 交易类型(非空)[收入、支出]，支持按此字段筛选查询
     */
    @NotNull
    private String type;

    /**
     * 交易分类[转账、支付、取款、存款...](支持修改）
     */
    @Size(max = 36)
    private String category;

    /**
     * 交易备注(支持修改,字符长度[0,255])
     */
    @Size(max = 255)
    private String remark;


    /**
     * 更新时的时间戳(系统生成）
     */
    private Long updateTime;
}
