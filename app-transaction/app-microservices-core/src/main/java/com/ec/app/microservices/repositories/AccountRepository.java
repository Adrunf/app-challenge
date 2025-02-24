package com.ec.app.microservices.repositories;

import com.ec.app.entities.procedures.AccountEntity;
import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.entities.procedures.QCustomerEntity;
import com.ec.app.microservices.account.AccountVO;
import com.ec.app.microservices.config.JPAQueryDslBaseRepository;
import com.ec.app.microservices.customer.CustomerVO;
import com.querydsl.core.types.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ec.app.entities.procedures.QAccountEntity.accountEntity;

/**
 * Repository for process account resources
 *
 * @author arobayo
 */
@Lazy
@Repository
public class AccountRepository extends JPAQueryDslBaseRepository<AccountEntity> implements IAccountRepository {
    public AccountRepository() {
        super(AccountEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AccountVO> findAllAccounts() {
        QCustomerEntity customer = QCustomerEntity.customerEntity;
        return from(accountEntity).select(Projections.bean(AccountVO.class,
                                accountEntity.accountId,
                                accountEntity.accountNumber,
                                accountEntity.accountType,
                                accountEntity.initialBalance,
                                accountEntity.status,
                                Projections.bean(CustomerVO.class,
                                        customer.customerId,
                                        customer.name,
                                        customer.status,
                                        customer.identification
                                ).as("customer")
                        )
                )
                .innerJoin(accountEntity.customer, customer)
                .fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AccountVO> findById(Long accountId) {
        QCustomerEntity customer = QCustomerEntity.customerEntity;
        return from(accountEntity)
                .where(accountEntity.accountId.eq(accountId))
                .select(Projections.bean(AccountVO.class,
                                accountEntity.accountId,
                                accountEntity.accountNumber,
                                accountEntity.accountType,
                                accountEntity.initialBalance,
                                accountEntity.status,
                                Projections.bean(CustomerVO.class,
                                        customer.customerId,
                                        customer.name,
                                        customer.identification,
                                        customer.status
                                ).as("customer")
                        )
                )
                .innerJoin(accountEntity.customer, customer)
                .stream().findFirst();
    }

}
