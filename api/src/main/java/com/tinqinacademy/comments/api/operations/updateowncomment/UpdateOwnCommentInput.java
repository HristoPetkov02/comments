package com.tinqinacademy.comments.api.operations.updateowncomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class UpdateOwnCommentInput {
    //входни данни за обновяване на собствен коментар
    @JsonIgnore
    private String commentId;

    @NotBlank(message = "Content is required")
    private String content;
}
