package GUI;

/**
 * Created by crypt on 2017/03/30.
 */

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDisplay {
    private static int WIDTH = 500;
    private static int HEIGHT = 250;

    private static JFrame frame;
    private static JPanel mainPanel;

    BillDisplay(ResultSet rs) throws SQLException
    {
        frame = new JFrame("Bill");
        mainPanel = new JPanel();
        frame.add(mainPanel);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        rs.next();

        frame.add(new JLabel("Bill ID: " + rs.getString("billid")));
        frame.add(new JLabel("Room Number: " + rs.getString("roomno")));
        frame.add(new JLabel("Guest Name: " + rs.getString("uname")));
        frame.add(new JLabel("Date of Bill: " + rs.getString("timestamp")));
        frame.add(new JLabel("Amount Total: $" + rs.getString("amtttl")));
        frame.add(new JLabel("Amount Paid: $" + rs.getString("amtpd")));
        frame.add(new JLabel("Amount to pay: $" + (rs.getFloat("amtttl") - rs.getFloat("amtpd"))));

        frame.pack();
        frame.setVisible(true);
    }
}
