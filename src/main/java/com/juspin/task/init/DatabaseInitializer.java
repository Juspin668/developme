package com.juspin.task.init;


import com.juspin.task.entity.Transaction;
import com.juspin.task.repository.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Description: 当前仅用于测试（服务启动时预置数据，方便测试）
 *
 * @author juspin
 * @since 2025/1/26
 */
@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        preSetData();
    }

    private void preSetData() {
        // Check if the table is empty and initialize with 100 records if it is
        if (transactionMapper.count() == 0) {
            List<Transaction> transactions = new ArrayList<>();
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                Transaction transaction = new Transaction();
                transaction.setOwner("User" + (i + 1));
                transaction.setCreateTime(System.currentTimeMillis());
                transaction.setFromAccount("Account" + (i + 1));
                transaction.setToAccount("Account" + (i + 2));
                transaction.setAmount((long) (random.nextDouble() * 49999 + 1)); // [0.01, 50000]
                transaction.setType(random.nextBoolean() ? "收入" : "支出");
                transaction.setCategory(randomCategory(random));
                transaction.setRemark("备注" + (i + 1));
                transactions.add(transaction);
            }
            transactionMapper.insertAll(transactions);
        }
    }

    private String randomCategory(Random random) {
        String[] categories = {"转账", "支付", "取款", "存款"};
        return categories[random.nextInt(categories.length)];
    }
}
