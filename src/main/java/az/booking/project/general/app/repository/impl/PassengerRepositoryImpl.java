package az.booking.project.general.app.repository.impl;

import az.booking.project.general.app.config.SQLConfig;
import az.booking.project.general.app.entity.Flight;
import az.booking.project.general.app.repository.FlightRepository;
import az.booking.project.general.app.repository.PassengerFlightRepository;
import az.booking.project.general.app.repository.PassengerRepository;
import az.booking.project.general.app.repository.Queries;
import az.booking.project.general.app.service.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepositoryImpl implements PassengerRepository {

    private FlightRepository flightRepository;
    private PassengerFlightRepository passengerFlightRepository;

    @Override
    public void bookFlight(int serialNumber, String... people) {
        this.flightRepository = new FlightRepositoryImpl();
        this.passengerFlightRepository = new PassengerFlightRepositoryImpl();
        String wordInFile = "";
        int flightId = 0;
        ResultSet resultSet = null;
        //boolean resultSet=false;
        try (Connection connection = SQLConfig.sqlConfig().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.SEARCH_FLIGHT_BY_SERIAL_NUMBER);
        ) {
            preparedStatement.setString(1, String.valueOf(serialNumber));
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                flightId = resultSet.getInt(1);
            } else {
                return;
            }
            passengerFlightRepository.insert(UserServiceImpl.getUserId(), flightId);
            System.out.println("Inserted");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        //sql connection
        //find with serialnumber
        //convert it to Flight
        //then find people if exist, or add people to friend
        //add flight id to passenger_flight table

    }

    @Override
    public int findCurrentUser(String username, String password) {
        int id = 0;
        try (Connection connection = SQLConfig.sqlConfig().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.FIND_USER)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public int saveNewUser(String username, String password, String email) {
        int id = 0;
        try (Connection connection = SQLConfig.sqlConfig().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_USER)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            int isUserSaved = preparedStatement.executeUpdate();

            if (isUserSaved > 0) {
                System.out.println("User saved");
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Flight> myFlights(int userId) {
        List<Flight> flights = new ArrayList<>();
        try (Connection connection = SQLConfig.sqlConfig().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.FIND_MY_FLIGHTS)) {
            preparedStatement.setInt(1, userId);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                long id = set.getLong(1);
                String destination = set.getString(2);
                String fromThis = set.getString(3);
                String serialNumber = set.getString(4);
                /*Date date = set.getDate(5);
                LocalDate localDate = date.toLocalDate();
                Time time = set.getTime(6);
                LocalTime localTime = time.toLocalTime();*/
                Flight flight = Flight.builder()
                        .id(id)
                        .destination(destination)
                        .fromThis(fromThis)
                        .serialNumber(serialNumber)
                        /* .localDate(localDate)
                         .localTime(localTime)*/
                        .build();
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    @Override
    public void deleteFlight(int flightId) {
        //TODO call PassengerFlightRepository to delete or cancel flight from passenger_flight table
        this.passengerFlightRepository = new PassengerFlightRepositoryImpl();
        passengerFlightRepository.delete(flightId);
    }
}
