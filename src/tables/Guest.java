package tables;

/**
 * Created by Matthew on 2017-03-22.
 */
public class Guest {
    private int userid;

    public Guest(String username, String password, String name, String phonenumber, String email, int userid, String membershipType, String address, int nightsStayedBefore) {
        super(userid, username, password, name, phonenumber);
        this.userid = userid;
    }

    public int getGuestid() {
        return userid;
    }

    public void setGuestid(int userid) {
        this.userid = userid;
    }
}
