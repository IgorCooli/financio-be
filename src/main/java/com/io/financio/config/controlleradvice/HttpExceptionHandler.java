package com.io.financio.config.controlleradvice;

import com.io.financio.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<Void> handleSessionNotFoundException(SessionNotFoundException ex) {
        //TODO logs
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<Void> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        //TODO logs
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Void> handleUserNotFoundException(UserNotFoundException ex) {
        //TODO logs
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(DigestPassorException.class)
    public ResponseEntity<Void> handleDigestPassorException(DigestPassorException ex) {
        //TODO logs
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(AuthTokenNotReceivedException.class)
    public ResponseEntity<Void> handleAuthTokenNotReceivedException(AuthTokenNotReceivedException ex) {
        //TODO logs
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(InvalidTokenDataException.class)
    public ResponseEntity<Void> handleInvalidTokenDataException(InvalidTokenDataException ex) {
        //TODO logs
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(TokenEncryptionException.class)
    public ResponseEntity<Void> handleTokenEncryptionException(TokenEncryptionException ex) {
        //TODO logs
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(PasswordHashingException.class)
    public ResponseEntity<Void> handlePasswordHashingException(PasswordHashingException ex) {
        //TODO logs
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
