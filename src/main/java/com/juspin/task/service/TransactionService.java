package com.juspin.task.service;

import com.juspin.task.entity.PageData;
import com.juspin.task.entity.SearchCondition;
import com.juspin.task.entity.Transaction;
import com.juspin.task.exception.ServiceException;
import com.juspin.task.repository.dao.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * Description: The implements of transaction management interface.
 *
 * @author juspin
 * @since 2025/1/26
 */
@Service
public class TransactionService implements ITransactionService{

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public Transaction queryById(Long id) {
        return transactionDao.selectById(id);
    }

    @Override
    public PageData queryPage(int page, int size) {
        List<Transaction> transactions = transactionDao.selectAll(page, size);
        return PageData.of(page, size, transactions);
    }

    @Override
    public List<Transaction> queryByCondition(SearchCondition condition) {
        return transactionDao.selectByCondition(condition);
    }

    @Override
    public Transaction create(Transaction transaction) {
        transaction.setCreateTime(System.currentTimeMillis());
        transactionDao.insert(transaction);
        return transaction;
    }

    @Override
    public Transaction modifyById(Long id, String newCategory, String newRemark) {
        Transaction existTransaction = transactionDao.selectById(id);
        if (Objects.nonNull(existTransaction)) {
            long updateTime = System.currentTimeMillis();
            return transactionDao.updateById(id, newCategory, newRemark, updateTime);
        } else {
            throw new ServiceException("The transaction to modify does not exist.");
        }
    }

    @Override
    public Transaction deleteById(Long id) {
        Transaction transaction = transactionDao.selectById(id);
        if (Objects.nonNull(transaction)) {
            transactionDao.deleteById(id);
            return transaction;
        } else {
            throw new ServiceException("The transaction to delete does not exist.");
        }
    }
}
