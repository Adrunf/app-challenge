package com.ec.app.microservices.repositories;

import com.ec.app.entities.procedures.AccountEntity;
import com.ec.app.microservices.account.AccountVO;
import com.ec.app.microservices.config.IQueryDslBaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface repository for account resources
 *
 * @author arobayo
 */
public interface IAccountRepository extends IQueryDslBaseRepository<AccountEntity> {

    /**
     * Return account list information
     *
     * @return List<AccountVO>
     */
    List<AccountVO> findAllAccounts();

    /**
     * Return account information by accountId
     *
     * @param accountId Long
     * @return Optional<AccountVO>
     */
    Optional<AccountVO> findById(Long accountId);

}
