package com.tinqinacademy.comments.core.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.interfaces.ErrorHandlerService;
import com.tinqinacademy.comments.api.models.ErrorWrapper;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentInput;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentOperation;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentOutput;
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

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class UpdateCommentOperationProcessor extends BaseOperationProcessor<UpdateCommentInput, UpdateCommentOutput> implements UpdateCommentOperation {
    public UpdateCommentOperationProcessor(ConversionService conversionService, ObjectMapper mapper, ErrorHandlerService errorHandlerService, Validator validator, CommentRepository commentRepository) {
        super(conversionService, mapper, errorHandlerService, validator, commentRepository);
    }

    @Override
    public Either<ErrorWrapper, UpdateCommentOutput> process(UpdateCommentInput input) {
        return Try.of(() -> updateComment(input))
                .toEither()
                .mapLeft(errorHandlerService::handle);
    }

    private Comment getCurrentComment(UpdateCommentInput input){
        Comment currentComment = commentRepository.findById(UUID.fromString(input.getCommentId()))
                .orElseThrow(() -> new CommentsApiException(
                        String.format("Comment with %s id does not exist",input.getCommentId()),
                HttpStatus.NOT_FOUND));
        return currentComment;
    }

    private UpdateCommentOutput updateComment(UpdateCommentInput input) {
        logStart(input);

        validateInput(input);

        Comment currentComment = getCurrentComment(input);
        Comment updatedComment = Objects.requireNonNull(conversionService.convert(input, Comment.CommentBuilder.class))
                .userId(currentComment.getUserId())
                .lastEditedBy(UUID.randomUUID())
                .build();

        commentRepository.save(updatedComment);

        UpdateCommentOutput output = conversionService.convert(updatedComment,UpdateCommentOutput.class);

        logEnd(output);
        return output;
    }
}
