package com.ec.app.microservices.services;

import com.ec.app.microservices.reports.ReportRequestVO;
import com.ec.app.microservices.reports.ReportResponseVO;
import com.ec.app.microservices.customer.CustomerVO;

import java.util.List;

/**
 * Interface service used for customer resources
 *
 * @author arobayo
 */
public interface ICustomerService {

    /**
     * Return the list of customers
     *
     * @return List<CustomerVo> The list of customers
     */
    List<CustomerVO> findAllCustomers();

    /**
     * Return the information of customer by customerId
     * @param customerId The unique customer identifier code
     * @return CustomerVo The customer values
     */
    CustomerVO findCustomer(Long customerId);

    /**
     * Save a new customer
     *
     * @param customer The customer values
     */
    void saveCustomer(CustomerVO customer);

    /**
     * Update customer information.
     *
     * @param customer The customer values
     */
    void updateCustomer(CustomerVO customer);

    /**
     * Delete a customer.
     *
     * @param customerId The customer identifier code
     */
    void deleteCustomer(Long customerId);

    /**
     * Return the actual account status values
     *
     * @param params AccountStatusRequestVo The account request parameters
     * @return List<AccountStatusResponseVo> List of transactions made in the account
     */
    List<ReportResponseVO> generateAccountReport(ReportRequestVO params);
}
