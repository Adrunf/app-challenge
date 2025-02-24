package com.ec.app.microservices.services;

import com.ec.app.entities.procedures.TransactionEntity;
import com.ec.app.microservices.account.AccountVO;
import com.ec.app.microservices.transaction.TransactionVO;
import com.ec.app.microservices.config.Response;
import com.ec.app.microservices.constants.constants.TransactionConstants;
import com.ec.app.microservices.repositories.IAccountRepository;
import com.ec.app.microservices.repositories.ITransactionRepository;
import com.ec.app.microservices.util.TransformUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Service for transaction resources
 *
 * @author arobayo
 */
@Lazy
@Service
@Transactional
public class TransactionService implements ITransactionService {

    @Lazy
    private ITransactionRepository transactionRepository;

    @Lazy
    private IAccountRepository accountRepository;

    public TransactionService(ITransactionRepository transactionRepository, IAccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TransactionVO> findTransactionList() {
        return transactionRepository.findTransactionList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response<String> saveTransaction(TransactionVO transaction) {
        AccountVO account = accountRepository.findById(transaction.getAccountId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        TransactionConstants.ACCOUNT_NOT_FOUND));

        double newBalance = calculateBalance(account, transaction);

        if (newBalance < 0) {
            return buildResponse(HttpStatus.BAD_REQUEST, TransactionConstants.INSUFFICIENT_FUNDS);
        }

        // Save transaction if is OK
        saveTransaction(account, transaction, newBalance);

        return buildResponse(HttpStatus.CREATED, TransactionConstants.CREATED_MESSAGE);
    }

    /**
     * Calculate the balance for the transaction
     *
     * @param account     The account values
     * @param transaction The transaction request values
     * @return The balance after transaction amount
     */
    private double calculateBalance(AccountVO account, TransactionVO transaction) {
        List<TransactionVO> transactionList = transactionRepository.findTransactionsByAccountId(account.getAccountId());

        double balance = transactionList.stream()
                .max(Comparator.comparing(TransactionVO::getDate))
                .map(TransactionVO::getBalance)
                .orElse(account.getInitialBalance());

        return transaction.getTransactionType().equals(TransactionConstants.DEPOSIT_VALUE)
                ? balance + transaction.getAmount()
                : balance - transaction.getAmount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTransaction(TransactionVO transaction) {
        TransactionEntity transactionEntity = transactionRepository.findById(transaction.getTransactionId())
                .map(TransformUtil::getTransactionEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, TransactionConstants.TRANSACTION_NOT_FOUND));

        transactionRepository.update(transactionEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTransaction(Long transactionId) {
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId)
                .map(TransformUtil::getTransactionEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, TransactionConstants.TRANSACTION_NOT_FOUND));

        transactionRepository.delete(transactionEntity);
    }

    /**
     * Save the transaction with the request values
     *
     * @param account     The account values.
     * @param transaction The transaction values
     * @param newBalance  The balance value
     */
    private void saveTransaction(AccountVO account, TransactionVO transaction, double newBalance) {
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .date(new Date())
                .transactionType(transaction.getTransactionType())
                .amount(transaction.getAmount())
                .balance(newBalance)
                .account(TransformUtil.getAccountEntity(account))
                .status(transaction.getStatus())
                .build();

        transactionRepository.save(transactionEntity);
    }

    /**
     * Generate the response
     *
     * @param status  The http status code
     * @param message The message of the service
     * @return Response
     */
    private Response<String> buildResponse(HttpStatus status, String message) {
        return Response.<String>builder()
                .code(status.value())
                .message(message)
                .build();
    }
}
