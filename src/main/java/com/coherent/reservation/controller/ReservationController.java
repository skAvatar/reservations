package com.coherent.reservation.controller;

import com.coherent.reservation.service.ReservationServices;
import dto.MessageResponseDTO;
import dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationServices reservationServices;

    @PostMapping("/reservation/")
    public MessageResponseDTO add(@RequestBody ReservationDTO newReservation){
        return reservationServices.add(newReservation);
    }

    @GetMapping ("/reservation/{id}")
    public MessageResponseDTO getOne(@PathVariable Integer id){
        return reservationServices.getOne(id);
    }

    @GetMapping ("/reservation/")
    public MessageResponseDTO getAll(){
        return reservationServices.getAll();
    }

    @PutMapping ("/reservation/{id}")
    public MessageResponseDTO put(@PathVariable Integer id, @RequestBody ReservationDTO updateReservation){
        return reservationServices.put(id, updateReservation);
    }

    @DeleteMapping ("/reservation/{id}")
    public MessageResponseDTO delete(@PathVariable Integer id){
        return reservationServices.delete(id);
    }

    @GetMapping ("/reservation/roomNumber/{roomNumber}")
    public MessageResponseDTO getByRoomNumber(@PathVariable Integer roomNumber){
        return reservationServices.getByRoomNumber(roomNumber);
    }



}
