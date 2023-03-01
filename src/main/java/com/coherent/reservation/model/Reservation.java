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
        return ReservationDTO.builder()
                .id(this.id)
                .clientFullName(this.clientFullName)
                .roomNumber(this.roomNumber)
                .reservationDates(this.reservationDates.stream()
                        .map(LocalDate::toString)
                        .collect(Collectors.toList())).build();
    }

}
