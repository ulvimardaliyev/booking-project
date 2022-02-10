package az.booking.project.general.app.repository;

public class Queries {
    public static final String FIND_ALL_FLIGHTS =
            "SELECT * FROM flight";
    public static final String SEARCH =
            "SELECT * FROM FLIGHT WHERE (DESTINATION=?) AND (FLIGHTDATE=?) and (PASSENGER_COUNT>?)";
    public static final String SEARCH_FLIGHT_BY_ID = "SELECT FLIGHTDATE, DESTINATION, PASSENGER_COUNT FROM FLIGHT WHERE id=?";
}
