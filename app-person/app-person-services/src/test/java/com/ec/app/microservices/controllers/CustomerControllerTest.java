package com.ec.app.microservices.controllers;

import com.ec.app.microservices.customer.CustomerVO;
import com.ec.app.microservices.repositories.CustomerRepository;
import com.ec.app.microservices.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerController customerController;


    @Test
    public void testGetCustomer_Success() throws Exception {
        CustomerVO customer = CustomerVO
                .builder()
                .customerId(2L)
                .build();

        when(customerService.findCustomer(2L)).thenReturn(customer);

        mockMvc.perform(get("/customers/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.customerId").value(2));
    }


    @Test
    public void testGetCustomer_NotFound() throws Exception {
        when(customerService.findCustomer(1000L))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/customers/1000"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCustomerList_Success() throws Exception {
        CustomerVO customer1 = CustomerVO.builder().customerId(1L).build();
        CustomerVO customer2 = CustomerVO.builder().customerId(2L).build();
        List<CustomerVO> customerList = Arrays.asList(customer1, customer2);

        when(customerService.findAllCustomers()).thenReturn(customerList);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].customerId").value(1))
                .andExpect(jsonPath("$.data[1].customerId").value(2));
    }

    @Test
    public void testGetCustomerList_Empty() throws Exception {
        when(customerService.findAllCustomers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    public void testSaveCustomer_Success() throws Exception {
        CustomerVO customer = new CustomerVO();
        customer.setName("John Doe");
        customer.setAge(30);
        customer.setStatus(true);
        customer.setGender("Hombre");
        customer.setIdentification("123456789");
        customer.setAddress("123 Main St");
        customer.setPhone("987654321");
        customer.setPassword("password");

        doNothing().when(customerService).saveCustomer(any(CustomerVO.class));

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\", \"age\":30, \"status\":\"true\", \"gender\":\"Hombre\", \"identification\":\"123456789\", \"address\":\"123 Main St\", \"phone\":\"987654321\", \"password\":\"password\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Cliente creado con exito"));
    }

}
