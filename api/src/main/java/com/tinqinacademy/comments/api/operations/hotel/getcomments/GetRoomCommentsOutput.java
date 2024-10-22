package com.tinqinacademy.comments.api.operations.hotel.getcomments;

import com.tinqinacademy.comments.api.base.OperationOutput;
import com.tinqinacademy.comments.api.models.output.GetCommentOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetRoomCommentsOutput implements OperationOutput {
    private List<GetCommentOutput> commentOutputList;
}
