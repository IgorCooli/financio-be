package com.io.financio.config.controlleradvice;

import com.io.financio.domain.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<Void> handleSessionNotFoundException(SessionNotFoundException ex) {
        log.error("m=handleSessionNotFoundException, ex={}, msg={}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<Void> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        log.error("m=handleUserAlreadyRegisteredException, ex={}, msg={}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(InvalidUsernameOrPasswordException.class)
    public ResponseEntity<Void> handleInvalidUsernameOrPasswordException(InvalidUsernameOrPasswordException ex) {
        log.error("m=handleInvalidUsernameOrPasswordException, ex={}, msg={}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(DigestPasswordException.class)
    public ResponseEntity<Void> handleDigestPasswordException(DigestPasswordException ex) {
        log.error("m=handleDigestPasswordException, ex={}, msg={}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(AuthTokenNotReceivedException.class)
    public ResponseEntity<Void> handleAuthTokenNotReceivedException(AuthTokenNotReceivedException ex) {
        log.error("m=handleAuthTokenNotReceivedException, ex={}, msg={}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(InvalidTokenDataException.class)
    public ResponseEntity<Void> handleInvalidTokenDataException(InvalidTokenDataException ex) {
        log.error("m=handleInvalidTokenDataException, ex={}, msg={}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(TokenEncryptionException.class)
    public ResponseEntity<Void> handleTokenEncryptionException(TokenEncryptionException ex) {
        log.error("m=handleTokenEncryptionException, ex={}, msg={}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(PasswordHashingException.class)
    public ResponseEntity<Void> handlePasswordHashingException(PasswordHashingException ex) {
        log.error("m=handlePasswordHashingException, ex={}, msg={}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
