package com.io.financio.config.controlleradvice;

import com.io.financio.domain.exception.SessionNotFoundException;
import com.io.financio.domain.exception.UserAlreadyRegisteredException;
import com.io.financio.domain.exception.UserNotFoundException;
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
}
