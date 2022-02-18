package az.booking.project.general.app.service.impl;

import az.booking.project.general.app.service.MainService;

public class MainServiceImpl implements MainService {

    @Override
    public void onlineBoard() {
        String menu = String.format("Main menu : %d\n", 0);
        String exit = String.format("Exit : %d\n", 1);
        String login = String.format("Login : %d\n", 2);
        String signUp = String.format("SignUp : %d\n", 3);
        String myFlights = String.format("MyFlights : %d\n", 6);
        String book = String.format("Book flights : %d\n", 7);
        String cancel = String.format("Cancel flight : %d\n", 8);
        String searchFlightById = String.format("Search Flight by Id: %d\n", 9);
        String searchSpecificFlight = String.format("Search Specific Flight : %d\n", 10);
        String showFlightInfo = String.format("Show all flight : %d\n", 11);
        System.out.println(menu + exit + login + signUp + myFlights + book + cancel + searchFlightById + searchSpecificFlight + showFlightInfo);
    }

    @Override
    public void exit() {
        System.out.println("Bye");
        System.exit(1);
    }
}
