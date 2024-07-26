package com.tinqinacademy.comments.api.operations.getcomments;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GetRoomCommentsInput {
    //това са входните данни за взимане на всички коментари на стая
    private String roomId;
}
