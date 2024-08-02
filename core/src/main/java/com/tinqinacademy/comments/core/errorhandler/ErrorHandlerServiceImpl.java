package com.tinqinacademy.comments.core.errorhandler;

import com.tinqinacademy.comments.api.interfaces.ErrorHandlerService;
import com.tinqinacademy.comments.api.models.ErrorWrapper;
import com.tinqinacademy.comments.core.exceptions.CommentsApiException;
import com.tinqinacademy.comments.core.exceptions.GeneralValidationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;
import com.tinqinacademy.comments.api.models.Error;

@Component
public class ErrorHandlerServiceImpl implements ErrorHandlerService {
    @Override
    public ErrorWrapper handle(Throwable throwable) {
        return Match(throwable).of(
                Case($(instanceOf(CommentsApiException.class)), this::handleCommentsApiException),
                Case($(instanceOf(GeneralValidationException.class)), this::handleGenericValidationException),
                Case($(), this::handleDefaultException)
        );
    }

    private ErrorWrapper handleCommentsApiException(CommentsApiException ex) {
        return ErrorWrapper.builder()
                .errorCode(ex.getHttpStatus())
                .errors(
                        List.of(
                                Error.builder()
                                        .message(ex.getMessage())
                                        .build()))
                .build();
    }

    private ErrorWrapper handleGenericValidationException(GeneralValidationException ex) {
        return ErrorWrapper.builder()
                .errorCode(ex.getStatus())
                .errors(ex.getErrors())
                .build();
    }


    private ErrorWrapper handleDefaultException(Throwable ex) {
        return ErrorWrapper.builder()
                .errorCode(HttpStatus.BAD_REQUEST)
                .errors(
                        List.of(
                                Error.builder()
                                        .message(ex.getMessage())
                                        .build()))
                .build();
    }
}
