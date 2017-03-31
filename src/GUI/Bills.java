package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by crypt on 2017/03/30.
 */

public class Bills {
    private static int WIDTH = 500;
    private static int HEIGHT = 250;

    private static JFrame frame;
    private static JPanel mainPanel;

    private static Connection con;
    Bills(Connection con)
    {
        frame = new JFrame("Generate Bill");
        mainPanel = new JPanel();
        frame.add(mainPanel);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());


        JButton generate = new JButton("Generate Bill");
        mainPanel.add(generate);

        frame.setVisible(true);
    }

    private static class genBill implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                ResultSet rs = con.createStatement().executeQuery("select b.AMOUNTDUE as \"Amount Total\", b.billid as \"Bill ID\", b.amountpaid as \"Amount Paid\", b.dateofbill as \"Timestamp\", u.name as \"Guest\", b.roomno as \"Room Number\" from BILL_HAS_GENERATE_BILL b inner join USERS u ON b.guserid = u.USERID where u.userid = 1111;");
            }
            catch (SQLException gbbe)
            {
                JOptionPane.showMessageDialog(frame, "Unable to find specific bill. Selection criteria is returns more than or less than one result.");
            }
        }
    }
}
