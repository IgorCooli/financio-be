package com.io.financio.domain.exception;

public class PasswordHashingException extends RuntimeException {
    public PasswordHashingException(String message) {
        super(message);
    }
}
