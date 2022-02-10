package az.booking.project.general.app.service.impl;

import az.booking.project.general.app.entity.Flight;
import az.booking.project.general.app.entity.Friend;
import az.booking.project.general.app.entity.PassengerFlight;
import az.booking.project.general.app.repository.FlightRepository;
import az.booking.project.general.app.repository.impl.FlightRepositoryImpl;
import az.booking.project.general.app.service.MainService;

import java.time.LocalDate;
import java.util.List;

public class MainServiceImpl implements MainService {
    private FlightRepository flightRepository;

    @Override
    public void onlineBoard() {
        String menu = String.format("Main menu : %d\n", 0);
        String board = String.format("Online board : %d\n", 1);
        String flightInfo = String.format("Flight info : %d\n", 2);
        String searchFlight = String.format("Search fight : %d\n", 3);
        String bookFlight = String.format("Book flight : %d\n", 4);
        String cancelBooking = String.format("Cancel booking : %d\n", 5);
        String myFlights = String.format("My flights : %d\n", 6);
        String exit = String.format("Exit : %d\n", 7);
        System.out.println(menu + board + flightInfo + searchFlight + bookFlight + cancelBooking + myFlights + exit);
    }

    @Override
    public List<Flight> showFlightInfo() {
        flightRepository = new FlightRepositoryImpl();
        return flightRepository.findAllFlights();
    }

    @Override
    public List<Flight> search(String destination, LocalDate localDate, int flyWith) {
        return flightRepository.search(destination, localDate, flyWith);
    }

    @Override
    public Flight searchById(int id) {
        return flightRepository.searchById(id);
    }

    @Override
    public void book(int serialNumber, List<Friend> friends) {

    }

    @Override
    public void cancelBooking(int flightId) {

    }

    @Override
    public List<PassengerFlight> myFlights() {
        return null;
    }

    @Override
    public void exit() {
        System.out.println("Bye");
        System.exit(1);
    }
}
