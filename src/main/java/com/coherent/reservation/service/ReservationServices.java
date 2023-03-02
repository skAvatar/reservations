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

    private BigInteger reservationId = BigInteger.ONE;

    public MessageResponseDTO add(ReservationDTO newReservation) {
        if (searchServices.isDatesValid(searchServices.stringToLocalDate(newReservation.getReservationDates()))) {
            if (searchServices.findByRowNumberAndDates(reservationRepo.getReservations(), newReservation).isPresent()) {
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

        return MessageResponseDTO.builder()
                .codeHttp(HttpStatus.SC_NO_CONTENT)
                .message(MessagesResponse.RSV_NOT_FOUND.message)
                .build();

    }

    public MessageResponseDTO getOne(Integer id) {
        Optional<Reservation> rsvById = searchServices.findById(reservationRepo.getReservations(), id);
        if (rsvById.isPresent()) {
            return MessageResponseDTO.builder()
                    .codeHttp(HttpStatus.SC_OK)
                    .reservation(rsvById.get().getDTO())
                    .build();
        }

        return MessageResponseDTO.builder()
                .codeHttp(HttpStatus.SC_NO_CONTENT)
                .message(MessagesResponse.RSV_NOT_FOUND.getMessage())
                .build();
    }

    public MessageResponseDTO getAll() {
        Optional<List<ReservationDTO>> rsvAny = searchServices.findAll(reservationRepo.getReservations());
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

    public MessageResponseDTO put(Integer id, ReservationDTO updateReservation) {

        if (searchServices.isDatesValid(searchServices.stringToLocalDate(updateReservation.getReservationDates()))) {
            Optional<Reservation> rsvById = searchServices.findById(reservationRepo.getReservations(), id);
            if (rsvById.isPresent()) {

                if (searchServices.isDatesForTheRoomTaken(reservationRepo.getReservations(),
                        searchServices.stringToLocalDate(updateReservation.getReservationDates()))) {

                    return MessageResponseDTO.builder()
                            .codeHttp(HttpStatus.SC_NO_CONTENT)
                            .message(MessagesResponse.ROOM_TAKEN.message)
                            .build();
                }
                reservationRepo.getReservations().remove(rsvById.get());
                rsvById.get().setId(id);
                rsvById.get().setClientFullName(updateReservation.getClientFullName());
                rsvById.get().setRoomNumber(updateReservation.getRoomNumber());
                rsvById.get().setReservationDates(searchServices.stringToLocalDate(updateReservation.getReservationDates()));
                reservationRepo.getReservations().add(rsvById.get());

                return MessageResponseDTO.builder()
                        .codeHttp(HttpStatus.SC_OK)
                        .reservation(rsvById.get().getDTO())
                        .build();
            }

            return MessageResponseDTO.builder()
                    .codeHttp(HttpStatus.SC_NO_CONTENT)
                    .message(MessagesResponse.RSV_NOT_FOUND.message)
                    .build();
        }

        return MessageResponseDTO.builder()
                .codeHttp(HttpStatus.SC_NO_CONTENT)
                .message(MessagesResponse.DATES_BAD_VALUE.message)
                .build();

    }

    public MessageResponseDTO delete(Integer id) {
        Optional<Reservation> rsvById = searchServices.findById(reservationRepo.getReservations(), id);
        if (rsvById.isPresent()) {
            reservationRepo.getReservations().remove(rsvById.get());

            return MessageResponseDTO.builder()
                    .codeHttp(HttpStatus.SC_OK)
                    .message(MessagesResponse.RSV_DELETED.getMessage())
                    .build();
        }
        return MessageResponseDTO.builder()
                .codeHttp(HttpStatus.SC_NO_CONTENT)
                .message(MessagesResponse.RSV_NOT_FOUND.getMessage())
                .build();
    }

    public MessageResponseDTO getByRoomNumber(Integer roomNumber) {

        Optional<List<ReservationDTO>> allRsvByRoom = searchServices.findAllDatesByRoomNumber(reservationRepo.getReservations(), roomNumber);
        if (allRsvByRoom.isPresent() && allRsvByRoom.get().size() > 0) {
            return MessageResponseDTO.builder()
                    .codeHttp(HttpStatus.SC_OK)
                    .reservations(allRsvByRoom.get())
                    .build();
        }

        return MessageResponseDTO.builder()
                .codeHttp(HttpStatus.SC_NO_CONTENT)
                .message(MessagesResponse.ROOM_FREE.getMessage())
                .build();

    }
}
