package com.tinqinacademy.comments.api.operations.hotel.updateowncomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UpdateOwnCommentOutput implements OperationOutput {
    private String id;
}
