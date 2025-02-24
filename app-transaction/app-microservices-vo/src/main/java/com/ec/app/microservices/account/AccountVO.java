package com.ec.app.microservices.account;

import com.ec.app.microservices.customer.CustomerVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {

    private Long accountId;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean status;
    private Long customerId;
    private CustomerVO customer;
}
