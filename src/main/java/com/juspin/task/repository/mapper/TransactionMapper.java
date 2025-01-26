package com.juspin.task.repository.mapper;


import com.juspin.task.entity.SearchCondition;
import com.juspin.task.entity.Transaction;
import com.juspin.task.repository.DynamicSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * Description: The mapper interface.
 *
 * @author juspin
 * @since 2025/1/26
 */
@Mapper
public interface TransactionMapper {

    // 根据ID查询交易记录
    @Select("SELECT * FROM transactions WHERE id=#{id}")
    Transaction selectById(@Param("id") Long id);

    // 查询所有交易记录（带分页）
    @Select("SELECT * FROM transactions LIMIT #{offset}, #{size}")
    List<Transaction> selectAll(@Param("offset") int offset, @Param("size") int size);

    // 根据条件查询交易记录
    @SelectProvider(type = DynamicSqlProvider.class, method = "searchByCondition")
    List<Transaction> selectByCondition(SearchCondition condition);

    // 插入新的交易记录
    @Insert("INSERT INTO transactions (owner, createTime, fromAccount, toAccount, amount, type, category, remark, updateTime) " +
            "VALUES (#{owner}, #{createTime}, #{fromAccount}, #{toAccount}, #{amount}, #{type}, #{category}, #{remark}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Transaction transaction);

    // 根据ID更新交易记录
    @Update("UPDATE transactions SET category=#{newCategory}, remark=#{newRemark}, updateTime=#{updateTime} WHERE id=#{id}")
    void updateById(@Param("id") Long id, @Param("newCategory") String newCategory, @Param("newRemark") String newRemark, @Param("updateTime") long updateTime);

    // 根据ID删除交易记录
    @Delete("DELETE FROM transactions WHERE id=#{id}")
    void deleteById(@Param("id") Long id);


    // 统计交易记录总数
    @Select("SELECT COUNT(*) FROM transactions")
    int count();

    @Insert({
            "<script>",
            "INSERT INTO transactions (owner, createTime, fromAccount, toAccount, amount, type, category, remark) VALUES ",
            "<foreach collection='transactions' item='transaction' separator=','>",
            "(#{transaction.owner}, #{transaction.createTime}, #{transaction.fromAccount}, #{transaction.toAccount}, #{transaction.amount}, #{transaction.type}, #{transaction.category}, #{transaction.remark})",
            "</foreach>",
            "</script>"
    })
    void insertAll(@Param("transactions") List<Transaction> transactions);

}
