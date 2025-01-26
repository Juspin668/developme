package com.juspin.task.entity;

import lombok.Data;

import java.util.List;

/**
 * Description: Page query response data.
 *
 * @author juspin
 * @since 2025/1/26
 */
@Data
public class PageData {
    private int page;
    private int size;
    private List<Transaction> transactions;

    private PageData(int page, int size, List<Transaction> transactions) {
        this.page = page;
        this.size = size;
        this.transactions = transactions;
    }

    /**
     * Static all args constructor.
     *
     * @param page The current page no.
     * @param size The page size.
     * @param transactions The transactions.
     * @return The current page data.
     */
    public static PageData of(int page, int size, List<Transaction> transactions) {
        return new PageData(page, size, transactions);
    }
}
