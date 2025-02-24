package com.ec.app.microservices.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionVO {
    private Long transactionId;
    private Date date;
    private String transactionType;
    private Double amount;
    private Double balance;
    private Long accountId;
    private Boolean status;

}
