package com.ec.app.microservices.resources;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class TransactionResourceResolver {
    protected final ResourceBundle resourceBundle;

    public TransactionResourceResolver(String bundleRoute) {
        if (System.getProperty("env") == null) {
            this.resourceBundle = ResourceBundle.getBundle(bundleRoute, new Locale("dev", ""));
        } else {
            this.resourceBundle = ResourceBundle.getBundle(bundleRoute, new Locale(System.getProperty("env"), ""));
        }
    }

    public String getString(String key, String... parameters) {
        MessageFormat messageFormat = new MessageFormat(this.resourceBundle.getString(key));
        return messageFormat.format(parameters);
    }

}
