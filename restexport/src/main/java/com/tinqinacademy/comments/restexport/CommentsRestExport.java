package com.tinqinacademy.comments.restexport;

import com.tinqinacademy.comments.api.operations.system.deletecomment.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetRoomCommentsOutput;
import com.tinqinacademy.comments.api.operations.hotel.leavecomment.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.leavecomment.LeaveCommentOutput;
import com.tinqinacademy.comments.api.operations.system.updatecomment.UpdateCommentInput;
import com.tinqinacademy.comments.api.operations.system.updatecomment.UpdateCommentOutput;
import com.tinqinacademy.comments.api.operations.hotel.updateowncomment.UpdateOwnCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.updateowncomment.UpdateOwnCommentOutput;
import com.tinqinacademy.comments.api.restroute.RestApiRoutes;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommentsRestExport {
    //hotel controller
    @RequestLine("GET "+ RestApiRoutes.API_HOTEL_GET_ROOM_COMMENT)
    @Headers("Content-Type: application/json")
    GetRoomCommentsOutput getRoomComment(@Param("roomId") String roomId);


    @RequestLine("POST " + RestApiRoutes.API_HOTEL_LEAVE_COMMENT)
    @Headers("Content-Type: application/json")
    LeaveCommentOutput leaveComment(@Param("roomId") String roomId, @RequestBody LeaveCommentInput input);

    @RequestLine("PATCH " + RestApiRoutes.API_HOTEL_UPDATE_OWN_COMMENT)
    @Headers("Content-Type: application/json")
    UpdateOwnCommentOutput updateOwnComment(@Param("commentId") String commentId, @RequestBody UpdateOwnCommentInput input);

    //system controller
    @RequestLine("PUT " + RestApiRoutes.API_SYSTEM_UPDATE_COMMENT)
    @Headers("Content-Type: application/json")
    UpdateCommentOutput updateComment(@Param("commentId") String commentId, @RequestBody UpdateCommentInput input);

    @RequestLine("DELETE " + RestApiRoutes.API_SYSTEM_DELETE_COMMENT)
    @Headers("Content-Type: application/json")
    DeleteCommentOutput deleteComment(@Param("commentId") String commentId);
}
