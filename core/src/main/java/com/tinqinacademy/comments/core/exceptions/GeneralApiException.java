package com.tinqinacademy.comments.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralApiException extends RuntimeException{
    private final HttpStatus httpStatus;
    public GeneralApiException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
