package com.tinqinacademy.comments.api.restroute;

public class RestApiRoutes {
    public final static String API = "/api/v1";


    public final static String API_HOTEL = API + "/hotel";
    public final static String API_HOTEL_GET_ROOM_COMMENT = API_HOTEL +"/{roomId}/comment";
    public final static String API_HOTEL_LEAVE_COMMENT = API_HOTEL +"/{roomId}/comment";
    public final static String API_HOTEL_UPDATE_OWN_COMMENT = API_HOTEL +"/comment/{commentId}";



    public final static String API_SYSTEM = API + "/system";
    public final static String API_SYSTEM_UPDATE_COMMENT = API_SYSTEM + "/comment/{commentId}";
    public final static String API_SYSTEM_DELETE_COMMENT = API_SYSTEM + "/system/comment/{commentId}";
}
