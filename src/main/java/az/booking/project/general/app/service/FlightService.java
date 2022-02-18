package az.booking.project.general.app.service;

import az.booking.project.general.app.entity.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> showFlightInfo();

    List<Flight> search();

    Flight searchFlightById();
}
