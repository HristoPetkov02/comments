package com.tinqinacademy.comments.api.interfaces;


import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentInput;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentOutput;

public interface SystemService {
    UpdateCommentOutput updateComment(UpdateCommentInput input);
    DeleteCommentOutput deleteComment(DeleteCommentInput input);
}
