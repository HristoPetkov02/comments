package com.tinqinacademy.comments.core.processors.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.interfaces.ErrorHandlerService;
import com.tinqinacademy.comments.api.models.ErrorWrapper;
import com.tinqinacademy.comments.api.models.output.GetCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetRoomCommentsInput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetRoomCommentsOperation;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetRoomCommentsOutput;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class GetRoomCommentsOperationProcessor extends BaseOperationProcessor<GetRoomCommentsInput, GetRoomCommentsOutput> implements GetRoomCommentsOperation {
    private final CommentRepository commentRepository;

    public GetRoomCommentsOperationProcessor(ConversionService conversionService, ObjectMapper mapper, ErrorHandlerService errorHandlerService, Validator validator, CommentRepository commentRepository) {
        super(conversionService, mapper, errorHandlerService, validator);
        this.commentRepository = commentRepository;
    }


    @Override
    public Either<ErrorWrapper, GetRoomCommentsOutput> process(GetRoomCommentsInput input) {
        return Try.of(() -> getRoomComments(input))
                .toEither()
                .mapLeft(errorHandlerService::handle);
    }

    private List<Comment> getComments(String id) {
        List<Comment> comments = commentRepository.findCommentsByRoomId(UUID.fromString(id))
                .orElseThrow(() -> new GeneralApiException(
                        String.format("Room with id %s not found", id),
                        HttpStatus.NOT_FOUND));
        return comments;
    }

    private List<GetCommentOutput> getCommentOutputs(List<Comment> comments) {
        List<GetCommentOutput> commentOutputList = new ArrayList<>();
        for (Comment comment : comments) {
            commentOutputList.add(conversionService.convert(comment, GetCommentOutput.class));
        }
        return commentOutputList;
    }

    private GetRoomCommentsOutput getRoomComments(GetRoomCommentsInput input) {
        logStart(input);

        validateInput(input);

        List<Comment> comments = getComments(input.getRoomId());


        GetRoomCommentsOutput output = GetRoomCommentsOutput.builder()
                .commentOutputList(getCommentOutputs(comments))
                .build();

        logEnd(output);
        return output;
    }
}
