package com.tinqinacademy.comments.core.services;

import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentInput;
import com.tinqinacademy.comments.api.operations.deletecomment.DeleteCommentOutput;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentInput;
import com.tinqinacademy.comments.api.operations.updatecomment.UpdateCommentOutput;
import com.tinqinacademy.comments.api.interfaces.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {
    @Override
    public UpdateCommentOutput updateComment(UpdateCommentInput input) {
        log.info("Start updateComment input = {}",input);
        UpdateCommentOutput output = UpdateCommentOutput.builder()
                .id(UUID.randomUUID().toString())
                .build();
        log.info("End updateComment output = {}",output);
        return output;
    }

    @Override
    public DeleteCommentOutput deleteComment(DeleteCommentInput input) {
        log.info("Start deleteComment input = {}",input);
        DeleteCommentOutput output = DeleteCommentOutput.builder().build();
        log.info("End deleteComment output = {}",output);
        return output;
    }
}
