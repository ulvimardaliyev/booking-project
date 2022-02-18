package az.booking.project.general.app.dao.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
    private Long id;
    private String destination;
    private String fromThis;
    private String serialNumber;
    private LocalDate localDate;
    private LocalTime localTime;
    private int passengerCount;
}
