package com.coherent.reservation.util;

import com.coherent.reservation.model.Reservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public abstract class DatesServices {

    public List<LocalDate> stringToLocalDate(List<String> dates) {
        return dates.stream()
                .map(LocalDate::parse)
                .collect(Collectors.toList());
    }

    public List<String> localDateToString(List<LocalDate> dates) {
        return dates.stream()
                .map(LocalDate::toString)
                .collect(Collectors.toList());
    }

    public boolean isDatesValid(List<LocalDate> dates) {
        return (dates.get(0).isBefore(dates.get(1)) || dates.get(0).isEqual(dates.get(1)));
    }

    public boolean isDatesForTheRoomTaken(Set<Reservation> reservations, List<LocalDate> newDates) {
        boolean flag = false;

        for (Reservation actualRsv: reservations) {

            if (newDates.get(0).isEqual(actualRsv.getReservationDates().get(0)) ||
                    newDates.get(0).isEqual(actualRsv.getReservationDates().get(1))) {
                flag = true;
                break;
            }

            if (newDates.get(1).isEqual(actualRsv.getReservationDates().get(0)) ||
                    newDates.get(1).isEqual(actualRsv.getReservationDates().get(1))) {
                flag = true;
                break;
            }

            if (newDates.get(0).isAfter(actualRsv.getReservationDates().get(0)) &&
                    newDates.get(0).isBefore(actualRsv.getReservationDates().get(1))) {
                flag = true;
                break;
            }

            if (newDates.get(1).isAfter(actualRsv.getReservationDates().get(0)) &&
                    newDates.get(1).isBefore(actualRsv.getReservationDates().get(1))) {
                flag = true;
                break;
            }

        }
        return flag;
    }


}
