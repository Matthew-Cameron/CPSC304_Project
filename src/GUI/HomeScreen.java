package GUI;

/**
 * Created by Matthew on 2017-03-26.
 */

import database.Database;
import tables.Guest;
import tables.Manager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

public class HomeScreen {

    static String col = "";
    static String[] choices = { "BILLID","MUSERID", "AMOUNT", "BILLID,MUSERID", "BILLID,AMOUNT", "MUSERID,AMOUNT", "BILLID,MUSERID,AMOUNT"};
    static String project;
    static final JComboBox<String> cb = new JComboBox<String>(choices);
    static String sym = "";
    static String[] choices1 = { "AMOUNT =","AMOUNT >", "AMOUNT <" };
    static final JComboBox<String> cb1 = new JComboBox<String>(choices1);
    static int userid;
    static Boolean isM;


    private static int WIDTH = 1000;
    private static int HEIGHT = 650;
    private static Connection con;

    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel buttonPanel;
    private static JPanel billPanel;
    private static JPanel membershipBillPanel;
    private static JPanel selectionPanel;
    private static JPanel informationPanel;
    private static JPanel simpleOpsPanel;
    private static JPanel guestDeletionPanel;
    private static JPanel makeReservationPanel;
    private static JPanel updateDiscountPanel;
    private static JTextField sinText;
    private static JTextField roomText;
    private static JTextField hireText;
    private static JTextField projText;
    private static JTextField roomIdText;
    private static JTextField fromText;
    private static JTextField toText;

    private static JTextField roomDeletedText;


    private static JTextField billIdText;
    private static JTextField discountText;

    // Constructs homescreen for a manager
    public HomeScreen(int userId, boolean isManager) {
        isM = isManager;
        con = Database.getInstance().getConnection();
        if (isManager) {
            try {
                ResultSet rs = con.createStatement().executeQuery("SELECT * from Users where userid ='" + userId + "'");
                rs.next();
                userid = rs.getInt("USERID");
                String tempusername = rs.getString("USERNAME");
                String tempname = rs.getString("NAME");
                String tempphone = rs.getString("PHONENO");

                Manager managerUser = new Manager(tempname, tempphone, userid, tempusername, null, 0);
                System.out.println("Manager: " + managerUser.getName());

                generateStructure();

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

                JButton findHousekeepersAssignedToAllRoom = new JButton("Housekeepers assigned to all floors");
                findHousekeepersAssignedToAllRoom.addActionListener(new findHousekeepersAssignedToAllRoom());
                buttonPanel.add(findHousekeepersAssignedToAllRoom);

                JLabel sinLabel = new JLabel("Delete housekeeper with SIN");
                sinLabel.setBounds(200, 100, 80, 25);
                simpleOpsPanel.add(sinLabel);
                sinText = new JTextField(7);
                sinText.setBounds(100, 40, 50, 25);
                simpleOpsPanel.add(sinText);
                JButton submitHButton = new JButton();
                submitHButton.setText("Submit");
                submitHButton.addActionListener(new viewDeleteHousekeeper());
                simpleOpsPanel.add(submitHButton);
                JButton viewSer = new JButton("View Services Assigned");
                viewSer.addActionListener(new viewServicesAssigns());
                buttonPanel.add(viewSer);

                JLabel roomNoLabel = new JLabel("Delete room with roomNo:");
                roomNoLabel.setBounds(200, 100, 80, 25);
                simpleOpsPanel.add(roomNoLabel);
                roomText = new JTextField(7);
                roomText.setBounds(100, 40, 50, 25);
                simpleOpsPanel.add(roomText);
                JButton submitRButton = new JButton();
                submitRButton.setText("Submit");
                submitRButton.addActionListener(new viewDeleteRoom());
                simpleOpsPanel.add(submitRButton);

                JLabel addHouseLabel = new JLabel("Hire housekeeper with SIN");
                addHouseLabel.setBounds(200, 100, 80, 25);
                simpleOpsPanel.add(addHouseLabel);
                hireText = new JTextField(7);
                hireText.setBounds(100, 40, 50, 25);
                simpleOpsPanel.add(hireText);
                JButton addHouseButton = new JButton();
                addHouseButton.setText("Submit");
                addHouseButton.addActionListener(new hireHousekeeper());
                simpleOpsPanel.add(addHouseButton);

                JButton seeUnpaidBills = new JButton("Unpaid Bills");
                seeUnpaidBills.addActionListener(new viewUnpaidBills());
                billPanel.add(seeUnpaidBills);
                JButton discountedBills = new JButton("Discounted Bills");
                discountedBills.addActionListener(new viewDiscountedBills());
                billPanel.add(discountedBills);
                JButton billLarge = new JButton("Largest Bill");
                billLarge.addActionListener(new viewLargeBill());
                billPanel.add(billLarge);
                JButton billSmall = new JButton("Smallest Bill");
                billSmall.addActionListener(new viewSmallBill());
                billPanel.add(billSmall);
                JButton billavg = new JButton("Average Bill");
                billavg.addActionListener(new viewAvgBill());
                billPanel.add(billavg);
                JButton billTotal = new JButton("Total Bill");
                billTotal.addActionListener(new viewTotalBill());
                billPanel.add(billTotal);
                JButton billNum = new JButton("Number of Bills");
                billNum.addActionListener(new viewNumBill());
                billPanel.add(billNum);
                JButton genBill = new JButton("Generate Bills");
                genBill.addActionListener(new generateBill());
                billPanel.add(genBill);
                JButton maxAvgMembership = new JButton("Membership with the maximum average amount paid per bill");
                maxAvgMembership.addActionListener(new viewMaxAvgMembership());
                membershipBillPanel.add(maxAvgMembership);
                JButton minAvgMembership = new JButton("Membership with the minimum average amount paid per bill");
                minAvgMembership.addActionListener(new viewMinAvgMembership());
                membershipBillPanel.add(minAvgMembership);
                JButton lowestPaidHouseKeeper = new JButton("Lowest Paid HouseKeeper");
                lowestPaidHouseKeeper.addActionListener(new viewLowestPaidHouseKeeper());
                buttonPanel.add(lowestPaidHouseKeeper);

                JLabel lbl = new JLabel("Select from DISCOUNTS");
                lbl.setVisible(true);
                selectionPanel.add(lbl);
                cb.setVisible(true);
                selectionPanel.add(cb);
                selectionPanel.add(cb1);
                projText = new JTextField(7);
                projText.setBounds(100, 40, 100, 25);
                selectionPanel.add(projText);
                JButton btn = new JButton("OK");
                btn.addActionListener(new viewSelectionQuery());
                selectionPanel.add(btn);

                JLabel lbl2 = new JLabel("Update Bill with Id:");
                lbl2.setVisible(true);
                updateDiscountPanel.add(lbl2);
                billIdText = new JTextField(7);
                billIdText.setBounds(100, 40, 100, 25);
                updateDiscountPanel.add(billIdText);
                JLabel lbl3 = new JLabel("with new discount:");
                lbl3.setVisible(true);
                updateDiscountPanel.add(lbl3);
                discountText = new JTextField(7);
                discountText.setBounds(100, 40, 100, 25);
                updateDiscountPanel.add(discountText);
                JButton btn1 = new JButton("OK");
                btn1.addActionListener(new viewDiscountBillQuery());
                updateDiscountPanel.add(btn1);

                JButton logout = new JButton("Logout");
                logout.addActionListener(new viewLoginScreen());
                mainPanel.add(logout);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else //Constructs homescreen for Guest
        {
            try {
                ResultSet rs = con.createStatement().executeQuery("SELECT * from Users u, Guest g where u.userid ='" + userId + "' and g.userid ='" + userId + "'");
                rs.next();
                userid = rs.getInt("USERID");
                String tempusername = rs.getString("USERNAME");
                String tempname = rs.getString("NAME");
                String tempphone = rs.getString("PHONENO");
                String tempmembershipType = rs.getString("MEMBERSHIPTYPE");
                String tempaddress = rs.getString("HOMEADDRESS");
                int tempnightsStayedBefore = rs.getInt("NIGHTS_STAYED_BEFORE");
                Guest guestUser = new Guest(tempname, tempphone, userid, tempusername, null, tempmembershipType, tempaddress, tempnightsStayedBefore);
                System.out.println("Guest: " + guestUser.getName());
                generateStructure();

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

                
                JButton viewRooms = new JButton("View Available Rooms");
                viewRooms.addActionListener(new viewRooms());
                buttonPanel.add(viewRooms);
                JButton viewRes = new JButton("View Reservation");
                viewRes.addActionListener(new viewReservation());
                buttonPanel.add(viewRes);
                JButton deleteRes = new JButton("Delete All Reservations");
                deleteRes.addActionListener(new viewReservationAfterDeletion());
                buttonPanel.add(deleteRes);

                JLabel deleteRoomLabel = new JLabel("Delete Your Reservation RoomNo:");
                deleteRoomLabel.setBounds(200, 100, 80, 25);
                simpleOpsPanel.add(deleteRoomLabel);
                roomDeletedText = new JTextField(7);
                roomDeletedText.setBounds(100, 40, 50, 25);
                simpleOpsPanel.add(roomDeletedText);
                JButton deleteResButton = new JButton();
                deleteResButton.setText("Submit");
                deleteResButton.addActionListener(new viewReservationAfterOneDeletion());
                simpleOpsPanel.add(deleteResButton);

                JLabel lbl2 = new JLabel("Reserve room number:");
                lbl2.setVisible(true);
                makeReservationPanel.add(lbl2);
                roomIdText = new JTextField(7);
                roomIdText.setBounds(100, 40, 100, 25);
                makeReservationPanel.add(roomIdText);
                JLabel lbl3 = new JLabel("from date (YYYY-MM-DD):");
                lbl3.setVisible(true);
                makeReservationPanel.add(lbl3);
                fromText = new JTextField(7);
                fromText.setBounds(100, 40, 100, 25);
                makeReservationPanel.add(fromText);
                JLabel lbl4 = new JLabel("to date (YYYY-MM-DD):");
                lbl4.setVisible(true);
                makeReservationPanel.add(lbl4);
                toText = new JTextField(7);
                toText.setBounds(100, 40, 100, 25);
                makeReservationPanel.add(toText);
                JButton btn2 = new JButton("OK");
                btn2.addActionListener(new viewMakeReservationQuery());
                makeReservationPanel.add(btn2);

                JButton logout = new JButton("Logout");
                logout.addActionListener(new viewLoginScreen());
                mainPanel.add(logout);


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        frame.setVisible(true);
    }

    // Creates layout of homescreen
    private void generateStructure() {

        frame = new JFrame("Hotel");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        mainPanel = new JPanel();
        frame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        informationPanel = new JPanel();
        mainPanel.add(informationPanel);
        informationPanel.setLayout(new GridLayout(2, 5, 8, 8));
        informationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "User Information",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        simpleOpsPanel = new JPanel();
        mainPanel.add(simpleOpsPanel);
        simpleOpsPanel.setLayout(new GridLayout(3, 3, 8, 8));
        simpleOpsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Simple Operations",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Buttons",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        if (isM){
            billPanel = new JPanel();
            mainPanel.add(billPanel);
            billPanel.setLayout(new FlowLayout());
            billPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    "Bills",
                    TitledBorder.CENTER,
                    TitledBorder.TOP));

            membershipBillPanel = new JPanel();
            mainPanel.add(membershipBillPanel);
            membershipBillPanel.setLayout(new FlowLayout());
            membershipBillPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    "Membership Operations",
                    TitledBorder.CENTER,
                    TitledBorder.TOP));

            selectionPanel = new JPanel();
            mainPanel.add(selectionPanel);
            selectionPanel.setLayout(new FlowLayout());
            selectionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    "Select/Project from Discounts",
                    TitledBorder.CENTER,
                    TitledBorder.TOP));

            updateDiscountPanel = new JPanel();
            mainPanel.add(updateDiscountPanel);
            updateDiscountPanel.setLayout(new FlowLayout());
            updateDiscountPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    "Update Bill Discount",
                    TitledBorder.CENTER,
                    TitledBorder.TOP));
        }else{
            makeReservationPanel = new JPanel();
            mainPanel.add(makeReservationPanel);
            makeReservationPanel.setLayout(new FlowLayout());
            makeReservationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                    "Make Reservation",
                    TitledBorder.CENTER,
                    TitledBorder.TOP));
        }
    }

    //Button actions from here on

    // #1 Selection and projection query
    private static class viewUnpaidBills implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = con.createStatement().executeQuery("select GUSERID, AMOUNTPAID, AMOUNTDUE from BILL_HAS_GENERATE_BILL where AMOUNTPAID < AMOUNTDUE");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from BILL_HAS_GENERATE_BILL where AMOUNTPAID < AMOUNTDUE");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Unpaid Bills", Arrays.asList("Guest ID", "Amount Paid", "Amount Due"), Arrays.asList("GUSERID", "AMOUNTPAID", "AMOUNTDUE"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    // #1 Selection query
    private static class viewSelectionQuery implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            col = cb.getSelectedItem().toString();
            sym = cb1.getSelectedItem().toString();
            String givenAmount = projText.getText();
            try {
                int num = Integer.parseInt(givenAmount);
                try {
                    ResultSet rs = con.createStatement().executeQuery("select " + col + " from DISCOUNTS where " + sym + " " + givenAmount);

                    ResultSet countrs = con.createStatement().executeQuery("select count(*) from DISCOUNTS where " + sym + " " + givenAmount);
                    countrs.next();
                    ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "DISCOUNTS " + col, Arrays.asList(col.split(",")), Arrays.asList(col.split(",")));
                } catch (SQLException vre1) {
                    JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                    System.out.println(vre1.getMessage());
                    System.out.println(Arrays.toString(vre1.getStackTrace()));
                }
            }
            catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "Did not input an integer ", "Fail", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private static class viewMaxAvgMembership implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = con.createStatement().executeQuery("select * from (select g.membershiptype, round(avg(b.amountdue),0) from guest g, BILL_HAS_GENERATE_BILL b where g.USERID = b.GUSERID group by membershiptype order by round(avg(b.amountdue),0) desc) where rownum = 1");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from (select avg(amountdue) from BILL_HAS_GENERATE_BILL)");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Membership with the maximum average amount paid per bill", Arrays.asList("Membership", "Average Bill Amount"), Arrays.asList("MEMBERSHIPTYPE", "ROUND(AVG(B.AMOUNTDUE),0)"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewMinAvgMembership implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = con.createStatement().executeQuery("select * from (select g.membershiptype, round(avg(b.amountdue),0) from guest g, BILL_HAS_GENERATE_BILL b where g.USERID = b.GUSERID group by membershiptype order by round(avg(b.amountdue),0) asc) where rownum = 1");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from (select avg(amountdue) from BILL_HAS_GENERATE_BILL)");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Membership with the minimum average amount paid per bill", Arrays.asList("Membership", "Average Bill Amount"), Arrays.asList("MEMBERSHIPTYPE", "ROUND(AVG(B.AMOUNTDUE),0)"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewDiscountedBills implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = con.createStatement().executeQuery("select b.BILLID, b.GUSERID, b.amountdue, d.AMOUNT from bill_has_generate_bill b inner join DISCOUNTS d on b.BILLID = d.BILLID");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from bill_has_generate_bill b inner join DISCOUNTS d on b.BILLID = d.BILLID");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Discounted Bills", Arrays.asList("Bill ID", "Guest ID", "Amount Due", "Discount"), Arrays.asList("BILLID", "GUSERID", "AMOUNTDUE", "AMOUNT"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewLargeBill implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = con.createStatement().executeQuery("select b1.billid, b1.guserid, b1.amountdue from BILL_HAS_GENERATE_BILL b1 where b1.amountdue = (select max(b2.amountdue) from BILL_HAS_GENERATE_BILL b2)");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from (select max(amountdue) from BILL_HAS_GENERATE_BILL)");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Largest Bill", Arrays.asList("Bill ID", "Guest ID", "Amount Due"), Arrays.asList("BILLID", "GUSERID", "AMOUNTDUE"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewSmallBill implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = con.createStatement().executeQuery("select b1.billid, b1.guserid, b1.amountdue from BILL_HAS_GENERATE_BILL b1 where b1.amountdue = (select min(b2.amountdue) from BILL_HAS_GENERATE_BILL b2)");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from (select min(amountdue) from BILL_HAS_GENERATE_BILL)");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Smallest Bill", Arrays.asList("Bill ID", "Guest ID", "Amount Due"), Arrays.asList("BILLID", "GUSERID", "AMOUNTDUE"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewAvgBill implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = con.createStatement().executeQuery("select round(avg(amountdue),0) from BILL_HAS_GENERATE_BILL");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from (select avg(amountdue) from BILL_HAS_GENERATE_BILL)");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Average Bill", Arrays.asList("Average Amount Due"), Arrays.asList("ROUND(AVG(AMOUNTDUE),0)"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewTotalBill implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = con.createStatement().executeQuery("select sum(amountdue) from BILL_HAS_GENERATE_BILL");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from (select count(amountdue) from BILL_HAS_GENERATE_BILL)");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Total Bill Amount", Arrays.asList("Total Bill Amount"), Arrays.asList("SUM(AMOUNTDUE)"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewNumBill implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = con.createStatement().executeQuery("select count(*) from BILL_HAS_GENERATE_BILL");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from (select count(*) from BILL_HAS_GENERATE_BILL)");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Number of Bills", Arrays.asList("Number of Bills"), Arrays.asList("COUNT(*)"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    // #4 Aggregation query
    private static class viewLowestPaidHouseKeeper implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = con.createStatement().executeQuery("select * from Housekeeper2 where wage = (select min(wage) from HOUSEKEEPER2)");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from Housekeeper2 where wage = (select min(wage) from HOUSEKEEPER2)");
                countrs.next();
                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Lowest Paid HouseKeeper", Arrays.asList("Wage", "Job", "Phone", "Name", "SIN"), Arrays.asList("WAGE", "CLEANINGSPECIALITY", "PHONENO", "NAME", "SIN"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewRooms implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                ResultSet rs = con.createStatement().executeQuery("select r2.roomno, r2.typeofroom, r2.floorno, r2.numofbeds, r1.cost from reserve_room_has_floor2 r2, reserve_room_has_floor1 r1 where r2.guserid is null and r1.typeofroom = r2.typeofroom");
                ResultSet countrs = con.createStatement().executeQuery("select count(*) from reserve_room_has_floor2 where guserid is null");
                countrs.next();

                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Rooms Available", Arrays.asList("Room Number", "Type of Room", "Floor No", "Num of Beds", "Price"), Arrays.asList("ROOMNO", "TYPEOFROOM", "FLOORNO", "NUMOFBEDS", "COST"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class findHousekeepersAssignedToAllRoom implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                ResultSet rs = con.createStatement().executeQuery("SELECT  * FROM housekeeper2 h WHERE  NOT EXISTS  (SELECT  * FROM  floor F WHERE  NOT EXISTS  (SELECT  * FROM  services_Assigns S WHERE s.sin = h.sin and s.floorNo = f.floorNo))");
                ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM housekeeper2 h WHERE  NOT EXISTS  (SELECT  * FROM  floor F WHERE  NOT EXISTS  (SELECT  * FROM  services_Assigns S WHERE s.sin = h.sin and s.floorNo = f.floorNo))");
                countrs.next();

                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "List of Housekeepers", Arrays.asList("Wage", "Cleaning Speciality", "PhoneNo", "Name", "SIN"), Arrays.asList("WAGE", "CLEANINGSPECIALITY", "PHONENO", "NAME", "SIN"));



            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }


    private static class viewDeleteHousekeeper implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String givenSin = sinText.getText();

            try {
                int num = Integer.parseInt(givenSin);
                try {
                    if (Database.getInstance().deleteHousekeeper(num)) {
                        JOptionPane.showMessageDialog(null, "Successfully deleted housekeeper with " + givenSin, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete housekeeper with " + givenSin, "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                    ResultSet rs = con.createStatement().executeQuery("SELECT  * FROM housekeeper2");
                    ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM housekeeper2");
                    countrs.next();

                    ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "List of Housekeepers", Arrays.asList("Wage", "Cleaning Speciality", "PhoneNo", "Name", "SIN"), Arrays.asList("WAGE", "CLEANINGSPECIALITY", "PHONENO", "NAME", "SIN"));


                } catch (SQLException vre1) {
                    JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                    System.out.println(vre1.getMessage());
                    System.out.println(Arrays.toString(vre1.getStackTrace()));
                }

            } catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "Did not input an integer ", "Fail", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }

    private static class viewDiscountBillQuery implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String billid = billIdText.getText();
            String amount = discountText.getText();


            try {
                if (Database.getInstance().updateDiscount(amount, billid, userid)) {
                    JOptionPane.showMessageDialog(null, "Successfully updated bill with id " + billid, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update bill with id " + billid, "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM discounts");
                ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM discounts");
                countrs.next();

                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Discounts", Arrays.asList("Amount", "Bill Id", "Manager Id"), Arrays.asList("AMOUNT", "BILLID", "MUSERID"));


            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewMakeReservationQuery implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String roomid = roomIdText.getText();
            String from = fromText.getText();
            String to = toText.getText();
            String gid = Integer.toString(userid);
            Random rand = new Random();
            String bnum = Integer.toString(rand.nextInt(999) + 1);


            try {
                if (Database.getInstance().makeReservation(roomid, from, to, gid, bnum)) {
                    JOptionPane.showMessageDialog(null, "Successfully reserved room " + roomid, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to reserve room " + roomid, "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                ResultSet rs = con.createStatement().executeQuery("SELECT * FROM RESERVE_ROOM_HAS_FLOOR2 where GUSERID = " + gid);
                ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM RESERVE_ROOM_HAS_FLOOR2 where GUSERID = " + gid);
                countrs.next();

                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Reservations", Arrays.asList("Room", "Type", "Floor Number", "Guest Id", "Booking Number", "From", "To", "Number of Beds"), Arrays.asList("ROOMNO", "TYPEOFROOM", "FLOORNO", "GUSERID", "BOOKINGNO", "FROMDATE", "TODATE", "NUMOFBEDS"));


            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }


    private static class viewDeleteRoom implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String givenRoom = roomText.getText();
            try {
                int num = Integer.parseInt(givenRoom);

                try {
                    if (Database.getInstance().deleteRoomNo(num)) {
                        JOptionPane.showMessageDialog(null, "Successfully deleted roomNo " + givenRoom, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete roomNo " + givenRoom, "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                    ResultSet rs = con.createStatement().executeQuery("SELECT  * FROM reserve_room_has_floor2");
                    ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM reserve_room_has_floor2");
                    countrs.next();

                    ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Rooms", Arrays.asList("RoomNo", "Type", "Floor", "Guest", "BookingNo", "From date", "To date", "Beds"), Arrays.asList("ROOMNO", "TYPEOFROOM", "FLOORNO", "GUSERID", "BOOKINGNO", "FROMDATE", "TODATE", "NUMOFBEDS"));


                } catch (SQLException vre1) {
                    JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                    System.out.println(vre1.getMessage());
                    System.out.println(Arrays.toString(vre1.getStackTrace()));
                }
            } catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "Did not input an integer ", "Fail", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private static class viewReservationAfterOneDeletion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String givenRoom = roomDeletedText.getText();
            try {
                int num = Integer.parseInt(givenRoom);

                try {
                    if (Database.getInstance().deleteRoomNo(num)) {
                        JOptionPane.showMessageDialog(null, "Successfully deleted reservation in " + givenRoom, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete reservation in" + givenRoom, "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                    ResultSet rs = con.createStatement().executeQuery("SELECT  * FROM reserve_room_has_floor2 where guserId = " + userid);
                    ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM reserve_room_has_floor2 where guserId = " + userid);
                    countrs.next();

                    ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Rooms", Arrays.asList("RoomNo", "Type", "Floor", "Guest", "BookingNo", "From date", "To date", "Beds"), Arrays.asList("ROOMNO", "TYPEOFROOM", "FLOORNO", "GUSERID", "BOOKINGNO", "FROMDATE", "TODATE", "NUMOFBEDS"));


                } catch (SQLException vre1) {
                    JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                    System.out.println(vre1.getMessage());
                    System.out.println(Arrays.toString(vre1.getStackTrace()));
                }
            } catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "Did not input an integer ", "Fail", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private static class viewReservationAfterDeletion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

                try {
                    if (Database.getInstance().deleteAllReservation(userid)) {
                        JOptionPane.showMessageDialog(null, "Successfully deleted all reservations " + userid, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,  "You no longer have any reservations " + userid, "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                    ResultSet rs = con.createStatement().executeQuery("SELECT *  FROM reserve_room_has_floor2 where guserid = " + userid);
                    ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM reserve_room_has_floor2 where guserid = " + userid);
                    countrs.next();

                    ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Rooms", Arrays.asList("RoomNo", "Type", "Floor", "Guest", "BookingNo", "From date", "To date", "Beds"), Arrays.asList("ROOMNO", "TYPEOFROOM", "FLOORNO", "GUSERID", "BOOKINGNO", "FROMDATE", "TODATE", "NUMOFBEDS"));


                } catch (SQLException vre1) {
                    JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                    System.out.println(vre1.getMessage());
                    System.out.println(Arrays.toString(vre1.getStackTrace()));
                }
        }
    }



    private static class viewServicesAssigns implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                ResultSet rs = con.createStatement().executeQuery("SELECT  * FROM services_Assigns order by floorno asc");
                ResultSet countrs = con.createStatement().executeQuery("SELECT COUNT(*) FROM services_Assigns");
                countrs.next();

                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Services Assigned", Arrays.asList("SIN", "Floor", "Manager"), Arrays.asList("SIN", "FLOORNO", "MUSERID"));
            } catch (SQLException vre1) {
                JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                System.out.println(vre1.getMessage());
                System.out.println(Arrays.toString(vre1.getStackTrace()));
            }
        }
    }

    private static class viewReservation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ResultSet rs = con.createStatement().executeQuery("SELECT  * FROM reserve_room_has_floor2 where guserId = " + userid);
                ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM reserve_room_has_floor2 where guserId = " + userid);
                countrs.next();

                ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "Rooms", Arrays.asList("RoomNo", "Type", "Floor", "Guest", "BookingNo", "From date", "To date", "Beds"), Arrays.asList("ROOMNO", "TYPEOFROOM", "FLOORNO", "GUSERID", "BOOKINGNO", "FROMDATE", "TODATE", "NUMOFBEDS"));


            } catch (SQLException vre1) {
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

    private static class hireHousekeeper implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String givenSin = hireText.getText();
            try {
                int num = Integer.parseInt(givenSin);

                try {
                    if (Database.getInstance().hireHousekeeper(num)) {
                        JOptionPane.showMessageDialog(null, "Successfully added housekeeper " + givenSin, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add housekeeper ", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                    ResultSet rs = con.createStatement().executeQuery("SELECT  * FROM housekeeper2");
                    ResultSet countrs = con.createStatement().executeQuery("SELECT  COUNT(*) FROM housekeeper2");
                    countrs.next();

                    ResultDisplay rd = new ResultDisplay(rs, countrs.getInt(1), "List of Housekeepers", Arrays.asList("Wage", "Cleaning Speciality", "PhoneNo", "Name", "SIN"), Arrays.asList("WAGE", "CLEANINGSPECIALITY", "PHONENO", "NAME", "SIN"));

                } catch (SQLException vre1) {
                    JOptionPane.showMessageDialog(frame, vre1.getErrorCode() + " " + vre1.getMessage() + '\n', "Error ", JOptionPane.ERROR_MESSAGE);
                    System.out.println(vre1.getMessage());
                    System.out.println(Arrays.toString(vre1.getStackTrace()));
                }
            } catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "Did not input an integer ", "Fail", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    //This is where the fun begins
    private static class generateBill implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Bills();
        }
    }

    private static class viewLoginScreen implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginScreen();
        }
    }
}
