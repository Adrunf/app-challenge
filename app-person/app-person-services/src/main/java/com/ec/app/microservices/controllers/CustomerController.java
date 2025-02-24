package com.ec.app.microservices.controllers;

import com.ec.app.microservices.customer.CustomerVO;
import com.ec.app.microservices.config.Response;
import com.ec.app.microservices.constants.PersonConstants;
import com.ec.app.microservices.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customers")
@Lazy
public class CustomerController {
    @Lazy
    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public ResponseEntity<Response<List<CustomerVO>>> findAllCustomers() {
        return new ResponseEntity<>(Response.<List<CustomerVO>>builder()
                .data(customerService.findAllCustomers())
                .message(PersonConstants.SEARCHED_MESSAGE)
                .code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Response<CustomerVO>> findCustomer(@PathVariable Long customerId) {
        return new ResponseEntity<>(Response.<CustomerVO>builder()
                .data(customerService.findCustomer(customerId))
                .message(PersonConstants.SEARCHED_MESSAGE)
                .code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Response<Void>> saveCustomer(
            @RequestBody CustomerVO customer) {
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(Response.<Void>builder()
                .code(HttpStatus.CREATED.value())
                .message(PersonConstants.CREATED_MESSAGE)
                .build(), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Response<Void>> updateCustomer(@PathVariable Long customerId,
                                                         @RequestBody CustomerVO customer) {
        customer.setCustomerId(customerId);
        customerService.updateCustomer(customer);
        return new ResponseEntity<>(Response.<Void>builder()
                .code(HttpStatus.OK.value())
                .message(PersonConstants.UPDATED_MESSAGE)
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Response<Void>> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(Response.<Void>builder()
                .code(HttpStatus.OK.value())
                .message(PersonConstants.DELETED_MESSAGE)
                .build(), HttpStatus.OK);
    }

}
