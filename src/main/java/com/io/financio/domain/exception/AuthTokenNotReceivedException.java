package com.io.financio.domain.exception;

public class AuthTokenNotReceivedException extends RuntimeException {
    public AuthTokenNotReceivedException(String message) {
        super(message);
    }
}
