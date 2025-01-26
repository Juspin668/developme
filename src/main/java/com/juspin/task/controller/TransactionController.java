package com.juspin.task.controller;

import com.juspin.task.entity.PageData;
import com.juspin.task.entity.SearchCondition;
import com.juspin.task.entity.Transaction;
import com.juspin.task.service.ITransactionService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Description: The controller of transaction operation.
 *
 * @author juspin
 * @since 2025/1/26
 */
@Validated
@RestController
@RequestMapping("/transaction")
//@Api(value = "Transaction Management System", description = "restful apis")
public class TransactionController {

    private final ITransactionService transactionService;

    @Autowired
    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/test")
    // @ApiOperation(value = "Just for test!", notes = "return hello world!")
    public List<String> testApi() {
        return Arrays.asList("hello", "juspin");
    }

    @GetMapping("/{id}")
    public Transaction query(@PathVariable Long id) {
        return transactionService.queryById(id);
    }

    @GetMapping("/listAll")
    public PageData listAll(@RequestParam int page, @RequestParam int size) {
        return transactionService.queryPage(page, size);
    }

    @PostMapping("/filter")
    public List<Transaction> filter(@Valid @RequestBody SearchCondition condition) {
        return transactionService.queryByCondition(condition);
    }

    @PostMapping("/create")
    public Transaction create(@Valid @RequestBody Transaction transaction) {
        return transactionService.create(transaction);
    }

    @PutMapping("/update/{id}")
    public Transaction update(@PathVariable Long id, @RequestParam String category, @RequestParam String remark) {
        return transactionService.modifyById(id, category, remark);
    }

    @DeleteMapping("/delete/{id}")
    public Transaction delete(@PathVariable Long id) {
        return transactionService.deleteById(id);
    }
}
