package az.booking.project.general.app.service;

import az.booking.project.general.app.dao.entity.Flight;

import java.util.List;

public interface PassengerService {
    boolean login();

    boolean signUp();

    List<Flight> myFlights();

    void book();

    void cancelBooking();

}
