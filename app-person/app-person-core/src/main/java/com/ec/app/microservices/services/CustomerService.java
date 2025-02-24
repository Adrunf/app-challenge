package com.ec.app.microservices.services;

import com.ec.app.entities.procedures.CustomerEntity;
import com.ec.app.microservices.constants.PersonConstants;
import com.ec.app.microservices.reports.ReportRequestVO;
import com.ec.app.microservices.reports.ReportResponseVO;
import com.ec.app.microservices.customer.CustomerVO;
import com.ec.app.microservices.repositories.ICustomerRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service used for the CRUD of customer
 *
 * @author arobayo
 */
@Lazy
@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Lazy
    private ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerVO> findAllCustomers() {
        return customerRepository.findAllCustomers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerVO findCustomer(Long customerId) {
        return customerRepository.findCustomer(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, PersonConstants.NOT_FOUND_MESSAGE
                ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCustomer(CustomerVO customer) {
        try {
            customerRepository.save(CustomerEntity.builder()
                    .name(customer.getName())
                    .gender(customer.getGender())
                    .age(customer.getAge())
                    .identification(customer.getIdentification())
                    .address(customer.getAddress())
                    .phone(customer.getPhone())
                    .password(customer.getPassword())
                    .status(customer.getStatus())
                    .build());
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    PersonConstants.GENERIC_ERROR_MESSAGE + e.getMessage()
            );
        }
    }

    @Override
    public void updateCustomer(CustomerVO customer) {
        CustomerEntity existingCustomer = customerRepository.findCustomer(customer.getCustomerId())
                .map(this::getCustomerEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PersonConstants.NOT_FOUND_MESSAGE));

        customerRepository.update(existingCustomer);
    }


    @Override
    public void deleteCustomer(Long customerId) {
        CustomerEntity customerEntity = customerRepository.findCustomer(customerId)
                .map(this::getCustomerEntity)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PersonConstants.NOT_FOUND_MESSAGE));

        customerRepository.delete(customerEntity);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReportResponseVO> generateAccountReport(ReportRequestVO params) {
        try {
            return customerRepository.findReportByFilters(params);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, PersonConstants.REPORT_ERROR_MESSAGE, e);
        }
    }

    /**
     * Generate the entity based on the DTO of the customer
     *
     * @param customer The customer DTO
     * @return The entity with the respective values
     */
    private CustomerEntity getCustomerEntity(CustomerVO customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customer.getCustomerId());
        customerEntity.setName(customer.getName());
        customerEntity.setGender(customer.getGender());
        customerEntity.setAge(customer.getAge());
        customerEntity.setIdentification(customer.getIdentification());
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setPhone(customer.getPhone());
        customerEntity.setPassword(customer.getPassword());
        customerEntity.setStatus(customer.getStatus());
        return customerEntity;
    }

}
