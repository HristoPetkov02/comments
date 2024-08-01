package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.models.output.GetCommentOutput;
import com.tinqinacademy.comments.core.base.BaseConverter;
import com.tinqinacademy.comments.persistence.models.Comment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CommentToGetCommentOutput extends BaseConverter<Comment, GetCommentOutput>{
    @Override
    protected GetCommentOutput convertObject(Comment input) {
        GetCommentOutput output = GetCommentOutput.builder()
                .id(input.getId().toString())
                .firstName("firstName")//TODO: when the communication between services is establish change the values
                .lastName("lastName")
                .content(input.getContent())
                .lastEditedBy(input.getLastEditedBy().toString())
                .lastEditedDate(LocalDate.from(input.getLastEditedDate()))
                .publishDate(LocalDate.from(input.getPublishDate()))
                .build();
        return output;
    }
}
