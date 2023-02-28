package com.coherent.reservation.model;

import dto.ReservationDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class Reservation {

    Integer id;
    String clientFullName;
    Integer roomNumber;
    List<LocalDate> reservationDates;

    public ReservationDTO getDTO() {
        ReservationDTO thisReservationDTO = new ReservationDTO();
        thisReservationDTO.setId(this.id);
        thisReservationDTO.setClientFullName(this.clientFullName);
        thisReservationDTO.setRoomNumber(this.roomNumber);
        thisReservationDTO.setReservationDates(this.reservationDates.stream()
                .map(LocalDate::toString)
                .collect(Collectors.toList()));
        return thisReservationDTO;
    }

}
