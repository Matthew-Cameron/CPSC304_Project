package GUI;

/**
 * Created by crypt on 2017/03/30.
 */
import tables.Bill_Has_Generate_Bill;

import javax.swing.*;
import java.awt.*;

public class BillDisplay {
    private static int WIDTH = 500;
    private static int HEIGHT = 250;

    private static JFrame frame;
    private static JPanel mainPanel;

    BillDisplay(Bill_Has_Generate_Bill theBill)
    {
        frame = new JFrame("Bill");
        mainPanel = new JPanel();
        frame.add(mainPanel);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());
    }
}
