package com.tinqinacademy.comments.api.operations.updatecomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class UpdateCommentInput {
    //входни данни за обновяване на коментар от админ
    @JsonIgnore
    private String commentId;

    @NotBlank(message = "Room No is required")
    private String roomNo;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Comment content is required")
    private String content;
}
