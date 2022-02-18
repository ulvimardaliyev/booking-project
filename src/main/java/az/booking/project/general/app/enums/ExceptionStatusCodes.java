package az.booking.project.general.app.enums;

public enum ExceptionStatusCodes {
    USER_NOT_FOUND(404, "User with this id is not found"),
    FLIGHT_NOT_FOUND(404, "Flight with this id is not found"),
    SERIAL_NUMBER_NOT_FOUND(404, "Serial number with this number not found");

    private int statusCode;
    private String message;

    ExceptionStatusCodes(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public java.lang.String getMessage() {
        return message;
    }
}
