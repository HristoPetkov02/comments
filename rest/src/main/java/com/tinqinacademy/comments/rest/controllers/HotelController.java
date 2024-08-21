package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetRoomCommentsInput;
import com.tinqinacademy.comments.api.operations.hotel.getcomments.GetRoomCommentsOperation;
import com.tinqinacademy.comments.api.operations.hotel.leavecomment.LeaveCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.leavecomment.LeaveCommentOperation;
import com.tinqinacademy.comments.api.operations.hotel.updateowncomment.UpdateOwnCommentInput;
import com.tinqinacademy.comments.api.operations.hotel.updateowncomment.UpdateOwnCommentOperation;
import com.tinqinacademy.comments.api.restroute.RestApiRoutes;
import com.tinqinacademy.comments.rest.base.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class HotelController extends BaseController {
    private final LeaveCommentOperation leaveCommentOperation;
    private final GetRoomCommentsOperation getRoomCommentsOperation;
    private final UpdateOwnCommentOperation updateOwnCommentOperation;

    @Operation(summary = "Get all comments", description = " gets all comments of a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully displayed all comments"),
            @ApiResponse(responseCode = "400", description = "Wrong roomId format used"),
            @ApiResponse(responseCode = "404", description = "A room with this roomId doesn't exist")
    })
    @GetMapping(RestApiRoutes.API_HOTEL_GET_ROOM_COMMENT)
    public ResponseEntity<?> getRoomComments(@PathVariable String roomId) {
        GetRoomCommentsInput input = GetRoomCommentsInput.builder()
                .roomId(roomId)
                .build();
        return handle(getRoomCommentsOperation.process(input));
    }

    @Operation(summary = "Leave a comment", description = " leaves a comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a comment"),
            @ApiResponse(responseCode = "400", description = "Wrong roomId format used"),
            @ApiResponse(responseCode = "404", description = "A room with this roomId doesn't exist")
    })
    @PostMapping(RestApiRoutes.API_HOTEL_LEAVE_COMMENT)
    public ResponseEntity<?> leaveComment(
            @PathVariable String roomId,
            @RequestBody LeaveCommentInput input) {
        LeaveCommentInput updatedInput = input.toBuilder()
                .roomId(roomId)
                .build();

        return handleWithCode(leaveCommentOperation.process(updatedInput), HttpStatus.CREATED);
    }

    @Operation(summary = "Update own comment", description = " updates your own comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the comment"),
            @ApiResponse(responseCode = "400", description = "Wrong commentId format used"),
            @ApiResponse(responseCode = "404", description = "A comment with this commentId doesn't exist")
    })
    @PatchMapping(RestApiRoutes.API_HOTEL_UPDATE_OWN_COMMENT)
    public ResponseEntity<?> updateOwnComment(
            @PathVariable String commentId,
            @RequestBody UpdateOwnCommentInput input) {
        UpdateOwnCommentInput updatedInput = input.toBuilder()
                .commentId(commentId)
                .build();
        return handle(updateOwnCommentOperation.process(updatedInput));
    }

}
