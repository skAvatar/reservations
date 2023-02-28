package com.coherent.reservation.model;

import java.time.LocalDate;
import java.util.List;

public class Reservation {

    Integer id;
    String clientFullName;
    Integer roomNumber;
    List<LocalDate> reservationDates;

}
