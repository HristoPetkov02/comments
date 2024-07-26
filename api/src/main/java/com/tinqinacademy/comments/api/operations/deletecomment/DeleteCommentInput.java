package com.tinqinacademy.comments.api.operations.deletecomment;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DeleteCommentInput {
    //входни данни за изтриване на коментар
    private String commentID;
}
