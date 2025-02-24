package com.ec.app.microservices.repositories;

import com.ec.app.entities.procedures.AccountEntity;
import com.ec.app.entities.procedures.QAccountEntity;
import com.ec.app.entities.procedures.TransactionEntity;
import com.ec.app.microservices.account.AccountVO;
import com.ec.app.microservices.config.JPAQueryDslBaseRepository;
import com.ec.app.microservices.transaction.TransactionVO;
import com.querydsl.core.types.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ec.app.entities.procedures.QTransactionEntity.transactionEntity;

/**
 * Repository for process transaction resources
 *
 * @author arobayo
 */
@Lazy
@Repository
public class TransactionRepository extends JPAQueryDslBaseRepository<TransactionEntity> implements ITransactionRepository {
    public TransactionRepository() {
        super(TransactionEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TransactionVO> findTransactionList() {
        QAccountEntity account = QAccountEntity.accountEntity;
        return from(transactionEntity)
                .select(Projections.bean(TransactionVO.class,
                                transactionEntity.transactionId,
                                transactionEntity.date,
                                transactionEntity.transactionType,
                                transactionEntity.amount,
                                transactionEntity.balance,
                                Projections.bean(AccountVO.class,
                                        account.accountId,
                                        account.accountNumber,
                                        account.accountType,
                                        account.initialBalance
                                ).as("account"),
                                transactionEntity.status
                        )
                )
                .innerJoin(transactionEntity.account, account)
                .fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TransactionVO> findTransactionsByAccountId(Long accountId) {
        QAccountEntity account = QAccountEntity.accountEntity;
        return from(transactionEntity)
                .select(Projections.bean(TransactionVO.class,
                                transactionEntity.transactionId,
                                transactionEntity.date,
                                transactionEntity.transactionType,
                                transactionEntity.amount,
                                transactionEntity.balance,
                                Projections.bean(AccountVO.class,
                                        account.accountId,
                                        account.accountNumber,
                                        account.accountType,
                                        account.initialBalance,
                                        account.status
                                ).as("account"),
                                transactionEntity.status
                        )
                )
                .innerJoin(transactionEntity.account, account)
                .where(account.accountId.eq(accountId))
                .fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TransactionVO> findById(Long transactionId) {
        return from(transactionEntity)
                .where(transactionEntity.transactionId.eq(transactionId))
                .select(Projections.bean(TransactionVO.class,
                        transactionEntity.date,
                        transactionEntity.transactionType,
                        transactionEntity.amount,
                        transactionEntity.balance,
                        transactionEntity.status
                ))
                .stream().findFirst();
    }

}
