package com.ec.app.microservices.services;

import com.ec.app.microservices.reports.ReportRequestVO;
import com.ec.app.microservices.reports.ReportResponseVO;
import com.ec.app.microservices.common.ICommonService;
import com.ec.app.microservices.constants.PersonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Service for the generation of reports
 *
 * @author arobayo
 */
@Lazy
@Service
@Transactional
public class ReportService implements IReportService {

    @Lazy
    @Autowired
    private ICustomerService customerService;

    @Lazy
    @Autowired
    private ICommonService commonService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ReportResponseVO> generateAccountReport(ReportRequestVO params) {
        return customerService.generateAccountReport(params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] downloadAccountStatus(ReportRequestVO params) {
        List<ReportResponseVO> report = customerService.generateAccountReport(params);
        if (report.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        report.forEach(data -> {
            SimpleDateFormat formatter = new SimpleDateFormat(PersonConstants.YEAR_MONTH_DAY_PATTERN);
            String dateString = formatter.format(data.getDate());
            data.setDateString(dateString);
        });
        return commonService.generateReport(report, PersonConstants.JRXML_CLIENT_REPORT);
    }
}
