package com.tinqinacademy.comments.api.operations.updatecomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UpdateCommentOutput implements OperationOutput {
    //изходни данни за обновяване на коментар от админ
    private String id;
}
