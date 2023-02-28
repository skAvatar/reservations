package com.coherent.reservation.service;

import com.coherent.reservation.model.Reservation;
import com.coherent.reservation.repository.ReservationRepository;
import dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServices {

    @Autowired
    HandlerDatesServices hdlDatesServices;

    @Autowired
    ReservationRepository reservationRepo;

    public void add(ReservationDTO newReservation){
        reservationRepo.getReservations()
                .add( Reservation.builder()
                        .id(newReservation.getId())
                        .clientFullName(newReservation.getClientFullName())
                        .roomNumber(newReservation.getRoomNumber())
                        .reservationDates(hdlDatesServices.stringToLocalDate(newReservation.getReservationDates()))
                        .build());

    }

    public ReservationDTO getOne(Integer id) {
        return reservationRepo.getReservations().stream()
                .filter( rsv -> rsv.getId().equals(id))
                .findFirst().get().getDTO();
    }

    public List<ReservationDTO> getAll() {
        return reservationRepo.getReservations().stream()
                .map(Reservation::getDTO).collect(Collectors.toList());
    }
}
