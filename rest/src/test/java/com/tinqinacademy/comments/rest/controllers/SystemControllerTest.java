package com.tinqinacademy.comments.rest.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.operations.system.updatecomment.UpdateCommentInput;
import com.tinqinacademy.comments.api.restroute.RestApiRoutes;
import com.tinqinacademy.comments.persistence.models.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
public class SystemControllerTest {
    private final MockMvc mvc;
    private final CommentRepository commentRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public SystemControllerTest(MockMvc mvc, CommentRepository commentRepository, ObjectMapper objectMapper) {
        this.mvc = mvc;
        this.commentRepository = commentRepository;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    public void setup() {
        Comment comment = Comment.builder()
                .content("This is a comment")
                .roomId(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .lastEditedBy(UUID.randomUUID())
                .build();
        commentRepository.save(comment);
    }

    @AfterEach
    public void clearDB() {
        commentRepository.deleteAll();
    }

    @Test
    public void testUpdateCommentOk() throws Exception{
        Comment comment = commentRepository.findAll().getFirst();
        UpdateCommentInput input = UpdateCommentInput.builder()
                .roomId(comment.getRoomId().toString())
                .userId(comment.getUserId().toString())
                .content("This is an updated comment")
                .build();
        mvc.perform(put(RestApiRoutes.API_SYSTEM_UPDATE_COMMENT, comment.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateCommentBadRequest() throws Exception{
        Comment comment = commentRepository.findAll().getFirst();
        UpdateCommentInput input = UpdateCommentInput.builder()
                .roomId(comment.getRoomId().toString())
                .userId(comment.getUserId().toString())
                .content("This is an updated comment")
                .build();
        mvc.perform(put(RestApiRoutes.API_SYSTEM_UPDATE_COMMENT, "not-a-uuid")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateCommentNotFound() throws Exception{
        Comment comment = commentRepository.findAll().getFirst();
        UpdateCommentInput input = UpdateCommentInput.builder()
                .roomId(comment.getRoomId().toString())
                .userId(comment.getUserId().toString())
                .content("This is an updated comment")
                .build();
        UUID uuid = UUID.randomUUID();
        while (uuid.equals(comment.getId())) {
            uuid = UUID.randomUUID();
        }
        mvc.perform(put(RestApiRoutes.API_SYSTEM_UPDATE_COMMENT, uuid.toString())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isNotFound());
    }



    @Test
    public void testDeleteCommentOk() throws Exception{
        Comment comment = commentRepository.findAll().getFirst();
        mvc.perform(delete(RestApiRoutes.API_SYSTEM_DELETE_COMMENT, comment.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCommentBadRequest() throws Exception{
        mvc.perform(delete(RestApiRoutes.API_SYSTEM_DELETE_COMMENT, "not-a-uuid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteCommentNotFound() throws Exception{
        Comment comment = commentRepository.findAll().getFirst();
        UUID uuid = UUID.randomUUID();
        while (uuid.equals(comment.getId())) {
            uuid = UUID.randomUUID();
        }
        mvc.perform(delete(RestApiRoutes.API_SYSTEM_DELETE_COMMENT, uuid.toString()))
                .andExpect(status().isNotFound());
    }
}
