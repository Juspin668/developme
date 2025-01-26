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

    @Cacheable(cacheNames = "TransactionCache", key = "#id")
    public Transaction selectById(Long id) {
        return transactionMapper.selectById(id);
    }

    @Cacheable(cacheNames = "TransactionCache", key = "#page + '@' + #size")
    public List<Transaction> selectAll(int page, int size) {
        return transactionMapper.selectAll(page * size, size);
    }

    @Cacheable(cacheNames = "TransactionCache", key = "#condition.owner + '_' + #condition.type + '_' + #condition.category + '_' + #condition.minAmount + '_' + #condition.maxAmount")
    public List<Transaction> selectByCondition(SearchCondition condition) {
        // 这里需要根据条件构建查询语句，可以使用MyBatis的动态SQL功能
        return transactionMapper.selectByCondition(condition);
    }

    public void insert(Transaction transaction) {
        transactionMapper.insert(transaction);
    }

    // 更新交易信息并更新缓存
    @CachePut(cacheNames = "TransactionCache", key = "#id")
    public Transaction updateById(Long id, String newCategory, String newRemark, long updateTime) {
        transactionMapper.updateById(id, newCategory, newRemark, updateTime);
        return transactionMapper.selectById(id);
    }

    // 删除交易信息并移除缓存
    @CacheEvict(cacheNames = "TransactionCache", key = "#id")
    public void deleteById(Long id) {
        transactionMapper.deleteById(id);
    }
}
