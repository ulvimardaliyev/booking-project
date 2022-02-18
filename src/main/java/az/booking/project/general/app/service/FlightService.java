package az.booking.project.general.app.service;

import az.booking.project.general.app.dao.entity.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> showFlightInfo();

    void search();

    Flight searchFlightById();
}
