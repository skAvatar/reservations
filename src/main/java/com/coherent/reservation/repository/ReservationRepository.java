package com.coherent.reservation.repository;

import com.coherent.reservation.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class ReservationRepository {



    Set<Reservation> collection;

    public Set<Reservation> getReservations() {
        if(Optional.ofNullable(collection).isEmpty()){
            return collection = new HashSet<>();
        }

        return this.collection;
    }

    public void setReservations(Set<Reservation> collection) {
        this.collection = collection;
    }
}
