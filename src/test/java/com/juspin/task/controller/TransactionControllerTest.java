package com.juspin.task.controller;

import com.juspin.task.entity.PageData;
import com.juspin.task.entity.SearchCondition;
import com.juspin.task.entity.Transaction;
import com.juspin.task.service.ITransactionService;
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
public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private ITransactionService transactionService;

    @Test
    public void test_Api_should_return_hello_world() {
        List<String> result = this.transactionController.testApi();
        Assert.assertTrue(result.contains("hello"));
    }

    @Test
    public void test_query_should_success_when_valid_param() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        Mockito.when(this.transactionService.queryById(any())).thenReturn(transaction);
        Transaction result = this.transactionController.query(1L);
        Assert.assertNotNull(result);
    }

    @Test
    public void test_istAll_should_success_when_valid_param() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        PageData mockedPage= PageData.of(0, 10, List.of(transaction));
        Mockito.when(this.transactionService.queryPage(anyInt(), anyInt())).thenReturn(mockedPage);
        PageData result = this.transactionController.listAll(0, 10);
        Assert.assertNotNull(result);
    }

    @Test
    public void test_filter_should_success_when_valid_param() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        Mockito.when(this.transactionService.queryByCondition(any())).thenReturn(List.of(mockedTransaction));
        SearchCondition condition = new SearchCondition();
        condition.setOwner("testUser");
        condition.setType("支出");
        List<Transaction> results = this.transactionController.filter(condition);
        Assert.assertEquals(1, results.size());
    }

    @Test
    public void test_create_should_success_when_valid_param() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        Mockito.when(this.transactionService.create(any())).thenReturn(mockedTransaction);
        Transaction result = this.transactionController.create(mockedTransaction);
        Assert.assertNotNull(result);
    }

    @Test
    public void test_update_should_success_when_valid_param() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        mockedTransaction.setCategory("testModify");
        mockedTransaction.setRemark("testModify");
        Mockito.when(this.transactionService.modifyById(1L, "tsetModify", "testModify")).thenReturn(mockedTransaction);
        Transaction result = this.transactionController.update(1L, "tsetModify", "testModify");
        Assert.assertNotNull(result);
    }

    @Test
    public void test_delete_should_success_when_valid_param() {
        Transaction mockedTransaction = new Transaction();
        mockedTransaction.setId(1L);
        Mockito.when(this.transactionService.deleteById(1L)).thenReturn(mockedTransaction);
        Transaction result = this.transactionController.delete(1L);
        Assert.assertNotNull(result);
    }
  
}