package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentInput;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentOutput;
import com.tinqinacademy.comments.api.interfaces.SystemService;
import com.tinqinacademy.comments.api.restroute.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tinqinacademy.comments.rest.base.BaseController;

@RequiredArgsConstructor
@RestController
public class SystemController extends BaseController{
    private final SystemService systemService;

    @Operation(summary = "Update comment", description = " updates a comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the comment"),
            @ApiResponse(responseCode = "400", description = "Wrong commentId format used"),
            @ApiResponse(responseCode = "404", description = "A comment with this commentId doesn't exist")
    })
    @PutMapping(RestApiRoutes.API_SYSTEM_UPDATE_COMMENT)
    public ResponseEntity<?> updateComment(
            @PathVariable String commentId,
            @Valid @RequestBody UpdateCommentInput input){
        UpdateCommentInput updatedInput = input.toBuilder()
                .commentId(commentId)
                .build();

        UpdateCommentOutput output = systemService.updateComment(updatedInput);
        return new ResponseEntity<>(output, HttpStatus.OK);
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

        DeleteCommentOutput output = systemService.deleteComment(input);
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
