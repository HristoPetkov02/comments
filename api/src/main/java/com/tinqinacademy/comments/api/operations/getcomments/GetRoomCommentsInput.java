package com.tinqinacademy.comments.api.operations.getcomments;

import com.tinqinacademy.comments.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetRoomCommentsInput implements OperationInput {
    //това са входните данни за взимане на всички коментари на стая
    private String roomId;
}
