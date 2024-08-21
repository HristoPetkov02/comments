package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.system.updatecomment.UpdateCommentInput;
import com.tinqinacademy.comments.core.base.BaseConverter;
import com.tinqinacademy.comments.persistence.models.Comment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateCommentInputToCommentBuilder extends BaseConverter<UpdateCommentInput, Comment.CommentBuilder> {
    @Override
    protected Comment.CommentBuilder convertObject(UpdateCommentInput input) {
        Comment.CommentBuilder output = Comment.builder()
                .id(UUID.fromString(input.getCommentId()))
                .roomId(UUID.fromString(input.getRoomId()))
                .content(input.getContent());
        return output;
    }
}
