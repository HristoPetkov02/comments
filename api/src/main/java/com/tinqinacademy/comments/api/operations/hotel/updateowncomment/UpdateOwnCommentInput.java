package com.tinqinacademy.comments.api.operations.hotel.updateowncomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class UpdateOwnCommentInput implements OperationInput {
    @JsonIgnore
    private String commentId;

    @NotBlank(message = "Content is required")
    private String content;
}
