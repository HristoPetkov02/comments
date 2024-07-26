package com.tinqinacademy.comments.api.models.output;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GetCommentOutput {
    private String id;
    private String firstName;
    private String lastName;
    private String content;
    private LocalDate publishDate;
    private LocalDate lastEditedDate;
    private String lastEditedBy;
}
