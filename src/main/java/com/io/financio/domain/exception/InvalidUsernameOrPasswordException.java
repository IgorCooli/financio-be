package com.io.financio.domain.exception;

public class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException(String msg) {
        super(msg);
    }
}
