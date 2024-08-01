package com.tinqinacademy.comments.core.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.interfaces.ErrorHandlerService;
import com.tinqinacademy.comments.api.models.ErrorWrapper;
import com.tinqinacademy.comments.api.operations.leavecomment.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavecomment.LeaveCommentOperation;
import com.tinqinacademy.comments.api.operations.leavecomment.LeaveCommentOutput;
import com.tinqinacademy.comments.core.base.BaseOperationProcessor;
import com.tinqinacademy.comments.core.exceptions.CommentsApiException;
import com.tinqinacademy.comments.persistence.models.Comment;
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
public class LeaveCommentOperationProcessor extends BaseOperationProcessor<LeaveCommentInput, LeaveCommentOutput> implements LeaveCommentOperation {


    public LeaveCommentOperationProcessor(ConversionService conversionService, ObjectMapper mapper, ErrorHandlerService errorHandlerService, Validator validator, CommentRepository commentRepository) {
        super(conversionService, mapper, errorHandlerService, validator, commentRepository);
    }

    @Override
    public Either<ErrorWrapper, LeaveCommentOutput> process(LeaveCommentInput input) {
        return Try.of(() -> leaveComment(input))
                .toEither()
                .mapLeft(errorHandlerService::handle);
    }

    private LeaveCommentOutput leaveComment(LeaveCommentInput input) {
        logStart(input);

        validateInput(input);

        Comment comment = conversionService.convert(input, Comment.class);
        if (comment == null) {
            throw new CommentsApiException(
                    "Error converting input to Comment",
                    HttpStatus.BAD_REQUEST);
        }
        commentRepository.save(comment);


        LeaveCommentOutput output = LeaveCommentOutput.builder()
                .id(UUID.randomUUID().toString())
                .build();

        logEnd(output);
        return output;
    }
}
