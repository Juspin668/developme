package com.juspin.task.service;

import com.juspin.task.entity.PageData;
import com.juspin.task.entity.SearchCondition;
import com.juspin.task.entity.Transaction;
import com.juspin.task.exception.ServiceException;
import com.juspin.task.repository.dao.TransactionDao;
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
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionDao transactionDao;

    @Test
    public void test_queryById_should_success_when_valid_param() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        Mockito.when(transactionDao.selectById(mockedTransaction.getId())).thenReturn(mockedTransaction);
        Transaction result = transactionService.queryById(mockedTransaction.getId());
        Assert.assertEquals(mockedTransaction, result);
    }

    @Test
    public void test_queryPage_should_success_when_valid_param() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        Mockito.when(this.transactionDao.selectAll(anyInt(), anyInt())).thenReturn(List.of(mockedTransaction));
        PageData result = this.transactionService.queryPage(0, 10);
        Assert.assertNotNull(result);
    }

    @Test
    public void test_queryByCondition_should_success_when_valid_param() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        mockedTransaction.setType("支出");
        mockedTransaction.setOwner("testUser");
        mockedTransaction.setAmount(666L);
        SearchCondition searchCondition = new SearchCondition();
        searchCondition.setOwner("testUser");
        searchCondition.setType("支出");
        searchCondition.setMaxAmount(5000L);
        searchCondition.setMinAmount(500L);
        Mockito.when(this.transactionDao.selectByCondition(searchCondition)).thenReturn(List.of(mockedTransaction));
        List<Transaction> result = this.transactionService.queryByCondition(searchCondition);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void test_create_should_success_when_valid_param() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        mockedTransaction.setType("支出");
        mockedTransaction.setOwner("testUser");
        mockedTransaction.setAmount(666L);
        mockedTransaction.setFromAccount("testCount01");
        mockedTransaction.setToAccount("testCount02");
        Transaction createdTransaction = this.transactionService.create(mockedTransaction);
        Assert.assertEquals(mockedTransaction, createdTransaction);
    }

    @Test
    public void test_modifyById_should_success_when_valid_param() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        mockedTransaction.setCategory("testModify");
        mockedTransaction.setRemark("testModify");
        Mockito.when(this.transactionDao.selectById(any())).thenReturn(mockedTransaction);
        Transaction transaction = this.transactionService.modifyById(1L, "tsetModify", "testModify");
        Assert.assertNotNull(mockedTransaction);
    }

    @Test(expected = ServiceException.class)
    public void test_modifyById_should_throw_exception_when_not_exist() {
        Transaction transaction = this.transactionService.modifyById(1L, "tsetModify", "testModify");
    }

    @Test
    public void test_deleteById_should_success_when_valid_param() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        Mockito.when(this.transactionDao.selectById(any())).thenReturn(mockedTransaction);
        Transaction transaction = this.transactionService.deleteById(1L);
        Assert.assertNotNull(transaction);
    }

    @Test(expected = ServiceException.class)
    public void test_deleteById_should_throw_exception_when_not_exist() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        Transaction transaction = this.transactionService.deleteById(1L);
        this.transactionDao.deleteById(1L);
    }
}