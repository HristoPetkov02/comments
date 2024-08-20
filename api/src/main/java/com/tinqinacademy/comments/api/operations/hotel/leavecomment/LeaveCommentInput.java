package com.tinqinacademy.comments.api.operations.hotel.leavecomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class LeaveCommentInput implements OperationInput {
    @JsonIgnore
    private String roomId;

    @NotBlank(message = "User id is required")
    @UUID(message = "User id must be a valid UUID")
    private String userId;


    @NotBlank(message = "Comment content is required")
    private String content;
}
