package az.booking.project.general.app.exception;

public class FlightNotFoundException extends Exception {

    private int id;
    private String message;

    public FlightNotFoundException(int id, String message) {
        super(message);
    }
}
//TODO create Enums to assign it to exception in order to convert it to String.
//TODO HTTP.NOTFOUND -- 404 -- User not found