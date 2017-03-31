package GUI;

/**
 * Created by Matthew on 2017-03-22.
 */
import database.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
        frame.setSize(800, 450);
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
        loginButton.setMnemonic(KeyEvent.VK_ENTER);
        loginButton.addActionListener(new GetIn());
        panel.add(loginButton);

        frame.setVisible(true);
    }

    private static class GetIn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Connection con = loginConnection.getConnection();
            try
            { //we know, we know, storing plaintext passwords in the database is bad
                ResultSet rs = con.createStatement().executeQuery("SELECT userid, name from users where username ='" + username.getText() + "' and password ='" + password.getText() + "'");
                if(rs.next())
                { //there exists a user with such userid and password
                    int userid = rs.getInt("userid");
                    System.out.println("Username " + username.getText() + " has id " + userid);
                    ResultSet checkMan = con.createStatement().executeQuery("SELECT * from manager where userid =" + userid);
                    //JOptionPane.showMessageDialog(frame, "Welcome " + rs.getString("name") + ", you have access level " + ((isAManglement) ? "Manager" : "Customer"), "Login successful", JOptionPane.PLAIN_MESSAGE);
                    HomeScreen hs = new HomeScreen(userid, checkMan.next());
                    frame.dispose();
                }
                else
                { //give vague error
                    JOptionPane.showMessageDialog(frame, "Username or password is incorrect", "Login failed", JOptionPane.ERROR_MESSAGE);
                    //JOptionPane.showMessageDialog(frame, "Wrong", "Error success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch (Exception saf)
            {
                System.out.println(saf.getMessage());
            }
        }
    }

}

