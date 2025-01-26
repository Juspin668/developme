package com.juspin.task.repository.dao;

import com.juspin.task.entity.SearchCondition;
import com.juspin.task.entity.Transaction;
import com.juspin.task.repository.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description: The transaction Dao.
 *
 * @author juspin
 * @since 2025/1/26
 */
@Repository
public class TransactionDao {

    @Autowired
    private TransactionMapper transactionMapper;

    public Transaction selectById(Long id) {
        return transactionMapper.selectById(id);
    }

    public List<Transaction> selectAll(int page, int size) {
        return transactionMapper.selectAll(page * size, size);
    }

    public List<Transaction> selectByCondition(SearchCondition condition) {
        // 这里需要根据条件构建查询语句，可以使用MyBatis的动态SQL功能
        return transactionMapper.selectByCondition(condition);
    }

    public void insert(Transaction transaction) {
        transactionMapper.insert(transaction);
    }

    public Transaction updateById(Long id, String newCategory, String newRemark, long updateTime) {
        transactionMapper.updateById(id, newCategory, newRemark, updateTime);
        return transactionMapper.selectById(id);
    }

    public void deleteById(Long id) {
        transactionMapper.deleteById(id);
    }
}
