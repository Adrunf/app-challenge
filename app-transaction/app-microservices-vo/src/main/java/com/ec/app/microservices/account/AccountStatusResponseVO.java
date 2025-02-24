package com.ec.app.microservices.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatusResponseVO {
    private Date date;
    private String name;
    private String accountNumber;
    private Double initialBalance;
    private String status;
    private Double balance;
}
