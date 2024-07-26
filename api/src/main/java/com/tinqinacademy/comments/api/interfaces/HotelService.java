package com.tinqinacademy.comments.api.interfaces;

import com.tinqinacademy.comments.api.operations.getcomments.GetRoomCommentsInput;
import com.tinqinacademy.comments.api.operations.getcomments.GetRoomCommentsOutput;
import com.tinqinacademy.comments.api.operations.leavecomment.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.leavecomment.LeaveCommentOutput;
import com.tinqinacademy.comments.api.operations.updateowncomment.UpdateOwnCommentInput;
import com.tinqinacademy.comments.api.operations.updateowncomment.UpdateOwnCommentOutput;

public interface HotelService {
    GetRoomCommentsOutput getRoomComments(GetRoomCommentsInput input);
    LeaveCommentOutput leaveComment(LeaveCommentInput input);
    UpdateOwnCommentOutput updateOwnComment(UpdateOwnCommentInput input);
}
