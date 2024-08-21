package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.hotel.leavecomment.LeaveCommentInput;
import com.tinqinacademy.comments.core.base.BaseConverter;
import com.tinqinacademy.comments.persistence.models.Comment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LeaveCommentInputToComment extends BaseConverter<LeaveCommentInput, Comment> {
    @Override
    protected Comment convertObject(LeaveCommentInput input) {
        Comment output = Comment.builder()
                .roomId(UUID.fromString(input.getRoomId()))
                .userId(UUID.fromString(input.getUserId()))
                .lastEditedBy(UUID.fromString(input.getUserId()))
                .content(input.getContent())
                .build();

        return output;
    }
}
