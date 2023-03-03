package com.coherent.reservation.repository;

import com.coherent.reservation.model.Reservation;
import com.coherent.reservation.util.DatesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class ReservationRepository {

    Set<Reservation> reservations;

    public Set<Reservation> getReservations() {
        if (Optional.ofNullable(reservations).isEmpty()) {
            return reservations = new HashSet<>();
        }

        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }




}
