package database;

/**
 * Created by Matthew on 2017-03-22.
 */



import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance = null;
    private Connection connection;

    private Database() {
        try {
            String connectionURL = "jdbc:oracle:thin:@localhost:1522:ug";
            String username = "ora_h8y9a";
            String password = "a37594132";
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = DriverManager.getConnection(connectionURL, username, password);
        }
        catch (SQLException e) {
            System.out.println("Message: " + e.getMessage());
            System.exit(-1);
        }
    }

    public static Database getInstance(){
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}

