package com.vendingmachine.services;

public class NoInventoryException extends Exception {

    public NoInventoryException(String message) {
        super(message);

    }

    public NoInventoryException(String message, Throwable cause) {
        super(message, cause);

    }

}
