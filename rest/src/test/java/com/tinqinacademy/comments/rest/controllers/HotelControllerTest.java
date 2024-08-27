package com.tinqinacademy.comments.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.operations.hotel.leavecomment.LeaveCommentInput;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
public class HotelControllerTest {
    private final MockMvc mvc;
    private final CommentRepository commentRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public HotelControllerTest(MockMvc mvc, CommentRepository commentRepository, ObjectMapper objectMapper) {
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
    public void testGetRoomCommentsOk() throws Exception {
        Comment comment = commentRepository.findAll().getFirst();

        mvc.perform(get(RestApiRoutes.API_HOTEL_GET_ROOM_COMMENT, comment.getRoomId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRoomCommentsBadRequest() throws Exception {
        mvc.perform(get(RestApiRoutes.API_HOTEL_GET_ROOM_COMMENT, "not-a-uuid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetRoomCommentsNotFound() throws Exception {
        Comment comment = commentRepository.findAll().getFirst();
        UUID uuid = UUID.randomUUID();
        while (uuid.equals(comment.getRoomId())) {
            uuid = UUID.randomUUID();
        }

        mvc.perform(get(RestApiRoutes.API_HOTEL_GET_ROOM_COMMENT, uuid.toString()))
                .andExpect(status().isNotFound());
    }



    @Test
    public void testLeaveCommentCreated() throws Exception {
        Comment comment = commentRepository.findAll().getFirst();
        UUID uuid = UUID.randomUUID();
        while (uuid.equals(comment.getRoomId())) {
            uuid = UUID.randomUUID();
        }
        LeaveCommentInput input = LeaveCommentInput.builder()
                .userId(UUID.randomUUID().toString())
                .content("This is a comment")
                .build();


        mvc.perform(post(RestApiRoutes.API_HOTEL_LEAVE_COMMENT, uuid.toString())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testLeaveCommentBadRequest() throws Exception {
        LeaveCommentInput input = LeaveCommentInput.builder()
                .userId("not-a-uuid")
                .content("This is a comment")
                .build();

        mvc.perform(post(RestApiRoutes.API_HOTEL_LEAVE_COMMENT, "not-a-uuid")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }
}
