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
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class ResultDisplay {

    private static int WIDTH = 1000;
    private static int HEIGHT = 650;

    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel schedulePanel;

    public ResultDisplay(ResultSet results, String title, List<String> friendlyNames, List<String> realNames){

        frame = new JFrame("Available Rooms");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        mainPanel = new JPanel();
        frame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel overlaySchedulesPanel = new JPanel();
        mainPanel.add(overlaySchedulesPanel);
        overlaySchedulesPanel.setLayout(new BoxLayout(overlaySchedulesPanel, BoxLayout.X_AXIS));

        schedulePanel = new JPanel ();
        schedulePanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Schedule",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        Object[][] rowData = new Object[7][3];

        Object coln[] = { "Room Number", "Type", "Floor Number", "Number of Beds" } ;
        JTable timetable = new JTable(rowData, coln);
        JScrollPane scrollPane = new JScrollPane(timetable);
        schedulePanel.add(scrollPane, BorderLayout.CENTER);
        overlaySchedulesPanel.add(schedulePanel);
        frame.setVisible(true);
    }
}

