package com.ec.app.microservices.repositories;

import com.ec.app.entities.procedures.TransactionEntity;
import com.ec.app.microservices.config.IQueryDslBaseRepository;
import com.ec.app.microservices.transaction.TransactionVO;

import java.util.List;
import java.util.Optional;

/**
 * Interface service for transaction resources
 *
 * @author arobayo
 */
public interface ITransactionRepository extends IQueryDslBaseRepository<TransactionEntity> {

    /**
     * Return transaction list information
     *
     * @return List<TransactionVO>
     */
    List<TransactionVO> findTransactionList();

    /**
     * Return transaction list information
     *
     * @return List<TransactionVO>
     */
    List<TransactionVO> findTransactionsByAccountId(Long accountId);

    /**
     * Return transaction information by transactionId
     *
     * @return Optional<TransactionVO>
     */
    Optional<TransactionVO> findById(Long transactionId);

}
