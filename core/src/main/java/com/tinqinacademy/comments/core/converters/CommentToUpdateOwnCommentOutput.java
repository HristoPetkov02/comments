package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.hotel.updateowncomment.UpdateOwnCommentOutput;
import com.tinqinacademy.comments.core.base.BaseConverter;
import com.tinqinacademy.comments.persistence.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentToUpdateOwnCommentOutput extends BaseConverter<Comment, UpdateOwnCommentOutput>{
    @Override
    protected UpdateOwnCommentOutput convertObject(Comment input) {
        UpdateOwnCommentOutput output = UpdateOwnCommentOutput.builder()
                .id(input.getId().toString())
                .build();
        return output;
    }
}
