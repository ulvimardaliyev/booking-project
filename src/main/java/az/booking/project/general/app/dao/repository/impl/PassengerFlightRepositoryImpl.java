package az.booking.project.general.app.dao.repository.impl;

import az.booking.project.general.app.config.SQLConfig;
import az.booking.project.general.app.dao.repository.PassengerFlightRepository;
import az.booking.project.general.app.dao.repository.Queries;
import az.booking.project.general.app.service.impl.PassengerServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PassengerFlightRepositoryImpl implements PassengerFlightRepository {

    @Override
    public boolean insert(int passengerId, int flightId) {
        try (Connection connection = SQLConfig.sqlConfig().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.BOOK_FLIGHT)) {
            preparedStatement.setInt(1, passengerId);
            preparedStatement.setInt(2, flightId);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet != 0) {
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int flightId) {
        int id = PassengerServiceImpl.getUserId();
        boolean isDeleted = false;
        try (Connection connection = SQLConfig.sqlConfig().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Queries.DELETE_FLIGHT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, flightId);
            isDeleted = preparedStatement.execute();
            if (isDeleted) {
                System.out.println("Flight cancelled");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
}
