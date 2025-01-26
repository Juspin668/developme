package com.juspin.task.repository.dao;

import com.juspin.task.entity.SearchCondition;
import com.juspin.task.entity.Transaction;
import com.juspin.task.repository.mapper.TransactionMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class TransactionDaoTest {

    @InjectMocks
    private TransactionDao transactionDao;

    @Mock
    private TransactionMapper transactionMapper;

    @Test
    public void test_selectById_should_success() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        Mockito.when(this.transactionMapper.selectById(any())).thenReturn(mockedTransaction);
        Assert.assertNotNull(this.transactionDao.selectById(1L));
    }

    @Test
    public void test_selectAll_should_success() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        Mockito.when(this.transactionMapper.selectAll(anyInt(), anyInt())).thenReturn(List.of(mockedTransaction));
        List<Transaction> results = this.transactionDao.selectAll(0, 10);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void test_selectByCondition_should_success() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        Mockito.when(this.transactionMapper.selectByCondition(any())).thenReturn(List.of(mockedTransaction));
        SearchCondition condition = new SearchCondition();
        condition.setOwner("testUser");
        condition.setType("支出");
        List<Transaction> results = this.transactionDao.selectByCondition(condition);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void test_insert_should_success() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        this.transactionDao.insert(mockedTransaction);
        Mockito.verify(this.transactionMapper, Mockito.times(1)).insert(any());
    }

    @Test
    public void test_updateById_should_success() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        mockedTransaction.setCategory("testModify");
        mockedTransaction.setRemark("testModify");
        Mockito.when(this.transactionMapper.selectById(any())).thenReturn(mockedTransaction);
        Transaction transaction = this.transactionDao.updateById(1L, "tsetModify", "testModify", System.currentTimeMillis());
        Assert.assertNotNull(transaction);
    }

    @Test
    public void test_deleteById_should_success() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        this.transactionDao.deleteById(1L);
        Mockito.verify(this.transactionMapper, Mockito.times(1)).deleteById(any());
    }
}