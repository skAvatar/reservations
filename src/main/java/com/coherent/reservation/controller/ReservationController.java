package com.coherent.reservation.controller;

import com.coherent.reservation.model.Reservation;
import com.coherent.reservation.service.ReservationServices;
import dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationServices reservationServices;

    @PostMapping("/reservation/")
    public void add(@RequestBody ReservationDTO newReservation){
        reservationServices.add(newReservation);
    }

    @GetMapping ("/reservation/{id}")
    public ReservationDTO getOne(@PathVariable Integer id){
        return reservationServices.getOne(id);
    }

    @GetMapping ("/reservation/")
    public List<ReservationDTO> getAll(){
        return reservationServices.getAll();
    }




}
