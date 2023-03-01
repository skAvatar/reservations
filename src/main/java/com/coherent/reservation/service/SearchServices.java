package com.coherent.reservation.service;

import com.coherent.reservation.model.Reservation;
import com.coherent.reservation.util.DatesServices;
import dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SearchServices extends DatesServices {

    public Optional<Reservation> findRsvByRowNumberAndDates(Set<Reservation> reservations, ReservationDTO reservation) {

        Reservation rsvToSearch = Reservation.builder()
                .roomNumber(reservation.getRoomNumber())
                .reservationDates(stringToLocalDate(reservation.getReservationDates()))
                .build();

        if (findByRoomNumber(reservations, reservation.getRoomNumber())) {
            if (findByDates(reservations, stringToLocalDate(reservation.getReservationDates()))) {
                return Optional.of(rsvToSearch);
            }
        }

        return Optional.empty();

    }

    private boolean findByRoomNumber(Set<Reservation> reservations, Integer roomNumber) {
        Optional<Reservation> rsvByRoomNumber = reservations.stream()
                .filter(rsv -> rsv.getRoomNumber().equals(roomNumber))
                .findFirst();

        return rsvByRoomNumber.isPresent();
    }

    private boolean findByDates(Set<Reservation> reservations, List<LocalDate> inDates) {
        Optional<Reservation> rsvByRoomNumber = Optional.empty();
        for (LocalDate actualDate : inDates) {
            rsvByRoomNumber = reservations.stream()
                    .filter(rsv -> rsv.getReservationDates().contains(actualDate))
                    .findFirst();
        }


        return rsvByRoomNumber.isPresent();
    }

    public Optional<Reservation> findRsvById(Set<Reservation> reservations, Integer id) {
        return reservations.stream()
                .filter(rsv -> rsv.getId().equals(id)).findAny();

    }

    public Optional<List<ReservationDTO>> findAny(Set<Reservation> reservations) {

        if (!reservations.isEmpty()) {
            return Optional.of(reservations.stream()
                    .map(Reservation::getDTO).collect(Collectors.toList()));
        }
        return Optional.empty();

    }
}
