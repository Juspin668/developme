package com.juspin.task.service;

import com.juspin.task.entity.PageData;
import com.juspin.task.entity.SearchCondition;
import com.juspin.task.entity.Transaction;

import java.util.List;

/**
 * Description: The Interface of transaction management.
 *
 * @author juspin
 * @since 2025/1/26
 */
public interface ITransactionService {

    /**
     * Query by ID.
     *
     * @param id The ID.
     * @return The transaction.
     */
    Transaction queryById(Long id);

    /**
     * Query page data.
     *
     * @param page The page no.
     * @param size The page size.
     * @return The page data.
     */
    PageData queryPage(int page, int size);

    /**
     * Query by search condition.
     *
     * @param condition The search condition.
     * @return The transactions.
     */
    List<Transaction> queryByCondition(SearchCondition condition);


    /**
     * Create transaction.
     *
     * @param transaction The transaction model.
     * @return The created transaction.
     */
    Transaction create(Transaction transaction);


    /**
     * Modify by transaction id.
     *
     * @param id The transaction ID.
     * @param newCategory The new category to update.
     * @param newRemark The new remark to update.
     * @return The transaction that updated.
     */
    Transaction modifyById(Long id, String newCategory, String newRemark);

    /**
     * Delete by transaction id.
     *
     * @param id The transaction id.
     * @return The transaction that deleted.
     */
    Transaction deleteById(Long id);
}
