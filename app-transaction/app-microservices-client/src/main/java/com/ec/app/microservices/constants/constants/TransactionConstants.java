package com.ec.app.microservices.constants.constants;

import com.ec.app.microservices.resources.TransactionProperties;

/**
 * Constant value used for the process of transactions
 *
 * @author arobayo
 */

public class TransactionConstants {
    public static final String SEARCHED_MESSAGE = TransactionProperties.
            getString("com.ec.person.search.message");
    public static final String CREATED_MESSAGE = TransactionProperties.
            getString("com.ec.person.created.status.message");
    public static final String UPDATED_MESSAGE = TransactionProperties.
            getString("com.ec.person.updated.status.message");
    public static final String DELETED_MESSAGE = TransactionProperties.
            getString("com.ec.person.deleted.status.message");
    public static final String INSUFFICIENT_FUNDS = TransactionProperties.
            getString("com.ec.person.insufficient.funds.status.message");
    public static final String DEPOSIT_VALUE = TransactionProperties.
            getString("com.ec.procedure.deposit");
    public static final String ACCOUNT_NOT_FOUND = TransactionProperties.
            getString("com.ec.procedure.account.not.found");
    public static final String GENERIC_ERROR_MESSAGE = TransactionProperties.
            getString("com.ec.procedure.error.generic");
    public static final String TRANSACTION_NOT_FOUND = TransactionProperties.
            getString("com.ec.procedure.transaction.not.found");
}
