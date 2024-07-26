package com.tinqinacademy.comments.api.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Error {
    private String field;
    private String message;
}
