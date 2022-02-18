package az.booking.project.general.app.run;

import az.booking.project.general.app.repository.FlightRepository;
import az.booking.project.general.app.repository.PassengerRepository;
import az.booking.project.general.app.repository.impl.FlightRepositoryImpl;
import az.booking.project.general.app.repository.impl.PassengerRepositoryImpl;
import az.booking.project.general.app.service.FlightService;
import az.booking.project.general.app.service.MainService;
import az.booking.project.general.app.service.UserService;
import az.booking.project.general.app.service.impl.FlightServiceImpl;
import az.booking.project.general.app.service.impl.MainServiceImpl;
import az.booking.project.general.app.service.impl.UserServiceImpl;

import java.util.Scanner;

public class IterateWithWhile {

    public static void iterate() {
        Scanner scanner = new Scanner(System.in);
        FlightRepository flightRepository = new FlightRepositoryImpl();
        PassengerRepository passengerRepository = new PassengerRepositoryImpl();
        UserService userService = new UserServiceImpl(passengerRepository, scanner);
        FlightService flightService = new FlightServiceImpl(flightRepository, scanner);
        MainService mainService = new MainServiceImpl();
        System.out.println("Enter the value from 1 to 11");
        mainService.onlineBoard();

        boolean flag = true;

        while (flag) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    mainService.onlineBoard();
                    break;
                case 1:
                    mainService.exit();
                    flag = false;
                    break;
                case 2:
                    userService.login();
                    break;
                case 3:
                    userService.signUp();
                    break;
                /*case 4:
                 */
                case 6:
                    userService.myFlights();
                    break;
                case 7:
                    userService.book();
                    break;
                case 8:
                    userService.cancelBooking();
                    break;
                case 9:
                    flightService.searchFlightById();
                    break;
                case 10:
                    flightService.search();
                    break;
                case 11:
                    flightService.showFlightInfo();
                    break;
                default:
                    System.out.println("Please choose correct value inside range 1-6 inclusive");
                    break;
            }
        }
    }
}
