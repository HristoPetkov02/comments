package com.tinqinacademy.comments.core.processors.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.interfaces.ErrorHandlerService;
import com.tinqinacademy.comments.api.models.ErrorWrapper;
import com.tinqinacademy.comments.api.operations.hotel.updateowncomment.UpdateOwnCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.updateowncomment.UpdateOwnCommentOperation;
import com.tinqinacademy.comments.api.operations.hotel.updateowncomment.UpdateOwnCommentOutput;
import com.tinqinacademy.comments.core.base.BaseOperationProcessor;
import com.tinqinacademy.comments.core.exceptions.GeneralApiException;
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
public class UpdateOwnCommentOperationProcessor extends BaseOperationProcessor<UpdateOwnCommentInput, UpdateOwnCommentOutput> implements UpdateOwnCommentOperation {
    private final CommentRepository commentRepository;

    public UpdateOwnCommentOperationProcessor(ConversionService conversionService, ObjectMapper mapper, ErrorHandlerService errorHandlerService, Validator validator, CommentRepository commentRepository) {
        super(conversionService, mapper, errorHandlerService, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<ErrorWrapper, UpdateOwnCommentOutput> process(UpdateOwnCommentInput input) {
        return Try.of(() -> updateOwnComment(input))
                .toEither()
                .mapLeft(errorHandlerService::handle);
    }

    private Comment getCurrentComment(UpdateOwnCommentInput input){
        Comment currentComment = commentRepository.findById(UUID.fromString(input.getCommentId()))
                .orElseThrow(() -> new GeneralApiException(
                        String.format("Comment with id %s not found", input.getCommentId()),
                        HttpStatus.NOT_FOUND));
        return currentComment;
    }

    private void checkIfUserIsOwner(UpdateOwnCommentInput input, Comment currentComment){
        if (!currentComment.getUserId().equals(UUID.fromString(input.getUserId()))) {
            throw new GeneralApiException("You are not the owner of this comment", HttpStatus.BAD_REQUEST);
        }
    }

    private UpdateOwnCommentOutput updateOwnComment(UpdateOwnCommentInput input) {
        logStart(input);

        validateInput(input);

        Comment currentComment = getCurrentComment(input);

        checkIfUserIsOwner(input, currentComment);
        Comment updatedComment = currentComment.toBuilder()
                .lastEditedBy(UUID.fromString(input.getUserId()))
                .content(input.getContent())
                .build();

        commentRepository.save(updatedComment);


        UpdateOwnCommentOutput output = conversionService.convert(updatedComment, UpdateOwnCommentOutput.class);

        logEnd(output);
        return output;
    }
}
