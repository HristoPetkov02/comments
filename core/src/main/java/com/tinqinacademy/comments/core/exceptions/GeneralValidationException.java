package com.tinqinacademy.comments.core.exceptions;

import com.tinqinacademy.comments.api.models.Error;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class GeneralValidationException extends RuntimeException{
    private final List<Error> errors;
    private final HttpStatus status;

    public GeneralValidationException(String message, List<Error> errors, HttpStatus status){
        super(message);
        this.errors = errors;
        this.status = status;
    }
}
