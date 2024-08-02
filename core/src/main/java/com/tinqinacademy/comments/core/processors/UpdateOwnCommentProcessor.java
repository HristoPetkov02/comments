package com.tinqinacademy.comments.core.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.interfaces.ErrorHandlerService;
import com.tinqinacademy.comments.api.models.ErrorWrapper;
import com.tinqinacademy.comments.api.operations.updateowncomment.UpdateOwnCommentInput;
import com.tinqinacademy.comments.api.operations.updateowncomment.UpdateOwnCommentOperation;
import com.tinqinacademy.comments.api.operations.updateowncomment.UpdateOwnCommentOutput;
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
public class UpdateOwnCommentProcessor extends BaseOperationProcessor<UpdateOwnCommentInput, UpdateOwnCommentOutput> implements UpdateOwnCommentOperation {
    private final CommentRepository commentRepository;

    public UpdateOwnCommentProcessor(ConversionService conversionService, ObjectMapper mapper, ErrorHandlerService errorHandlerService, Validator validator, CommentRepository commentRepository) {
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

    private UpdateOwnCommentOutput updateOwnComment(UpdateOwnCommentInput input) {
        logStart(input);

        validateInput(input);

        Comment currentComment = getCurrentComment(input);
        Comment updatedComment = currentComment.toBuilder()
                .lastEditedBy(currentComment.getId())
                .content(input.getContent())
                .build();

        commentRepository.save(updatedComment);


        UpdateOwnCommentOutput output = conversionService.convert(updatedComment, UpdateOwnCommentOutput.class);

        logEnd(output);
        return output;
    }
}
