package az.booking.project.general.app.dao.repository.impl;

import az.booking.project.general.app.config.SQLConfig;
import az.booking.project.general.app.dao.entity.Flight;
import az.booking.project.general.app.dao.repository.FlightRepository;
import az.booking.project.general.app.dao.repository.Queries;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FlightRepositoryImpl implements FlightRepository {

    @Override
    public List<Flight> findAllFlights() {
        List<Flight> allFlights = new ArrayList<>();
        try (Connection connection = SQLConfig.sqlConfig().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.FIND_ALL_FLIGHTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            collectAllFlights(allFlights, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allFlights;
    }

    @Override
    public List<Flight> search(String destination, LocalDate localDate, int flyWith) {
        List<Flight> allFlights = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     SQLConfig.sqlConfig().getConnection().prepareStatement(Queries.SEARCH)) {
            preparedStatement.setString(1, destination);
            preparedStatement.setDate(2, Date.valueOf(localDate));
            preparedStatement.setInt(3, flyWith);
            ResultSet resultSet = preparedStatement.executeQuery();
            collectAllFlights(allFlights, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allFlights;
    }

    @Override
    public Flight searchFlightById(int id) {

        Flight flight = null;
        ResultSet resultSet = null;
        try (Connection connection = SQLConfig.sqlConfig().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.SEARCH_FLIGHT_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int count = resultSet.getInt(3);
                String dest = resultSet.getString(2);
                Date date = resultSet.getDate(1);
                flight = Flight
                        .builder()
                        .passengerCount(count)
                        .localDate(date.toLocalDate())
                        .destination(dest)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }


    private void collectAllFlights(List<Flight> allFlights, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String destination = resultSet.getString(2);
            String fromThis = resultSet.getString(3);
            String serialNum = resultSet.getString(4);
            Date date = resultSet.getDate(5);
            LocalDate localDate = date.toLocalDate();
            Time time = resultSet.getTime(6);
            LocalTime localTime = time.toLocalTime();
            int count = resultSet.getInt(7);
            Flight flight =
                    Flight.builder()
                            .id((long) id)
                            .destination(destination)
                            .fromThis(fromThis)
                            .serialNumber(serialNum)
                            .localDate(localDate)
                            .localTime(localTime)
                            .passengerCount(count)
                            .build();
            allFlights.add(flight);
        }
    }

}
