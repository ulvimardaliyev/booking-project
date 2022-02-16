package az.booking.project.general.app.service.impl;

import az.booking.project.general.app.entity.Flight;
import az.booking.project.general.app.entity.Friend;
import az.booking.project.general.app.repository.FlightRepository;
import az.booking.project.general.app.repository.PassengerRepository;
import az.booking.project.general.app.repository.impl.FlightRepositoryImpl;
import az.booking.project.general.app.repository.impl.PassengerRepositoryImpl;
import az.booking.project.general.app.service.MainService;

import java.time.LocalDate;
import java.util.List;

public class MainServiceImpl implements MainService {
    private FlightRepository flightRepository;
    private PassengerRepository passengerRepository;

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
        this.passengerRepository = new PassengerRepositoryImpl();
        passengerRepository.bookFlight(serialNumber, "");
    }

    @Override
    public void cancelBooking(int flightId) {
        int userId = UserServiceImpl.getUserId();
        passengerRepository = new PassengerRepositoryImpl();
        passengerRepository.deleteFlight(flightId);
    }

    @Override
    public List<Flight> myFlights() {
        this.passengerRepository = new PassengerRepositoryImpl();
        int id = UserServiceImpl.getUserId();
        return passengerRepository.myFlights(id);
    }

    @Override
    public void exit() {
        System.out.println("Bye");
        System.exit(1);
    }

    /*private int getUserId(){
        int id = 0;
        File file = new File("src/main/resources/userinfo.txt");
        try (FileReader fileReader = new FileReader(file)) {
            StringBuilder word = new StringBuilder();
            int a;
            while ((a = fileReader.read()) != -1) {
                word.append((char) a);
            }
            id = Integer.parseInt(word.substring(14));
            System.out.println("ID is " + id);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return id;
    }*/
}
