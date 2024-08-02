package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.leavecomment.LeaveCommentOutput;
import com.tinqinacademy.comments.core.base.BaseConverter;
import com.tinqinacademy.comments.persistence.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentToLeaveCommentOutput extends BaseConverter<Comment, LeaveCommentOutput> {
    @Override
    protected LeaveCommentOutput convertObject(Comment input) {
        LeaveCommentOutput output = LeaveCommentOutput.builder()
                .id(input.getId().toString())
                .build();
        return output;
    }
}
