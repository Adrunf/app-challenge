package com.ec.app.microservices.repositories;

import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.microservices.reports.ReportRequestVO;
import com.ec.app.microservices.reports.ReportResponseVO;
import com.ec.app.microservices.customer.CustomerVO;
import com.ec.app.microservices.config.IQueryDslBaseRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface service used for customer resources
 *
 * @author arobayo
 */
public interface ICustomerRepository extends IQueryDslBaseRepository<CustomerEntity> {

    /**
     * Return the list of customers
     *
     * @return List<CustomerVo> The list of customers
     */
    List<CustomerVO> findAllCustomers();

    /**
     * Return the information of customer by customerId
     * @param customerId The unique customer identifier code
     * @return CustomerVo The customer DTO
     */
    Optional<CustomerVO> findCustomer(Long customerId);


    /**
     * Return actual account status values.
     * @param params The parameters to search for the report
     * @return ReportRequestVO The list of transactions the user made
     */
    List<ReportResponseVO> findReportByFilters(ReportRequestVO params);
}
