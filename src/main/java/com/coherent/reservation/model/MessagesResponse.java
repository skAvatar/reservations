package com.coherent.reservation.model;

public enum MessagesResponse {
    RSV_CREATED("Reservation created successfully"),
    RSV_NOT_FOUND("Reservation not found"),

    RSV_NOT_ANY("There are no reservations"),


    ROOM_TAKEN("Can not do Reservation. This room for this day is taken ");



    public final String message;

    MessagesResponse(String message) {
        this.message = message;
    }

    public  String getMessage(){
        return message;
    }

}
