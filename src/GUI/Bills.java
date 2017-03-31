package GUI;

import tables.Bill_Has_Generate_Bill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by crypt on 2017/03/30.
 */

public class Bills {
    private static int WIDTH = 500;
    private static int HEIGHT = 250;

    private static JFrame frame;
    private static JPanel mainPanel;
    private static JTabbedPane tabs;

    private static JTextField billId;
    private static JTextField gname;
    private static JTextField roomno;


    private static Connection con;
    Bills(Connection con)
    {
        this.con = con;
        frame = new JFrame("Generate Bill");
        tabs = new JTabbedPane();
        frame.add(tabs);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        //choosing to locate a specific bill by one of a few ways
        //directly by an id
        JPanel billIDOnlyPanel = new JPanel(false);
        billIDOnlyPanel.setLayout(new GridLayout(2, 2));
        billIDOnlyPanel.add(new JLabel("Bill ID"));
        billId = new JTextField();
        billIDOnlyPanel.add(billId);

        JPanel super1 = new JPanel();
        super1.setLayout(new GridLayout(2, 1));
        super1.add(billIDOnlyPanel);
        JButton generate1 = new JButton("Generate Bill");
        generate1.addActionListener(new genBill1());
        super1.add(generate1);
        tabs.addTab("Locate by Bill ID", null, super1);

        //using name and room number. will select latest match
        JPanel namePlusRoom = new JPanel(false);
        namePlusRoom.setLayout(new GridLayout(2, 2));
        //escape textbox input?
        namePlusRoom.add(new JLabel("Guest Name"));
        gname = new JTextField();
        namePlusRoom.add(gname);
        namePlusRoom.add(new JLabel("Room No"));
        roomno = new JTextField();
        namePlusRoom.add(roomno);

        JPanel super2 = new JPanel();
        super2.setLayout(new GridLayout(2, 1));
        JButton generate2 = new JButton("Generate Bill");
        generate2.addActionListener(new genBill2());
        super2.add(namePlusRoom);
        super2.add(generate2);
        tabs.addTab("Locate by Name and Room No", null, super2);

        frame.setVisible(true);
    }

    private static class genBill1 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                System.out.println("GenBill: Bill ID: " + billId.getText());
                ResultSet rs = con.createStatement().executeQuery("select b.billid as \"billid\", b.AMOUNTDUE as \"amtttl\", b.amountpaid as \"amtpd\", b.dateofbill as \"timestamp\", u.name as \"uname\", b.roomno as \"roomno\" from BILL_HAS_GENERATE_BILL b inner join USERS u ON b.guserid = u.USERID where b.billid = " + billId.getText().trim() + " order by b.dateofbill desc;");
                if(rs.next())
                {
                    System.out.println("Got to next");
                    new BillDisplay(rs);
                }
                else
                {
                    throw new SQLException("No results found.");
                }
            }
            catch (SQLException gbbe)
            {
                JOptionPane.showMessageDialog(frame, "Unable to find specific bill. Selection criteria is returns more than or less than one result.");
            }
        }
    }

    private static class genBill2 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                System.out.println("GenBill: Name is: " + gname.getText() + " and roomno is: " + roomno.getText());
                ResultSet rs = con.createStatement().executeQuery("select b.billid as \"billid\", b.AMOUNTDUE as \"amtttl\", b.amountpaid as \"amtpd\", b.dateofbill as \"timestamp\", u.name as \"uname\", b.roomno as \"roomno\" from BILL_HAS_GENERATE_BILL b inner join USERS u ON b.guserid = u.USERID where u.name is like '" + gname.getText() + "' and b.roomno = " + roomno.getText() + " order by b.dateofbill desc;");
                if(rs.next())
                {
                    System.out.println("Got to next");
                    new BillDisplay(rs);
                }
                else
                {
                    throw new SQLException("No results found.");
                }
            }
            catch (SQLException gbbe)
            {
                JOptionPane.showMessageDialog(frame, "Unable to find specific bill. Selection criteria is returns more than or less than one result.");
            }
        }
    }
}
