package com.tinqinacademy.comments.api.operations.system.deletecomment;

import com.tinqinacademy.comments.api.base.OperationInput;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DeleteCommentInput implements OperationInput {
    @UUID(message = "Comment ID must be a valid UUID")
    private String commentId;
}
