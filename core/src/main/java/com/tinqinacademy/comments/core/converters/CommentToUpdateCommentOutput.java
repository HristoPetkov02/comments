package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.system.updatecomment.UpdateCommentOutput;
import com.tinqinacademy.comments.core.base.BaseConverter;
import com.tinqinacademy.comments.persistence.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentToUpdateCommentOutput extends BaseConverter<Comment, UpdateCommentOutput> {

    @Override
    protected UpdateCommentOutput convertObject(Comment input) {
        UpdateCommentOutput output = UpdateCommentOutput.builder()
                .id(input.getId().toString())
                .build();
        return output;
    }
}
