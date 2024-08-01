package com.tinqinacademy.comments.core.services;

import com.tinqinacademy.comments.api.models.output.GetCommentOutput;
import com.tinqinacademy.comments.api.operations.getcomments.GetRoomCommentsInput;
import com.tinqinacademy.comments.api.operations.getcomments.GetRoomCommentsOutput;
import com.tinqinacademy.comments.api.operations.leavecomment.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavecomment.LeaveCommentOutput;
import com.tinqinacademy.comments.api.operations.updateowncomment.UpdateOwnCommentInput;
import com.tinqinacademy.comments.api.operations.updateowncomment.UpdateOwnCommentOutput;
import com.tinqinacademy.comments.api.interfaces.HotelService;
import com.tinqinacademy.comments.persistence.models.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final CommentRepository commentRepository;
    private final ConversionService conversionService;

    @Override
    public GetRoomCommentsOutput getRoomComments(GetRoomCommentsInput input) {
        log.info("Start getRoomComments input = {}",input);
        List<GetCommentOutput> comments = Arrays.asList(
                GetCommentOutput.builder()
                        .firstName("Пепи")
                        .lastName("Пупи")
                        .content("content")
                        .id(UUID.randomUUID().toString())
                        .lastEditedDate(LocalDate.now())
                        .lastEditedBy("me")
                        .publishDate(LocalDate.now())
                        .build(),
                GetCommentOutput.builder()
                        .firstName("Пепи")
                        .lastName("Пупи")
                        .content("content")
                        .id(UUID.randomUUID().toString())
                        .lastEditedDate(LocalDate.now())
                        .lastEditedBy("me")
                        .publishDate(LocalDate.now())
                        .build()
        );
        GetRoomCommentsOutput output = GetRoomCommentsOutput.builder()
                .commentOutputList(comments)
                .build();
        log.info("End getRoomComments output = {}",output);
        return output;
    }

    @Override
    public LeaveCommentOutput leaveComment(LeaveCommentInput input) {
        log.info("Start leaveComment input = {}",input);

        Comment comment = conversionService.convert(input, Comment.class);
        if (comment == null) {
            throw new IllegalArgumentException("Comment is null");
        }
        commentRepository.save(comment);


        LeaveCommentOutput output = LeaveCommentOutput.builder()
                .id(UUID.randomUUID().toString())
                .build();
        log.info("End leaveComment output = {}",output);
        return output;
    }

    @Override
    public UpdateOwnCommentOutput updateOwnComment(UpdateOwnCommentInput input) {
        log.info("Start updateOwnComment input = {}",input);
        UpdateOwnCommentOutput output = UpdateOwnCommentOutput.builder()
                .id(UUID.randomUUID().toString())
                .build();
        log.info("Updated lastEditedDate");
        log.info("End updateOwnComment output = {}",output);
        return output;
    }
}
