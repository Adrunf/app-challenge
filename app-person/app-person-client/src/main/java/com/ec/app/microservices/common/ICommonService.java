package com.ec.app.microservices.common;

import java.util.List;

/**
 * Interface service used for the generation of reports
 *
 * @author arobayo
 */

public interface ICommonService {

    /**
     * Returns the generated report
     *
     * @param data List<?> The list of transactions for the report
     * @param jrxmlPath The path of the jrxml file.
     * @return byte[] The byte array with the data
     */
    byte[] generateReport(List<?> data, String jrxmlPath);
}
