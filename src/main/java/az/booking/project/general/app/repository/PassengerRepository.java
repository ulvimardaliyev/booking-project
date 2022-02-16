package az.booking.project.general.app.repository;

import az.booking.project.general.app.entity.Flight;

import java.util.List;

public interface PassengerRepository {

    void bookFlight(int serialNumber, String... people);

    int findCurrentUser(String username, String password);

    int saveNewUser(String username, String password, String email);

    List<Flight> myFlights(int userId);

    void deleteFlight(int flightId);
}
