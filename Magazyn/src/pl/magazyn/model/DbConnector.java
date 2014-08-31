package pl.magazyn.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pl.magazyn.exceptions.DbException;

class DbConnector {

    private static final String DB_USER = "root";

    private static final String DB_PASSWORD = "";

    private Connection connection;

    public DbConnector() {
        // empty
    }

    /**
     * for unit testing
     */
    public DbConnector(Connection connection) {
        this.connection = connection;
    }

    public void disconnect() throws DbException {
        try {
            connection.close();
        }
        catch (SQLException e) {
            String errorMsg = "Problem z zamykaniem bazy danych. Sprawdü stan po≥πczenia.";
            throw new DbException(errorMsg, e);
        }
    }

    public Connection getConnection() throws DbException {
        if (connection == null) {
            init();
        }
        return connection;
    }

    private void init() throws DbException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/" + DbHandler.DB_NAME;

            connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
        }
        catch (SQLException e) {
            String errorMsg = "Problem z otwarciem po≥πczenia do bazy danych. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
        catch (ClassNotFoundException e) {
            String errorMsg = "Problem z otwarciem po≥πczenia do bazy danych. Sprawdü czy baza danych jest w≥πczona.";
            throw new DbException(errorMsg, e);
        }
    }

}
