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

public class HomeScreen {

    private static int WIDTH = 1000;
    private static int HEIGHT = 650;
    private static Database mainConnection;

    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel buttonPanel;
    private static JPanel informationPanel;
    private static JPanel medicalHistoryPanel;
    private static JPanel schedulePanel;
    private static JPanel activityPannel;



    // Constructs homescreen for a manager
    public HomeScreen(int userId, boolean isManager){
        mainConnection = Database.getInstance();
        if(isManager)
        {
            //TODO: given userid, query database for info
            Manager managerUser = null;
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
        }
        else
        {
            //TODO: given userid, query database for info
            Guest guestUser = null;
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

        buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);
        buttonPanel.setLayout(new FlowLayout());

        informationPanel = new JPanel();
        mainPanel.add(informationPanel);
        informationPanel.setLayout(new GridLayout(2, 5 , 8, 8));
        informationPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "User Information",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        medicalHistoryPanel = new JPanel();
        mainPanel.add(medicalHistoryPanel);
        medicalHistoryPanel.setLayout(new GridLayout(2, 5, 8, 8));
        medicalHistoryPanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Medical History",
                TitledBorder.CENTER,
                TitledBorder.TOP));

        JPanel overlaySchedulesPanel = new JPanel();
        mainPanel.add(overlaySchedulesPanel);
        overlaySchedulesPanel.setLayout(new BoxLayout(overlaySchedulesPanel, BoxLayout.X_AXIS));

        schedulePanel = new JPanel ();
        schedulePanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Schedule",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        Object[][] rowData = new Object[7][3];

        Object columnNames[] = { "Day", "Available From", "Available To" } ;
        JTable timetable = new JTable(rowData, columnNames);
        JScrollPane scrollPane = new JScrollPane(timetable);
        schedulePanel.add(scrollPane, BorderLayout.CENTER);
        overlaySchedulesPanel.add(schedulePanel);
    }
}
