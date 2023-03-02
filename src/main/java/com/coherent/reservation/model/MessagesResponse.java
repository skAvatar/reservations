package com.coherent.reservation.model;

public enum MessagesResponse {
    RSV_CREATED("Reservation created successfully."),
    RSV_NOT_FOUND("Reservation not found."),
    RSV_DELETED("Reservation deleted successfully."),
    RSV_NOT_ANY("There are no reservations."),
    ROOM_FREE("This room have no reservations."),
    ROOM_TAKEN("Can not do Reservation. This room for this day is taken."),

    DATES_BAD_VALUE("Wrong values for reservation_dates, check HELP.md documentation");

    public final String message;

    MessagesResponse(String message) {
        this.message = message;
    }

    public  String getMessage(){
        return message;
    }

}
