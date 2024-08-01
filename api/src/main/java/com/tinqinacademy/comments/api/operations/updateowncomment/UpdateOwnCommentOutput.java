package com.tinqinacademy.comments.api.operations.updateowncomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UpdateOwnCommentOutput implements OperationOutput {
    //изходни данни за обновяване на собствен коментар
    private String id;
}
