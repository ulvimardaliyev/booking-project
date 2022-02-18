package az.booking.project.general.app.controller;

import az.booking.project.general.app.dao.entity.Flight;
import az.booking.project.general.app.service.PassengerService;

import java.util.List;

public class PassengerController {
    private PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public boolean login() {
        return passengerService.login();
    }

    public boolean signUp() {
        return passengerService.signUp();
    }

    public List<Flight> myFlights() {
        return passengerService.myFlights();
    }

    public void book() {
        passengerService.book();
    }

    public void cancelBooking() {
        passengerService.cancelBooking();
    }

}
