package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReservationDTO {

    @JsonProperty("id")
    Integer id;
    @JsonProperty("client_full_name")
    String clientFullName;
    @JsonProperty("room_number")
    Integer roomNumber;
    @JsonProperty("reservation_dates")
    List<String> reservationDates;

}
