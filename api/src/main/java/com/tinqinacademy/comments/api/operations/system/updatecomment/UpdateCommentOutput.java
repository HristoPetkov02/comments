package com.tinqinacademy.comments.api.operations.system.updatecomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UpdateCommentOutput implements OperationOutput {
    private String id;
}
