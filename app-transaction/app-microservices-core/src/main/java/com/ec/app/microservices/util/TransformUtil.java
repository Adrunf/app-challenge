package com.ec.app.microservices.util;

import com.ec.app.entities.procedures.AccountEntity;
import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.entities.procedures.TransactionEntity;
import com.ec.app.microservices.account.AccountVO;
import com.ec.app.microservices.transaction.TransactionVO;

/**
 * @author arobayo
 */
public class TransformUtil {
    /**
     * Transform transaction VO to entity
     * @param transaction The transaction DTO
     * @return The transaction entity
     */
    public static TransactionEntity getTransactionEntity(TransactionVO transaction) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(transaction.getTransactionId());
        transactionEntity.setDate(transaction.getDate());
        transactionEntity.setTransactionType(transaction.getTransactionType());
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setBalance(transaction.getBalance());
        transactionEntity.setAccount(AccountEntity.builder().accountId(transaction.getAccountId()).build());
        transactionEntity.setStatus(transaction.getStatus());
        return transactionEntity;
    }

    /**
     * Transform account VO to entity
     * @param account The account DTO
     * @return The account entity
     */
    public static AccountEntity getAccountEntity(AccountVO account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountId(account.getAccountId());
        accountEntity.setAccountNumber(account.getAccountNumber());
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setInitialBalance(account.getInitialBalance());
        accountEntity.setStatus(account.getStatus());
        accountEntity.setCustomer(CustomerEntity.builder().customerId(account.getCustomerId()).build());
        return accountEntity;
    }
}
