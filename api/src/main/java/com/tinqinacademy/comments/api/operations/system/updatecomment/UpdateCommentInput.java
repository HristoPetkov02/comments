package com.tinqinacademy.comments.api.operations.system.updatecomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class UpdateCommentInput implements OperationInput {
    @JsonIgnore
    private String commentId;

    @NotBlank(message = "Room id is required")
    @UUID(message = "Room id must be a valid UUID")
    private String roomId;

    @NotBlank(message = "User id is required")
    @UUID(message = "User id must be a valid UUID")
    private String userId;

    @NotBlank(message = "Comment content is required")
    private String content;
}
