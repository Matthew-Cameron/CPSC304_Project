package database;


/**
 * Created by Matthew on 2017-03-22.
 */



import tables.Reserve_Room_Has_Floor2;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import tables.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

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
        } catch (SQLException e) {
            System.out.println("FISSION MAILED");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static Database getInstance() {
        if (instance == null) instance = new Database();
        return instance;
    }


    public Connection getConnection() {
        return connection;
    }

    public int createQuery(String q) {
        return 0;
    }

    //division query
    public boolean deleteHousekeeper(int sin) throws SQLException {
        int result = connection.createStatement().executeUpdate("delete from housekeeper2 where sin = " + sin);
        return result == 1;

    }

    public boolean deleteRoomNo(int roomNo) throws SQLException {
        int result = connection.createStatement().executeUpdate("delete from reserve_room_has_floor2 where roomNo = " + roomNo);
        return result == 1;

    }
}




