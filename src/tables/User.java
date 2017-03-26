package tables;

/**
 * Created by Matthew on 2017-03-22.
 */
public class User implements Table{
    int userid;
    String username;
    String password;
    String name;
    String phone;

    public User(String name, String phone, int userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean getType(User guy)
    {
        //is this person a privileged user (ie. Manager)
        //or 'normal' (ie. Customer)
        return true;
    }
}
