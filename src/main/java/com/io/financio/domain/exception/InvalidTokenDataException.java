package com.io.financio.domain.exception;

public class InvalidTokenDataException extends RuntimeException {
    public InvalidTokenDataException(String message) {
        super(message);
    }
}
