package az.booking.project.general.app.dao.repository;

import az.booking.project.general.app.dao.entity.Flight;
import az.booking.project.general.app.dao.entity.Friend;

import java.util.List;

public interface PassengerRepository {

    void bookFlight(int serialNumber, List<Friend> friends);

    int findCurrentUser(String username, String password);

    int saveNewUser(String username, String password, String email);

    List<Flight> myFlights(int userId);

    void deleteFlight(int flightId);
}
