package com.ec.app.microservices.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The customer DTO
 *
 * @author arobayo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVO {
    private Long customerId;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private Boolean status;
}
