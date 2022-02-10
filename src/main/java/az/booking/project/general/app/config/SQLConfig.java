package az.booking.project.general.app.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLConfig {
    private static SQLConfig sqlConfig;
    private Connection connection;
    private static final Logger SQL_LOGGER = Logger.getLogger(SQLConfig.class.getName());

    private SQLConfig() {
        Properties properties = new Properties();
        InputStream input =
                getClass().getClassLoader().getResourceAsStream("db.properties");
        try {
            Class.forName("org.postgresql.Driver");
            properties.load(input);
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/booking_app", properties);
            SQL_LOGGER.log(Level.INFO, "Connection established");
        } catch (ClassNotFoundException | SQLException | IOException e) {
            SQL_LOGGER.log(Level.CONFIG, "Connection is closed");
            e.printStackTrace();
        }
    }

    public static SQLConfig sqlConfig() throws SQLException {
        if (sqlConfig == null) {
            sqlConfig = new SQLConfig();
        } else if (sqlConfig.getConnection().isClosed()) {
            sqlConfig = new SQLConfig();
        }
        return sqlConfig;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
