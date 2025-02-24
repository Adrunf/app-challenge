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
public class AccountStatusRequestVO {
    private Date initialDate;
    private Date endDate;
    private Long customerId;

}
