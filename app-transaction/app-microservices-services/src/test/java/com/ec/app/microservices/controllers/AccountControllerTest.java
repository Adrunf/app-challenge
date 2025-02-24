package com.ec.app.microservices.controllers;

import com.ec.app.entities.procedures.AccountEntity;
import com.ec.app.microservices.account.AccountVO;
import com.ec.app.microservices.services.AccountService;
import org.junit.jupiter.api.Test;
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

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;


    @Test
    public void testGetAccount_Success() throws Exception {
        AccountVO account = AccountVO
                .builder()
                .accountId(2L)
                .build();

        when(accountService.findAccount(2L)).thenReturn(account);

        mockMvc.perform(get("/account/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accountId").value(2));
    }

    @Test
    public void testGetAccount_NotFound() throws Exception {
        when(accountService.findAccount(1000L))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/account/1000"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAccountList_Success() throws Exception {
        AccountVO account1 = AccountVO.builder().accountId(1L).build();
        AccountVO account2 = AccountVO.builder().accountId(2L).build();
        List<AccountVO> accountList = Arrays.asList(account1, account2);

        when(accountService.findAllAccounts()).thenReturn(accountList);

        mockMvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].accountId").value(1))
                .andExpect(jsonPath("$.data[1].accountId").value(2));
    }

    @Test
    public void testGetAccountList_Empty() throws Exception {
        when(accountService.findAllAccounts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/account"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    public void testSaveAccount_Success() throws Exception {
        AccountVO account = new AccountVO();
        account.setAccountNumber("8762123473");
        account.setAccountType("Corriente");
        account.setInitialBalance(Double.valueOf(15));
        account.setStatus(true);
        account.setCustomerId(2L);

        doNothing().when(accountService).saveAccount(any(AccountVO.class));

        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountNumber\": \"8762123473\", \"accountType\": \"Corriente\", \"initialBalance\": 15, \"status\": \"true\", \"customerId\": 2}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Creado exitosamente"));
    }

}
