package com.tinqinacademy.comments.api.operations.leavecomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LeaveCommentOutput implements OperationOutput {
    //изходни данни от оставяне на коментар
    private String id;
}
