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

    @Autowired
    DatesServices datesServices;

    Set<Reservation> collection;

    public Set<Reservation> getReservations() {
        if (Optional.ofNullable(collection).isEmpty()) {
            return collection = new HashSet<>();
        }

        return this.collection;
    }

    public void setReservations(Set<Reservation> collection) {
        this.collection = collection;
    }




}
