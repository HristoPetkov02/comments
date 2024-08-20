package com.tinqinacademy.comments.api.operations.hotel.leavecomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LeaveCommentOutput implements OperationOutput {
    private String id;
}
