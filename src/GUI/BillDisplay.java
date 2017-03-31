package GUI;

/**
 * Created by crypt on 2017/03/30.
 */
import tables.Bill_Has_Generate_Bill;

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
        frame.setLayout(new GridLayout(3, 2));

        rs.first();

        frame.add(new JLabel("Room Number: " + rs.getString("roomno")));

        frame.pack();
        frame.setVisible(true);
    }
}
