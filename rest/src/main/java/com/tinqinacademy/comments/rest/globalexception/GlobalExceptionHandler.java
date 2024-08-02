package com.tinqinacademy.comments.rest.globalexception;

import com.tinqinacademy.comments.api.models.ErrorWrapper;
import com.tinqinacademy.comments.api.interfaces.ExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
    private final ExceptionService exceptionService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorWrapper errors = exceptionService.handleException(ex);
        return new ResponseEntity<>(errors.getErrors(), errors.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return new ResponseEntity<>(
                String.format("An error occurred: %s\n Clause: %s", ex.getMessage(), ex.getCause()), HttpStatus.BAD_REQUEST);
    }

}
