package com.ec.app.microservices.services;

import com.ec.app.entities.procedures.AccountEntity;
import com.ec.app.microservices.account.AccountVO;

import java.util.List;

/**
 * Interface service for account resources
 *
 * @author arobayo
 */
public interface IAccountService {

    /**
     * Return account list information
     *
     * @return List<AccountEntity>
     */
    List<AccountVO> findAllAccounts();

    /**
     * Return account information
     *
     * @param accountId Long
     * @return AccountEntity
     */
    AccountVO findAccount(Long accountId);

    /**
     * Save account
     *
     * @param account AccountVo
     */
    void saveAccount(AccountVO account);

    /**
     * Update account information.
     *
     * @param account AccountVo
     */
    void updateAccount(AccountVO account);

    /**
     * Delete an account.
     *
     * @param accountId Long
     */
    void deleteAccount(Long accountId);

}
