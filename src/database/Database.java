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
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String connectionURL = "jdbc:oracle:thin:@localhost:1522:ug";
            connection = DriverManager.getConnection(connectionURL, "ora_g0l0b", "a20090156");
            System.out.println("WE ARE CONNECTED");
        }
        catch (SQLException e) {
            System.out.println("FISSION MAILED");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static Database getInstance(){
        if (instance == null) instance = new Database();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public int createQuery(String q)
    {
        return 0;
    }
}

