package com.coherent.reservation.service;

import com.coherent.reservation.model.Reservation;
import com.coherent.reservation.util.DatesServices;
import dto.ReservationDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SearchServices extends DatesServices {

    public Optional<Reservation> findByRowNumberAndDates(Set<Reservation> reservations, ReservationDTO reservation) {

        Reservation rsvToSearch = Reservation.builder()
                .roomNumber(reservation.getRoomNumber())
                .reservationDates(stringToLocalDate(reservation.getReservationDates()))
                .build();

        if (isRoomTaken(reservations, reservation.getRoomNumber())) {
            if (isDatesForTheRoomTaken(reservations, stringToLocalDate(reservation.getReservationDates()))) {
                return Optional.of(rsvToSearch);
            }
        }
        return Optional.empty();
    }



    private boolean isRoomTaken(Set<Reservation> reservations, Integer roomNumber) {
        Optional<Reservation> rsvByRoomNumber = reservations.stream()
                .filter(rsv -> rsv.getRoomNumber().equals(roomNumber))
                .findFirst();

        return rsvByRoomNumber.isPresent();
    }

    public boolean isSameRoom(ReservationDTO newRsvDTO, Integer roomNumber){
        return newRsvDTO.getRoomNumber().equals(roomNumber);
    }

    public boolean isSameClientName( ReservationDTO newRsvDTO, String clientName){
        return newRsvDTO.getClientFullName().equals(clientName);
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

    public Optional<Reservation> findById(Set<Reservation> reservations, Integer id) {
        return reservations.stream()
                .filter(rsv -> rsv.getId().equals(id))
                .findAny();
    }

    public Optional<List<ReservationDTO>> findAll(Set<Reservation> reservations) {
        Comparator<Reservation> comparatorById = Comparator.comparing(Reservation::getId);
        if (!reservations.isEmpty()) {
            return Optional.of(reservations.stream()
                    .sorted(comparatorById)
                    .map(Reservation::getDTO).collect(Collectors.toList()));
        }
        return Optional.empty();
    }

    public Optional<List<ReservationDTO>> findAllDatesByRoomNumber(Set<Reservation> reservations, Integer roomNumber) {
        return Optional.of(reservations.stream()
                .filter(rsv -> rsv.getRoomNumber().equals(roomNumber))
                .map(Reservation::getDTO)
                .toList());
    }
}
