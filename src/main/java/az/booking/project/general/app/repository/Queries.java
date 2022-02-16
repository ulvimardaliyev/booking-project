package az.booking.project.general.app.repository;

public class Queries {
    public static final String FIND_ALL_FLIGHTS =
            "SELECT * FROM flight";
    public static final String SEARCH =
            "SELECT * FROM FLIGHT WHERE (DESTINATION=?) AND (FLIGHTDATE=?) and (PASSENGER_COUNT>?)";
    public static final String SEARCH_FLIGHT_BY_ID =
            "SELECT FLIGHTDATE, DESTINATION, PASSENGER_COUNT FROM FLIGHT WHERE id=?";
    public static final String SEARCH_FLIGHT_BY_SERIAL_NUMBER =
            "SELECT * FROM FLIGHT WHERE FLIGHT.serialnumber=?";
    public static final String BOOK_FLIGHT = "INSERT INTO PASSENGER_FLIGHT(passenger_id,flight_id) VALUES(?,?)";
    public static final String FIND_USER = "SELECT id from PASSENGER WHERE username=? AND password=?";
    public static final String INSERT_USER = "INSERT INTO PASSENGER(username, password, email) VALUES(?,?,?)";
    public static final String FIND_MY_FLIGHTS = "SELECT f.ID, f.DESTINATION, f.FROMTHIS, f.SERIALNUMBER, f.FLIGHTDATE, f.FLIGHTTIME from FLIGHT f " +
            "INNER JOIN PASSENGER_FLIGHT pf on f.ID = pf.FLIGHT_ID " +
            "INNER JOIN PASSENGER p on pf.PASSENGER_ID = p.id " +
            "WHERE p.id = ?";
    public static final String DELETE_FLIGHT = "DELETE FROM PASSENGER_FLIGHT WHERE PASSENGER_ID=? AND FLIGHT_ID=?";
}
