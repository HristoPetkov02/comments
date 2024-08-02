package com.tinqinacademy.comments.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.interfaces.HotelService;
import com.tinqinacademy.comments.api.operations.getcomments.GetRoomCommentsInput;
import com.tinqinacademy.comments.api.operations.leavecomment.LeaveCommentInput;
import com.tinqinacademy.comments.api.restroute.RestApiRoutes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebMvcTest(HotelController.class)
@ExtendWith(SpringExtension.class)
class HotelControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;


    @MockBean
    private HotelService hotelService;

    @Autowired
    public HotelControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }
/*
    @Test
    void test_getRoomComments_Ok() throws Exception {
        String roomId = "123";
        //GetRoomCommentsInput input = GetRoomCommentsInput.builder()
        //        .roomId(roomId)
        //        .build();

        //GetRoomCommentsOutput expectedOutput = hotelService.getRoomComments(input);
        //when(hotelService.getRoomComments(input)).thenReturn(expectedOutput);

        mockMvc.perform(get(RestApiRoutes.API_HOTEL_GET_ROOM_COMMENT, roomId))
                .andExpect(status().isOk());
    }

    @Test
    void test_getRoomComments_NotFound() throws Exception {
        String roomId = "123";


        mockMvc.perform(get(RestApiRoutes.API_HOTEL_GET_ROOM_COMMENT+"/he", roomId))
                .andExpect(status().isNotFound());
    }

    @Test
    void test_leaveComment_Ok()  throws Exception{
        String roomId = "123";
        LeaveCommentInput input = LeaveCommentInput.builder()
                .lastName("Pupu")
                .firstName("Pepe")
                .content("hehe")
                .build();

        String inputJson = objectMapper.writeValueAsString(input);


        mockMvc.perform(post(RestApiRoutes.API_HOTEL_LEAVE_COMMENT,roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isCreated());
    }

    @Test
    void test_leaveComment_BadRequest()  throws Exception{
        String roomId = "123";
        LeaveCommentInput input = LeaveCommentInput.builder()
                .lastName("")
                .content("hehe")
                .build();

        String inputJson = objectMapper.writeValueAsString(input);


        mockMvc.perform(post(RestApiRoutes.API_HOTEL_LEAVE_COMMENT,roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_leaveComment_NotFound()  throws Exception{
        String roomId = "123";
        LeaveCommentInput input = LeaveCommentInput.builder()
                .lastName("")
                .content("hehe")
                .build();

        String inputJson = objectMapper.writeValueAsString(input);


        mockMvc.perform(post(RestApiRoutes.API_HOTEL_LEAVE_COMMENT+"/hehe",roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isNotFound());
    }*/

}