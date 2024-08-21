package com.tinqinacademy.comments.api.operations.hotel.updateowncomment;

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
public class UpdateOwnCommentInput implements OperationInput {
    @JsonIgnore
    @UUID(message = "Comment id is not a valid UUID")
    private String commentId;

    @UUID(message = "User id is not a valid UUID")
    private String userId;

    @NotBlank(message = "Content is required")
    private String content;
}
