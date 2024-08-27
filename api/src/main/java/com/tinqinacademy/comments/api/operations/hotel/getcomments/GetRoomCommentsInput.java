package com.tinqinacademy.comments.api.operations.hotel.getcomments;

import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetRoomCommentsInput implements OperationInput {
    @UUID(message = "Room id must be a valid UUID")
    @NotBlank(message = "Room id is required")
    private String roomId;
}
