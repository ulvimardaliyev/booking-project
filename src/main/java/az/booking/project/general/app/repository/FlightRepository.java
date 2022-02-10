package az.booking.project.general.app.repository;

import az.booking.project.general.app.entity.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository {
    List<Flight> findAllFlights();

    List<Flight> search(String destination, LocalDate localDate, int flyWith);

    Flight searchById(int id);
}
