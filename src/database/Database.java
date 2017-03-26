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
            //This requires SSH tunneling and VPN/campus wifi/campus ethernet
            //SSH: use PuTTY
            //port: 1522
            //server: dbhost.ugrad.cs.ubc.ca:1522
            String connectionURL = "jdbc:oracle:thin:@localhost:1522:ug";
            String username = "ora_g0l0b";
            String password = "a20090156";
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

