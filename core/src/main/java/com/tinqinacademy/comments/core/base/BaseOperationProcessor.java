package com.tinqinacademy.comments.core.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.base.OperationInput;
import com.tinqinacademy.comments.api.base.OperationOutput;
import com.tinqinacademy.comments.api.interfaces.ErrorHandlerService;
import com.tinqinacademy.comments.api.models.Error;
import com.tinqinacademy.comments.core.exceptions.GeneralValidationException;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseOperationProcessor<I extends OperationInput, O extends OperationOutput>{
    protected final ConversionService conversionService;
    protected final ObjectMapper mapper;
    protected final ErrorHandlerService errorHandlerService;
    protected final Validator validator;
    protected final CommentRepository commentRepository;

    protected void validateInput(I input){
        Set<ConstraintViolation<I>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            List<Error> errors = violations.stream()
                    .map(violation -> Error.builder()
                            .field(violation.getPropertyPath().toString())
                            .message(violation.getMessage())
                            .build()).collect(Collectors.toList());
            throw new GeneralValidationException(
                    "Error validating input",
                    errors,
                    HttpStatus.BAD_REQUEST);
        }
    }

    protected void logStart(I input){
        log.info(
                String.format("Start %s input = %s",
                        this.getClass().getSimpleName(),
                        input));
    }

    protected  void logEnd(O output){
        log.info(
                String.format("End %s output = %s",
                        this.getClass().getSimpleName(),
                        output));
    }


}
