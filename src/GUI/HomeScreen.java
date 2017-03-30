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
    private static JTextField sinText;
    private static JLabel sinLabel;
    private static JButton submitHButton;
    private static JTextField roomText;
    private static JLabel roomNoLabel;
    private static JButton submitRButton;




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
                seeRes.addActionListener(null);
                buttonPanel.add(seeRes);

                JButton findHousekeepersAssignedToAllRoom = new JButton("Housekeepers assigned to all floors");
                findHousekeepersAssignedToAllRoom.addActionListener(new findHousekeepersAssignedToAllRoom());
                buttonPanel.add(findHousekeepersAssignedToAllRoom);

                sinLabel = new JLabel("Delete SIN");
                sinLabel.setBounds(200, 100, 80, 25);
                informationPanel.add(sinLabel);
                sinText = new JTextField(7);
                sinText.setBounds(100, 40, 50, 25);
                informationPanel.add(sinText);
                submitHButton = new JButton();
                submitHButton.setText("Submit");
                submitHButton.addActionListener(new viewDeleteHousekeeper());
                informationPanel.add(submitHButton);
                JButton viewSer = new JButton("View Services Assigned");
                viewSer.addActionListener(new viewServicesAssigns());
                buttonPanel.add(viewSer);

                roomNoLabel = new JLabel("Delete room");
                roomNoLabel.setBounds(200, 100, 80, 25);
                informationPanel.add(roomNoLabel);
                roomText = new JTextField(7);
                roomText.setBounds(100, 40, 50, 25);
                informationPanel.add(roomText);
                submitRButton = new JButton();
                submitRButton.setText("Submit");
                submitRButton.addActionListener(new viewDeleteRoom());
                informationPanel.add(submitRButton);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else
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

    private static class findHousekeepersAssignedToAllRoom implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {

                ResultSet rs = con.createStatement().executeQuery("SELECT  * FROM housekeeper2 h WHERE  NOT EXISTS  (SELECT  * FROM  floor F WHERE  NOT EXISTS  (SELECT  * FROM  services_Assigns S WHERE s.sin = h.sin and s.floorNo = f.floorNo))");
                ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM housekeeper2 h WHERE  NOT EXISTS  (SELECT  * FROM  floor F WHERE  NOT EXISTS  (SELECT  * FROM  services_Assigns S WHERE s.sin = h.sin and s.floorNo = f.floorNo))");
                countrs.next();


            }
            catch(SQLException vre1)
            {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }


    private static class viewDeleteHousekeeper implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String givenSin = sinText.getText();

            try {
                if (Database.getInstance().deleteHousekeeper(Integer.parseInt(givenSin))) {
                    JOptionPane.showMessageDialog(null, "Successfully deleted housekeeper with " + givenSin ,"Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete housekeeper with " + givenSin, "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                ResultSet rs = con.createStatement().executeQuery("SELECT  * FROM housekeeper2");
                ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM housekeeper2");
                countrs.next();

                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "List of Housekeepers", Arrays.asList("Wage", "Cleaning Speciality", "PhoneNo", "Name", "SIN"), Arrays.asList("WAGE", "CLEANINGSPECIALITY", "PHONENO", "NAME", "SIN"));



            }
            catch(SQLException vre1)
            {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewDeleteRoom implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String givenRoom = roomText.getText();

            try {
                if (Database.getInstance().deleteRoomNo(Integer.parseInt(givenRoom))) {
                    JOptionPane.showMessageDialog(null, "Successfully deleted roomNo " + givenRoom ,"Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete roomNo " + givenRoom, "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                ResultSet rs = con.createStatement().executeQuery("SELECT  * FROM reserve_room_has_floor2");
                ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM reserve_room_has_floor2");
                countrs.next();

                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Housekeeper assigned to all floors", Arrays.asList("RoomNo", "Type", "Flor", "Guest", "BookingNo", "From date", "To date", "Beds"), Arrays.asList("ROOMNO", "TYPEOFROOM", "FLOORNO", "GUSERID", "BOOKINGNO", "FROMDATE", "TODATE", "NUMOFBEDS"));


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
                ResultSet rs = con.createStatement().executeQuery("select * from res");
            }
            catch(SQLException vre1)
            {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Fail", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewServicesAssigns implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {

                ResultSet rs = con.createStatement().executeQuery("SELECT  * FROM services_Assigns");
                ResultSet countrs = con.createStatement().executeQuery("SELECT COUNT(*) FROM services_Assigns");
                countrs.next();

                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Services Assigned", Arrays.asList("SIN", "Floor", "Manager"), Arrays.asList("SIN", "FLOORNO", "MUSERID"));
            }
            catch(SQLException vre1)
            {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
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


    /*private static class seeAllReservations implements ActionListener
    {

    }*/
}
