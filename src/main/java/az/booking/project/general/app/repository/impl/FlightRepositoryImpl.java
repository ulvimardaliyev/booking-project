package az.booking.project.general.app.repository.impl;

import az.booking.project.general.app.config.SQLConfig;
import az.booking.project.general.app.entity.Flight;
import az.booking.project.general.app.repository.FlightRepository;
import az.booking.project.general.app.repository.Queries;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightRepositoryImpl implements FlightRepository {
    private SQLConfig sqlConfig;

    @Override
    public List<Flight> findAllFlights() {
        List<Flight> allFlights = new ArrayList<>();

        try {
            sqlConfig = SQLConfig.sqlConfig();
            PreparedStatement preparedStatement = sqlConfig.getConnection().prepareStatement(Queries.FIND_ALL_FLIGHTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String destination = resultSet.getString(2);
                String fromThis = resultSet.getString(3);
                String serialNum = resultSet.getString(4);
                /*Date date = resultSet.getDate(5);
                LocalDate localDate = date.toLocalDate();*/
                int count = resultSet.getInt(7);
                Flight flight =
                        Flight.builder()
                                .id((long) id)
                                .destination(destination)
                                .fromThis(fromThis)
                                .serialNumber(serialNum)
                                //.localDate(localDate)
                                .passengerCount(count)
                                .build();
                allFlights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allFlights;
    }

    @Override
    public List<Flight> search(String destination, LocalDate localDate, int flyWith) {
        List<Flight> allFlights = new ArrayList<>();
        try {
            sqlConfig = SQLConfig.sqlConfig();
            PreparedStatement preparedStatement = sqlConfig.getConnection().prepareStatement(Queries.SEARCH);
            preparedStatement.setString(1, destination);
            preparedStatement.setDate(2, Date.valueOf(localDate));
            preparedStatement.setInt(3, flyWith);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String dest = resultSet.getString(2);
                String fromThis = resultSet.getString(3);
                String serialNum = resultSet.getString(4);
                Date date = resultSet.getDate(5);
                LocalDate currentDate = date.toLocalDate();
                int count = resultSet.getInt(7);
                Flight flight =
                        Flight.builder()
                                .id((long) id)
                                .destination(destination)
                                .fromThis(fromThis)
                                .serialNumber(serialNum)
                                .localDate(currentDate)
                                .passengerCount(count)
                                .build();
                allFlights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allFlights;
    }

    @Override
    public Flight searchById(int id) {
        Flight flight = null;
        System.out.println("INSIDE SEARCH");
        try {
            sqlConfig = SQLConfig.sqlConfig();
            PreparedStatement preparedStatement = sqlConfig.getConnection().prepareStatement(Queries.SEARCH_FLIGHT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int count = resultSet.getInt(3);
                String dest = resultSet.getString(2);
                Date date = resultSet.getDate(1);

                flight = Flight.builder().passengerCount(count).localDate(date.toLocalDate()).destination(dest).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }
}
