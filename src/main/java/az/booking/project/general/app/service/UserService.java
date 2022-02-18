package az.booking.project.general.app.service;

import az.booking.project.general.app.entity.Flight;

import java.util.List;

public interface UserService {
    boolean login();

    boolean signUp();

    List<Flight> myFlights();

    void book();

    void cancelBooking();

}
