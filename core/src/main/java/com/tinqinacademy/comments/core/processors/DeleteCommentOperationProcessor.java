package com.tinqinacademy.comments.core.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.interfaces.ErrorHandlerService;
import com.tinqinacademy.comments.api.models.ErrorWrapper;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentOperation;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentOutput;
import com.tinqinacademy.comments.core.base.BaseOperationProcessor;
import com.tinqinacademy.comments.core.exceptions.GeneralApiException;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class DeleteCommentOperationProcessor extends BaseOperationProcessor<DeleteCommentInput, DeleteCommentOutput> implements DeleteCommentOperation {
    private final CommentRepository commentRepository;

    public DeleteCommentOperationProcessor(ConversionService conversionService, ObjectMapper mapper, ErrorHandlerService errorHandlerService, Validator validator, CommentRepository commentRepository) {
        super(conversionService, mapper, errorHandlerService, validator);
        this.commentRepository = commentRepository;
    }


    @Override
    public Either<ErrorWrapper, DeleteCommentOutput> process(DeleteCommentInput input) {
        return Try.of(() -> deleteComment(input))
                .toEither()
                .mapLeft(errorHandlerService::handle);
    }

    private void checkIfCommentExist(DeleteCommentInput input){
        commentRepository.findById(UUID.fromString(input.getCommentId()))
                .orElseThrow(() -> new GeneralApiException(
                        String.format("Comment with %s id doesn't exist",input.getCommentId()),
                        HttpStatus.NOT_FOUND));
    }

    private DeleteCommentOutput deleteComment(DeleteCommentInput input) {
        logStart(input);

        validateInput(input);

        checkIfCommentExist(input);

        commentRepository.deleteById(UUID.fromString(input.getCommentId()));

        DeleteCommentOutput output = DeleteCommentOutput.builder().build();
        logEnd(output);
        return output;
    }
}
