package com.revbookstoreclientapp.exception;

public class NoSellersFoundException extends RuntimeException {
    public NoSellersFoundException(String message) {
        super(message);
    }
}
