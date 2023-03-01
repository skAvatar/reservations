package com.coherent.reservation.util;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class DatesServices {

    public List<LocalDate> stringToLocalDate(List<String> dates){
        return dates.stream()
                .map(LocalDate::parse)
                .collect(Collectors.toList());
    }

    public List<String> localDateToString(List<LocalDate> dates){
        return dates.stream()
                .map(LocalDate::toString)
                .collect(Collectors.toList());
    }


}
