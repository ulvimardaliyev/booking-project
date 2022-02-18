package az.booking.project.general.app.run;

import az.booking.project.general.app.controller.FlightController;
import az.booking.project.general.app.controller.MainController;
import az.booking.project.general.app.controller.PassengerController;
import az.booking.project.general.app.dao.repository.FlightRepository;
import az.booking.project.general.app.dao.repository.PassengerFlightRepository;
import az.booking.project.general.app.dao.repository.PassengerRepository;
import az.booking.project.general.app.dao.repository.impl.FlightRepositoryImpl;
import az.booking.project.general.app.dao.repository.impl.PassengerFlightRepositoryImpl;
import az.booking.project.general.app.dao.repository.impl.PassengerRepositoryImpl;
import az.booking.project.general.app.service.FlightService;
import az.booking.project.general.app.service.MainService;
import az.booking.project.general.app.service.PassengerService;
import az.booking.project.general.app.service.impl.FlightServiceImpl;
import az.booking.project.general.app.service.impl.MainServiceImpl;
import az.booking.project.general.app.service.impl.PassengerServiceImpl;

import java.util.Scanner;

public class ApplicationStarter {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        FlightRepository flightRepository = new FlightRepositoryImpl();
        PassengerFlightRepository passengerFlightRepository = new PassengerFlightRepositoryImpl();
        PassengerRepository passengerRepository = new PassengerRepositoryImpl(flightRepository, passengerFlightRepository);
        PassengerService passengerService = new PassengerServiceImpl(passengerRepository, scanner);
        FlightService flightService = new FlightServiceImpl(flightRepository, scanner);
        MainService mainService = new MainServiceImpl();

        System.out.println("Enter the value from 1 to 9");
        mainService.onlineBoard();

        FlightController flightController = new FlightController(flightService);
        PassengerController passengerController = new PassengerController(passengerService);
        MainController mainController = new MainController(mainService);


        boolean flag = true;

        while (flag) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    mainController.onlineBoard();
                    break;
                case 1:
                    flag = mainController.exit();
                    break;
                case 2:
                    passengerController.login();
                    mainController.onlineBoard();
                    break;
                case 3:
                    passengerController.signUp();
                    mainController.onlineBoard();
                    break;
                case 4:
                    passengerController.myFlights();
                    mainController.onlineBoard();
                    break;
                case 5:
                    passengerController.book();
                    mainController.onlineBoard();
                    break;
                case 6:
                    passengerController.cancelBooking();
                    mainController.onlineBoard();
                    break;
                case 7:
                    System.out.println(flightController.searchFlightById());
                    mainController.onlineBoard();
                    break;
                case 8:
                    flightController.search();
                    mainController.onlineBoard();
                    break;
                case 9:
                    System.out.println(flightController.showFlightInfo());
                    mainController.onlineBoard();
                    break;
                default:
                    System.out.println("Please choose correct value inside range 1-6 inclusive");
                    mainController.onlineBoard();
                    break;
            }
        }
    }
}
