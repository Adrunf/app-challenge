package com.ec.app.microservices.constants;

import com.ec.app.microservices.resources.PersonProperties;

/**
 * Constants used for the CRUD Person
 *
 * @author arobayo
 */

public class PersonConstants {
    public static final String SEARCHED_MESSAGE = PersonProperties.
            getString("com.ec.person.search.message");
    public static final String CREATED_MESSAGE = PersonProperties.
            getString("com.ec.person.created.message");
    public static final String DELETED_MESSAGE = PersonProperties.
            getString("com.ec.person.deleted.message");
    public static final String UPDATED_MESSAGE = PersonProperties.
            getString("com.ec.person.updated.message");
    public static final String REPORT_MESSAGE = PersonProperties.
            getString("com.ec.person.report.message");
    public static final String NOT_FOUND_MESSAGE = PersonProperties.
            getString("com.ec.person.error.not.found");
    public static final String GENERIC_ERROR_MESSAGE = PersonProperties.
            getString("com.ec.person.error.generic");
    public static final String REPORT_ERROR_MESSAGE = PersonProperties.
            getString("com.ec.person.report.error.message");
    public static final String JRXML_CLIENT_REPORT = PersonProperties.
            getString("com.ec.person.jrxml.report.name");
    public static final String YEAR_MONTH_DAY_PATTERN = PersonProperties.
            getString("com.ec.person.pattern.date");
}
