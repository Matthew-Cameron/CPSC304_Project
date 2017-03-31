package database;


/**
 * Created by Matthew on 2017-03-22.
 */


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
            System.out.println("DATABASE: This is where the fun begins!");
        } catch (SQLException e) {
            System.out.println("DATABASE: It's failure, then.");
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

    //deletion query
    public boolean deleteHousekeeper(int sin) throws SQLException {
        int result = connection.createStatement().executeUpdate("delete from housekeeper2 where sin = " + sin);
        return result == 1;

    }

    public boolean deleteRoomNo(int roomNo) throws SQLException {
        int result = connection.createStatement().executeUpdate("delete from reserve_room_has_floor2 where roomNo = " + roomNo);
        return result == 1;
    }

    public boolean hireHousekeeper(int sin) throws SQLException {
        int result = connection.createStatement().executeUpdate("insert  into housekeeper2 values(NULL, NULL, NULL, NULL," + sin + ")");
        return result == 1;
    }
    
    //update query
    public boolean updateDiscount(String amount, String bid) throws SQLException{
        int result = connection.createStatement().executeUpdate("update DISCOUNTS set AMOUNT = " + amount + " where BILLID = " + bid);
        return result == 1;
    }

    public boolean deleteAllReservation(int guestID) throws SQLException{
        int result = connection.createStatement().executeUpdate("update reserve_room_has_floor2 set guserID = null, bookingNo = null, fromDate = null, toDate = null where guserID =  " + guestID);
        return result == 1;
    }

    public boolean deleteOneReservation(int guestID, int roomNo) throws SQLException{
        int result = connection.createStatement().executeUpdate("update reserve_room_has_floor2 set guserID = null, bookingNo = null, fromDate = null, toDate = null where guserID =  " + guestID + "roomNo=" + roomNo);
        return result == 1;
    }



}




