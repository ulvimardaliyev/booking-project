package az.booking.project.general.app.repository;

public interface PassengerFlightRepository {
    boolean insert(int passengerId, int flightId);

    boolean delete(int flightId);
}
