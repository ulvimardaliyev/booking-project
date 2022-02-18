package az.booking.project.general.app.service.impl;

import az.booking.project.general.app.entity.Flight;
import az.booking.project.general.app.repository.FlightRepository;
import az.booking.project.general.app.service.FlightService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository;
    private Scanner scanner;

    public FlightServiceImpl(FlightRepository flightRepository, Scanner scanner) {
        this.flightRepository = flightRepository;
        this.scanner = scanner;
    }

    @Override
    public List<Flight> showFlightInfo() {
        return flightRepository.findAllFlights();
    }

    @Override
    public List<Flight> search() {
        System.out.println("Enter destination ");
        String destination = scanner.next();
        System.out.println("Enter date as format YYYY-MM-DD");
        String date = scanner.next();
        LocalDate localDate = LocalDate.parse(date);
        System.out.println("Enter the count of people who you want to fly with you");
        int number = scanner.nextInt();
        return flightRepository.search(destination, localDate, number);

    }

    @Override
    public Flight searchFlightById() {
        System.out.println("Enter flight id : ");
        int id = scanner.nextInt();
        return flightRepository.searchFlightById(id);
    }
}
