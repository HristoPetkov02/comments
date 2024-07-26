package com.tinqinacademy.comments.api.models;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ErrorWrapper {
    private List<Error> errors;
    private HttpStatus errorCode;
}
