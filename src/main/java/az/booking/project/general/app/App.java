package az.booking.project.general.app;

import java.sql.Date;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {/*

        MainService mainService = new MainServiceImpl();
        mainService.onlineBoard();
        System.out.println(mainService.showFlightInfo());
        System.out.println(mainService.search("Sumqayit", LocalDate.of(2022, 02, 10), 4));
        System.out.println(mainService.searchById(5));
*/
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.valueOf(localDateTime.toLocalDate() + localDateTime.toLocalTime());
        System.out.println(date);
    }

    //create while loop
    //call service class and inject it to this main class
    //create static methods inside service class(es)
    //create queries for entities (separate them)
    //create repositories for entities (separate them) insert delete update
    //enum to integer

}
