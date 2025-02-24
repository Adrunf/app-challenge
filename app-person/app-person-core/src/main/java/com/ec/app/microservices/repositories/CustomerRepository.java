package com.ec.app.microservices.repositories;

import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.entities.procedures.QAccountEntity;
import com.ec.app.entities.procedures.QTransactionEntity;
import com.ec.app.microservices.reports.ReportRequestVO;
import com.ec.app.microservices.reports.ReportResponseVO;
import com.ec.app.microservices.customer.CustomerVO;
import com.ec.app.microservices.config.JPAQueryDslBaseRepository;
import com.querydsl.core.types.Projections;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ec.app.entities.procedures.QCustomerEntity.customerEntity;

/**
 * Repository for customer resources
 *
 * @author arobayo
 */
@Lazy
@Repository
public class CustomerRepository extends JPAQueryDslBaseRepository<CustomerEntity> implements ICustomerRepository {
    public CustomerRepository() {
        super(CustomerEntity.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerVO> findAllCustomers() {
        return from(customerEntity)
                .select(Projections.bean(CustomerVO.class,
                                customerEntity.customerId,
                                customerEntity.name,
                                customerEntity.gender,
                                customerEntity.identification,
                                customerEntity.address,
                                customerEntity.phone,
                                customerEntity.customerId,
                                customerEntity.password,
                                customerEntity.status,
                                customerEntity.age
                        )
                )
                .fetch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<CustomerVO> findCustomer(Long customerId) {
        return from(customerEntity)
                .select(Projections.bean(CustomerVO.class,
                                customerEntity.customerId,
                                customerEntity.name,
                                customerEntity.gender,
                                customerEntity.identification,
                                customerEntity.address,
                                customerEntity.phone,
                                customerEntity.customerId,
                                customerEntity.password,
                                customerEntity.status,
                                customerEntity.age
                        )
                )
                .where(customerEntity.customerId.eq(customerId))
                .stream().findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReportResponseVO> findReportByFilters(ReportRequestVO params) {
        QAccountEntity account = QAccountEntity.accountEntity;
        QTransactionEntity transaction = QTransactionEntity.transactionEntity;
        return from(customerEntity)
                .select(Projections.bean(ReportResponseVO.class,
                        customerEntity.name,
                        account.accountNumber,
                        account.accountType,
                        account.initialBalance,
                        account.status,
                        transaction.transactionType,
                        transaction.amount,
                        transaction.balance,
                        transaction.date
                ))
                .innerJoin(customerEntity.accounts, account)
                .innerJoin(account.transactions, transaction)
                .where(customerEntity.customerId.eq(params.getCustomerId()))
                .where(transaction.date.between(params.getInitialDate(), params.getEndDate()))
                .stream().toList();
    }

}
