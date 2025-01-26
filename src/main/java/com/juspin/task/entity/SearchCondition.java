package com.juspin.task.entity;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Description: Search condition model.
 *
 * @author juspin
 * @since 2025/1/26
 */
@Getter
@Setter
@NoArgsConstructor
public class SearchCondition {

    @NotNull
    @Size(min = 1, max = 36)
    private String owner;

    @NotNull
    @Size(min = 1, max = 36)
    private String type;

    @Size(max = 36)
    private String category;

    @DecimalMin("0.01")
    @DecimalMax("5000")
    private Long minAmount;

    @DecimalMin("0.01")
    @DecimalMax("5000")
    private Long maxAmount;

}
