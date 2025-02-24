package com.ec.app.microservices.services;

import com.ec.app.entities.procedures.AccountEntity;
import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.microservices.account.AccountVO;
import com.ec.app.microservices.constants.constants.TransactionConstants;
import com.ec.app.microservices.repositories.IAccountRepository;
import com.ec.app.microservices.util.TransformUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service for account resources
 *
 * @author arobayo
 */
@Lazy
@Service
@Transactional
public class AccountService implements IAccountService {

    @Lazy
    private IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountVO> findAllAccounts() {
        return accountRepository.findAllAccounts();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountVO findAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, TransactionConstants.ACCOUNT_NOT_FOUND));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAccount(AccountVO account) {
        try {

        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    TransactionConstants.GENERIC_ERROR_MESSAGE + e.getMessage()
            );
        }
        accountRepository.save(AccountEntity.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .initialBalance(account.getInitialBalance())
                .status(account.getStatus())
                .customer(CustomerEntity.builder().customerId(account.getCustomerId()).build())
                .build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAccount(AccountVO account) {
        AccountEntity updatedEntity = accountRepository.findById(account.getAccountId())
                .map(TransformUtil::getAccountEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, TransactionConstants.ACCOUNT_NOT_FOUND));

        accountRepository.update(updatedEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAccount(Long accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId)
                .map(TransformUtil::getAccountEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, TransactionConstants.ACCOUNT_NOT_FOUND));

        accountRepository.delete(accountEntity);
    }
}
