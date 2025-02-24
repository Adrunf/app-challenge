package com.ec.app.microservices.services;

import com.ec.app.entities.procedures.TransactionEntity;
import com.ec.app.microservices.transaction.TransactionVO;
import com.ec.app.microservices.config.Response;

import java.util.List;

/**
 * Interface service for transaction resources
 *
 * @author arobayo
 */
public interface ITransactionService {

    /**
     * Return transaction list information
     *
     * @return List<TransactionVO>
     */
    List<TransactionVO> findTransactionList();

    /**
     * Save the transaction
     *
     * @param transaction TransactionVo The transaction values
     * @return Response<String>
     */
    Response<String> saveTransaction(TransactionVO transaction);

    /**
     * Update the transaction.
     *
     * @param transaction TransactionVo The transaction values to update
     */
    void updateTransaction(TransactionVO transaction);

    /**
     * Delete the transaction.
     *
     * @param transactionId The transaction identification code
     */
    void deleteTransaction(Long transactionId);

}
