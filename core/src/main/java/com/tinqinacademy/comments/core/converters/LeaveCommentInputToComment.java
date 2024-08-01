package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.leavecomment.LeaveCommentInput;
import com.tinqinacademy.comments.persistence.models.Comment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LeaveCommentInputToComment extends BaseConverter<LeaveCommentInput, Comment>{
    @Override
    protected Comment convertObject(LeaveCommentInput input) {
        UUID user = UUID.randomUUID(); //TODO: change to real user id when the communication between services is implemented
        Comment output = Comment.builder()
                .roomId(UUID.fromString(input.getRoomId()))
                .userId(user)
                .lastEditedBy(user)
                .content(input.getContent())
                .build();

        return output;
    }
}
