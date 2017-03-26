package GUI;

/**
 * Created by Matthew on 2017-03-22.
 */
import database.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

public class LoginScreen {

    private static JFrame frame;

    private static JTextField username;
    private static JTextField password;
    private static Database loginConnection;
    
    public LoginScreen(){
        loginConnection = Database.getInstance();

        frame = new JFrame("Login");
        frame.setSize(800, 450);    //16:9
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel userIDLabel = new JLabel("Username");
        userIDLabel.setBounds(200, 100, 80, 25);
        panel.add(userIDLabel);
        username = new JTextField(20);
        username.setBounds(290, 100, 260, 25);
        panel.add(username);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(200, 150, 80, 25);
        panel.add(passwordLabel);
        password = new JPasswordField(20);
        password.setBounds(290, 150, 260, 25);
        panel.add(password);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(330, 200, 160, 30);
        loginButton.addActionListener(new NewLoginListener());
        panel.add(loginButton);

        frame.setVisible(true);
    }

     //Inner-Class of LoginScreen: listens for actions that are performed on the login button, then returns a value based
     //on the user provided input.
    private static class NewLoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String givenUsername = username.getText();
            String givenPassword = password.getText();

            System.out.print("Got username " + givenUsername + " and password ");
            for (int i = 0; i < givenPassword.length(); i++) {
                System.out.print('*');
            }
            System.out.println();

            Connection con = loginConnection.getConnection();
            try
            {
                ResultSet rs = con.createStatement().executeQuery("SELECT * from user_tables");
                while(rs.next())
                {
                    System.out.println("");
                }
            }
            catch (Exception saf)
            {
                System.out.println(saf.getMessage());
            }

            try
            {

            }
            catch(Exception ef)
            {
                System.out.println(ef.getMessage());
                System.out.println(ef.getStackTrace());
            }
        }
    }

}

