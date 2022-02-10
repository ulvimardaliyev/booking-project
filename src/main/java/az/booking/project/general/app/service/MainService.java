package az.booking.project.general.app.service;

import az.booking.project.general.app.entity.Flight;
import az.booking.project.general.app.entity.Friend;
import az.booking.project.general.app.entity.PassengerFlight;

import java.time.LocalDate;
import java.util.List;

public interface MainService {

    void onlineBoard();

    List<Flight> showFlightInfo();

    List<Flight> search(String destination, LocalDate localDate, int flyWith);

    void book(int serialNumber, List<Friend> friends);

    void cancelBooking(int flightId);

    List<PassengerFlight> myFlights();

    void exit();

    Flight searchById(int id);
    //void signUp(String username, String email, String password);
}
