package com.ec.app.microservices.reports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseVO {
    private Date date;
    private String DateString;
    private String name;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean status;
    private String transactionType;
    private Double amount;
    private Double balance;
}
