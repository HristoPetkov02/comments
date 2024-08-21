package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.operations.system.deletecomment.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.system.deletecomment.DeleteCommentOperation;
import com.tinqinacademy.comments.api.operations.system.updatecomment.UpdateCommentInput;
import com.tinqinacademy.comments.api.operations.system.updatecomment.UpdateCommentOperation;
import com.tinqinacademy.comments.api.restroute.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tinqinacademy.comments.rest.base.BaseController;

@RequiredArgsConstructor
@RestController
public class SystemController extends BaseController{
    private final DeleteCommentOperation deleteCommentOperation;
    private final UpdateCommentOperation updateCommentOperation;

    @Operation(summary = "Update comment", description = " updates a comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the comment"),
            @ApiResponse(responseCode = "400", description = "Wrong commentId format used"),
            @ApiResponse(responseCode = "404", description = "A comment with this commentId doesn't exist")
    })
    @PutMapping(RestApiRoutes.API_SYSTEM_UPDATE_COMMENT)
    public ResponseEntity<?> updateComment(
            @PathVariable String commentId,
            @RequestBody UpdateCommentInput input){
        UpdateCommentInput updatedInput = input.toBuilder()
                .commentId(commentId)
                .build();

        return handle(updateCommentOperation.process(updatedInput));
    }


    @Operation(summary = "Delete comment", description = " delete a comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete the comment"),
            @ApiResponse(responseCode = "400", description = "Wrong commentId format used"),
            @ApiResponse(responseCode = "404", description = "A comment with this commentId doesn't exist")
    })
    @DeleteMapping(RestApiRoutes.API_SYSTEM_DELETE_COMMENT)
    public ResponseEntity<?> deleteComment(@PathVariable String commentId){
        DeleteCommentInput input = DeleteCommentInput.builder()
                .commentId(commentId)
                .build();

        return handle(deleteCommentOperation.process(input));
    }
}
