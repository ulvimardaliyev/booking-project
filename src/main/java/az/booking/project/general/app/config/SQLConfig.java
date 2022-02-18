package az.booking.project.general.app.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLConfig {
    private static SQLConfig sqlConfig;
    private Connection connection;
    public static final String databaseProps = "src/main/resources/db.properties";
    private static final Logger SQL_LOGGER = Logger.getLogger(SQLConfig.class.getName());

    private SQLConfig() {
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(databaseProps);
            Class.forName("org.postgresql.Driver");
            properties.load(fileInputStream);
            this.connection = DriverManager.getConnection(properties.getProperty("url"), properties);
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
