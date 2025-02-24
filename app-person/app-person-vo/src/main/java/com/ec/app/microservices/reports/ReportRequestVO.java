package com.ec.app.microservices.reports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The request for the generation of reports
 *
 * @author arobayo
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestVO {
    private Date initialDate;
    private Date endDate;
    private Long customerId;

}
