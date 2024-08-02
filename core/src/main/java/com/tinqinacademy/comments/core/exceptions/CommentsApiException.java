package com.tinqinacademy.comments.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommentsApiException extends RuntimeException{
    private final HttpStatus httpStatus;
    public CommentsApiException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
}
