package az.booking.project.general.app.controller;

import az.booking.project.general.app.dao.entity.Flight;
import az.booking.project.general.app.service.FlightService;

import java.util.List;

public class FlightController {
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public List<Flight> showFlightInfo() {
        return flightService.showFlightInfo();
    }

    public void search() {
        flightService.search();
    }

    public Flight searchFlightById() {
        return flightService.searchFlightById();
    }

}
