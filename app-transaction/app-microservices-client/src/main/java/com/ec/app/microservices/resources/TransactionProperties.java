package com.ec.app.microservices.resources;

import java.util.MissingResourceException;

public class TransactionProperties {

    public static final TransactionResourceResolver MESSAGE_RESOLVER =
            new TransactionResourceResolver("com.ec.app.microservices.resources.transaction");

    private TransactionProperties() {
    }

    /**
     * Get string
     *
     * @param key Property key
     * @return Property value
     */
    public static String getString(String key) {
        try {
            return MESSAGE_RESOLVER.getString(key);
        } catch (MissingResourceException e) {
            throw new RuntimeException("Clave no encontrada en el ResourceBundle: " + key, e);
        }
    }

}
