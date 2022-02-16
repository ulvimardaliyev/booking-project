package az.booking.project.general.app;

/*import az.booking.project.general.app.service.MainService;
import az.booking.project.general.app.service.impl.MainServiceImpl;*/

import az.booking.project.general.app.service.LoginService;
import az.booking.project.general.app.service.MainService;
import az.booking.project.general.app.service.SignUpService;
import az.booking.project.general.app.service.impl.LoginServiceImpl;
import az.booking.project.general.app.service.impl.MainServiceImpl;
import az.booking.project.general.app.service.impl.SignUpServiceImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        MainService mainService = new MainServiceImpl();
        LoginService loginService = new LoginServiceImpl();
        SignUpService service = new SignUpServiceImpl();
        Scanner scanner = new Scanner(System.in);
        loginService.logIn(scanner.next(), scanner.next());
        System.out.println(mainService.myFlights());
        System.out.println(mainService.showFlightInfo());
        mainService.book(scanner.nextInt(), new ArrayList<>());
        System.out.println(mainService.searchById(scanner.nextInt()));
        mainService.onlineBoard();
        System.out.println(mainService.myFlights());
        mainService.cancelBooking(scanner.nextInt());

    }


    //create while loop
    //call service class and inject it to this main class
    //create static methods inside service class(es)
    //create queries for entities (separate them)
    //create repositories for entities (separate them) insert delete update
    //enum to integer

}
