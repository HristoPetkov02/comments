package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.updateowncomment.UpdateOwnCommentOutput;
import com.tinqinacademy.comments.core.base.BaseConverter;
import com.tinqinacademy.comments.persistence.models.Comment;

public class CommentToUpdateOwnCommentOutput extends BaseConverter<Comment, UpdateOwnCommentOutput>{
    @Override
    protected UpdateOwnCommentOutput convertObject(Comment input) {
        UpdateOwnCommentOutput output = UpdateOwnCommentOutput.builder()
                .id(input.getId().toString())
                .build();
        return output;
    }
}
