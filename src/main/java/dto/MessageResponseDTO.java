package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.httpclient.HttpStatus;

import java.util.List;

@Data
@Builder
public class MessageResponseDTO {

    @JsonProperty("code")
    int codeHttp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("reservation")
    ReservationDTO reservation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("reservations")
    List<ReservationDTO> reservations;



}
