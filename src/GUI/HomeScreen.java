package GUI;

/**
 * Created by Matthew on 2017-03-26.
 */
import database.Database;
import tables.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HomeScreen {

    private static int WIDTH = 1000;
    private static int HEIGHT = 650;
    private static Connection con;

    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel buttonPanel;
    private static JPanel informationPanel;
    private static JPanel medicalHistoryPanel;
    private static JPanel schedulePanel;
    private static JPanel activityPannel;



    // Constructs homescreen for a manager
    public HomeScreen(int userId, boolean isManager){
        con = Database.getInstance().getConnection();
        if(isManager)
        {
            try {
                ResultSet rs = con.createStatement().executeQuery("SELECT * from Users where userid ='" + userId + "'");
                rs.next();
                int userid = rs.getInt("USERID");
                String tempusername = rs.getString("USERNAME");
                String tempname = rs.getString("NAME");
                String tempphone = rs.getString("PHONENO");

                Manager managerUser = new Manager(tempname, tempphone, userid, tempusername, null, 0);
                System.out.println("Manager: " + managerUser.getName());

                generateStructure(managerUser);

                JLabel name = new JLabel("Name: " + managerUser.getName());
                informationPanel.add(name);
                JLabel username = new JLabel("Username: " + managerUser.getUsername());
                informationPanel.add(username);
                JLabel id = new JLabel("ID: " + managerUser.getUserId());
                informationPanel.add(id);
                JLabel phone = new JLabel("Phone: " + managerUser.getPhone());
                informationPanel.add(phone);
                JLabel wage = new JLabel("Wage: " + managerUser.getWage());
                informationPanel.add(wage);
                JLabel userType = new JLabel("User Type: Manager");
                informationPanel.add(userType);

                JButton seeRes = new JButton("View All Reservations");
                seeRes.addActionListener(new seeAllReservations());
                buttonPanel.add(seeRes);
                JButton seeUnpaidBills = new JButton("Unpaid Bills");
                seeUnpaidBills.addActionListener(new viewUnpaidBills());
                buttonPanel.add(seeUnpaidBills);
                JButton lowestPaidHouseKeeper = new JButton("Lowest Paid HouseKeeper");
                lowestPaidHouseKeeper.addActionListener(new viewLowestPaidHouseKeeper());
                buttonPanel.add(lowestPaidHouseKeeper);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else //Constructs homescreen for Guest
        {
            try {
                ResultSet rs = con.createStatement().executeQuery("SELECT * from Users u, Guest g where u.userid ='" + userId + "' and g.userid ='" + userId + "'");
                rs.next();
                int userid = rs.getInt("USERID");
                String tempusername = rs.getString("USERNAME");
                String tempname = rs.getString("NAME");
                String tempphone = rs.getString("PHONENO");
                String tempmembershipType = rs.getString("MEMBERSHIPTYPE");
                String tempaddress = rs.getString("HOMEADDRESS");
                int tempnightsStayedBefore = rs.getInt("NIGHTS_STAYED_BEFORE");
                Guest guestUser = new Guest(tempname, tempphone, userid, tempusername, null, tempmembershipType, tempaddress, tempnightsStayedBefore );
                System.out.println("Guest: " + guestUser.getName());
                generateStructure(guestUser);

                JLabel name = new JLabel("Name: " + guestUser.getName());
                informationPanel.add(name);
                JLabel username = new JLabel("Username: " + guestUser.getUsername());
                informationPanel.add(username);
                JLabel id = new JLabel("ID: " + guestUser.getUserId());
                informationPanel.add(id);
                JLabel phone = new JLabel("Phone: " + guestUser.getPhone());
                informationPanel.add(phone);
                JLabel mType = new JLabel("Membership Type: " + guestUser.getMembershipType());
                informationPanel.add(mType);
                JLabel address = new JLabel("Address: " + guestUser.getAddress());
                informationPanel.add(address);
                JLabel nightsStayedBefore = new JLabel("Nights Stayed Before: " + guestUser.getNightsStayedBefore());
                informationPanel.add(nightsStayedBefore);
                JLabel userType = new JLabel("User Type: Guest");
                informationPanel.add(userType);

                JButton makeRes = new JButton("Create a Reservation");
                makeRes.addActionListener(new makeReservation());
                buttonPanel.add(makeRes);
                JButton viewRooms = new JButton("View Available Rooms");
                viewRooms.addActionListener(new viewRooms());
                buttonPanel.add(viewRooms);
                JButton changeRes = new JButton("Change Reservation");
                changeRes.addActionListener(new changeReservation());
                buttonPanel.add(changeRes);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        frame.setVisible(true);
    }

    // Creates layout of homescreen
    private void generateStructure(User u){
        frame = new JFrame("Hotel");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        mainPanel = new JPanel();
        frame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        informationPanel = new JPanel();
        mainPanel.add(informationPanel);
        informationPanel.setLayout(new GridLayout(2, 5 , 8, 8));
        informationPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "User Information",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout());
    }

    //Button actions from here on

    // #1 Selection and projection query
    private static class viewUnpaidBills implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                ResultSet rs = con.createStatement().executeQuery("select GUSERID, AMOUNTPAID, AMOUNTDUE from BILL_HAS_GENERATE_BILL where AMOUNTPAID < AMOUNTDUE");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from BILL_HAS_GENERATE_BILL where AMOUNTPAID < AMOUNTDUE");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Unpaid Bills", Arrays.asList("Guest ID", "Amount Paid", "Amount Due"), Arrays.asList("GUSERID", "AMOUNTPAID", "AMOUNTDUE"));
            }
            catch(SQLException vre1)
            {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    // #4 Aggregation query
    private static class viewLowestPaidHouseKeeper implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                ResultSet rs = con.createStatement().executeQuery("select * from Housekeeper2 where wage = (select min(wage) from HOUSEKEEPER2)");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from Housekeeper2 where wage = (select min(wage) from HOUSEKEEPER2)");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Lowest Paid HouseKeeper", Arrays.asList("Wage", "Job", "Phone", "Name", "SIN"), Arrays.asList("WAGE", "CLEANINGSPECIALITY", "PHONENO", "NAME", "SIN"));
            }
            catch(SQLException vre1)
            {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewRooms implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                ResultSet rs = con.createStatement().executeQuery("select r2.roomno, r2.typeofroom, r2.floorno, r2.numofbeds, r1.cost from reserve_room_has_floor2 r2, reserve_room_has_floor1 r1 where r2.guserid is null and r1.typeofroom = r2.typeofroom");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from reserve_room_has_floor2 where guserid is null");
                countrs.next();

                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Rooms Available", Arrays.asList("Room Number", "Type of Room", "Floor No", "Num of Beds", "Price"), Arrays.asList("ROOMNO", "TYPEOFROOM", "FLOORNO", "NUMOFBEDS", "COST"));
            }
            catch(SQLException vre1)
            {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class makeReservation implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                ResultSet rs = con.createStatement().executeQuery("select * from guest");
            }
            catch(SQLException vre1)
            {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Fail", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class changeReservation implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                ResultSet rs = con.createStatement().executeQuery("select * from user_tables");

            }
            catch(SQLException vre1)
            {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Fail", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class seeAllReservations implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                ResultSet rs = con.createStatement().executeQuery("select * from RESERVE_ROOM_HAS_FLOOR2");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from RESERVE_ROOM_HAS_FLOOR2");
                countrs.next();

                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt("COUNT(*)"), "Reservations", Arrays.asList("Room No", "Type of Room", "Floor No", "Guest ID", "Booking No", "From Date", "To Date", "Number of Beds"), Arrays.asList("ROOMNO", "TYPEOFROOM", "FLOORNO", "GUSERID", "BOOKINGNO", "FROMDATE", "TODATE", "NUMOFBEDS"));
            }
            catch(SQLException vre1)
            {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Fail", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }
}
