package tables;

/**
 * Created by Matthew on 2017-03-22.
 */
public class User extends Person implements Table{
    private int userid;
    private String username;
    private String password;


    public User(String name, String phone, String email, int userid, String username, String password) {
        super(name, phone, email);
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public void setUserId(int userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
