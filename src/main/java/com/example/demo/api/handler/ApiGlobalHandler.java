package com.example.demo.api.handler;

import com.example.demo.application.exception.DuplicateUsernameException;
import com.example.demo.application.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiGlobalHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handler(UserNotFoundException x) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(x.getMessage()));
    }

    @ExceptionHandler(value = DuplicateUsernameException.class)
    public ResponseEntity<ErrorResponse> handler(DuplicateUsernameException x) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ErrorResponse.of(x.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handler(Exception x) {
        log.error("ERROR", x);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(x.getMessage()));
    }
}
