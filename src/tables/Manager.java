package tables;

/**
 * Created by Matthew on 2017-03-22.
 */
public class Manager extends User implements Table{
    private int wage;

    public Manager(String name, String phone, int userid, String username, String password, int wage) {
        super(name, phone, userid, username, password);
        this.wage = wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getWage() {
        return wage;
    }
}
