package az.booking.project.general.app.exception;

public class UserNotFoundException extends Exception {
    private int id;


    public UserNotFoundException(String message, int id) {
        super(message);
        this.id = id;
    }

}
