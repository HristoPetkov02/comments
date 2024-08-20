package com.tinqinacademy.comments.api.operations.system.deletecomment;

import com.tinqinacademy.comments.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DeleteCommentInput implements OperationInput {
    private String commentId;
}
