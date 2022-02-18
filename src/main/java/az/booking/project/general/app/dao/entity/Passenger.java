package az.booking.project.general.app.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Passenger {
    private Long id;
    private String username;
    private String password;
    private String email;//email validation
    private List<Flight> flights;//manyToMany
    private List<Friend> flyWith;
}
