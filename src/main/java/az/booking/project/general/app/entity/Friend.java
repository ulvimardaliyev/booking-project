package az.booking.project.general.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friend {
    private Long id;
    private String name;
    private String lastname;
    private Flight flight;
    private Passenger passenger;
}
