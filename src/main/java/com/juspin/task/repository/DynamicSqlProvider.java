package com.juspin.task.repository;

import com.juspin.task.entity.SearchCondition;
import org.apache.ibatis.jdbc.SQL;

/**
 * Description: Dynamic sql provider.
 *
 * @author juspin
 * @since 2025/1/26
 */
public class DynamicSqlProvider {

    public String searchByCondition(SearchCondition condition) {
        return new SQL() {{
            SELECT("*");
            FROM("transactions");
            WHERE("owner = #{owner}");
            WHERE("type = #{type}");
            if (condition.getCategory() != null) {
                WHERE("category = #{category}");
            }
            if (condition.getMinAmount() != null) {
                WHERE("amount >= #{minAmount}");
            }
            if (condition.getMaxAmount() != null) {
                WHERE("amount <= #{maxAmount}");
            }
        }}.toString();
    }
}
