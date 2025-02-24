package com.ec.app.microservices.services;

import com.ec.app.microservices.reports.ReportRequestVO;
import com.ec.app.microservices.reports.ReportResponseVO;

import java.util.List;

/**
 * Interface service for the generation of reports
 *
 * @author arobayo
 */
public interface IReportService {

    /**
     * Return account report values
     *
     * @param params The account request to generate the actual status
     * @return List<ReportResponseVO>
     */
    List<ReportResponseVO> generateAccountReport(ReportRequestVO params);

    /**
     * Download the account report
     *
     * @param params The account request to generate the actual status
     * @return byte[]
     */
    byte[] downloadAccountStatus(ReportRequestVO params);

}
