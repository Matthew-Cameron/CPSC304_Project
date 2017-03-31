package GUI;

/**
 * Created by Matthew on 2017-03-26.
 */
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.ResultSet;
import java.util.List;

public class ResultDisplay {

    private static int WIDTH = 500;
    private static int HEIGHT = 60;

    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel schedulePanel;

    ResultDisplay(ResultSet results, int rsSize, String title, List<String> friendlyNames, List<String> realNames){

        frame = new JFrame(title);
        frame.setSize(WIDTH,100 +(HEIGHT * rsSize));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        mainPanel = new JPanel();
        frame.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel overlaySchedulesPanel = new JPanel();
        mainPanel.add(overlaySchedulesPanel);
        overlaySchedulesPanel.setLayout(new BoxLayout(overlaySchedulesPanel, BoxLayout.X_AXIS));

        schedulePanel = new JPanel ();
        schedulePanel.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                title,
                TitledBorder.CENTER,
                TitledBorder.TOP));
        Object[][] rowData = new Object[rsSize][friendlyNames.size()];

        try{
            int i = 0;
            while(results.next()){
                int j;
                for(j = 0; j < friendlyNames.size(); j++){
                    rowData[i][j] = results.getString(realNames.get(j));
                }
                i++;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        JTable timetable = new JTable(rowData, friendlyNames.toArray());

        JScrollPane scrollPane = new JScrollPane(timetable);
        schedulePanel.add(scrollPane, BorderLayout.CENTER);
        overlaySchedulesPanel.add(schedulePanel);
        frame.setVisible(true);
    }
}

