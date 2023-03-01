package com.coherent.reservation.service;

import com.coherent.reservation.model.MessagesResponse;
import com.coherent.reservation.model.Reservation;
import com.coherent.reservation.repository.ReservationRepository;
import dto.MessageResponseDTO;
import dto.ReservationDTO;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServices {

    @Autowired
    SearchServices searchServices;

    @Autowired
    ReservationRepository reservationRepo;

    private BigInteger reservationId = BigInteger.ONE ;

    public MessageResponseDTO add(ReservationDTO newReservation) {
        if (searchServices.findRsvByRowNumberAndDates(reservationRepo.getReservations(), newReservation).isPresent()) {
            return MessageResponseDTO.builder()
                    .codeHttp(HttpStatus.SC_NO_CONTENT)
                    .message(MessagesResponse.ROOM_TAKEN.message)
                    .build();
        }

        reservationRepo.getReservations()
                .add(Reservation.builder()
                        .id(reservationId.intValue())
                        .clientFullName(newReservation.getClientFullName())
                        .roomNumber(newReservation.getRoomNumber())
                        .reservationDates(searchServices.stringToLocalDate(newReservation.getReservationDates()))
                        .build());

        reservationId = reservationId.add(BigInteger.ONE);

        return MessageResponseDTO.builder()
                .codeHttp(HttpStatus.SC_OK)
                .message(MessagesResponse.RSV_CREATED.message)
                .build();

    }

    public MessageResponseDTO getOne(Integer id) {
        Optional<Reservation> rsvById = searchServices.findRsvById(reservationRepo.getReservations(), id);
        if (rsvById.isPresent()) {
            return MessageResponseDTO.builder()
                    .codeHttp(HttpStatus.SC_OK)
                    .reservation(rsvById.get().getDTO())
                    .build();
        } else {
            return MessageResponseDTO.builder()
                    .codeHttp(HttpStatus.SC_NO_CONTENT)
                    .message(MessagesResponse.RSV_NOT_FOUND.getMessage())
                    .build();
        }
    }

    public MessageResponseDTO getAll() {

        Optional<List<ReservationDTO>> rsvAny = searchServices.findAny(reservationRepo.getReservations());
        if (rsvAny.isPresent()) {
            return MessageResponseDTO.builder()
                    .codeHttp(HttpStatus.SC_OK)
                    .reservations(rsvAny.get())
                    .build();
        }
        return MessageResponseDTO.builder()
                .codeHttp(HttpStatus.SC_NO_CONTENT)
                .message(MessagesResponse.RSV_NOT_ANY.message)
                .build();
    }
}
