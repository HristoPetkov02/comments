package com.tinqinacademy.comments.api.base;


import com.tinqinacademy.comments.api.models.ErrorWrapper;
import io.vavr.control.Either;

public interface OperationProcessor <I extends OperationInput, O extends OperationOutput>{
    Either<ErrorWrapper,O> process(I input);
}
