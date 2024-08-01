package com.tinqinacademy.comments.api.operations.leavecomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class LeaveCommentInput implements OperationInput {
    //входни данни за оставяне на коментар на стая
    @JsonIgnore
    private String roomId;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Comment content is required")
    private String content;
}
