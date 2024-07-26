package com.tinqinacademy.comments.api.operations.getcomments;

import com.tinqinacademy.comments.api.models.output.GetCommentOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetRoomCommentsOutput {
    //това са изходните данни за извличане на коментарите за дадена стая
    private List<GetCommentOutput> commentOutputList;
}
