package tables;

/**
 * Created by Matthew on 2017-03-22.
 */
public class Guest extends User implements Table{
    private String membershipType;
    private String address;
    private int nightsStayedBefore;

    public Guest(String name, String phone, int userid, String username, String password, String membershipType, String address, int nightsStayedBefore) {
        super(name, phone, userid, username, password);
        this.membershipType = membershipType;
        this.address = address;
        this.nightsStayedBefore = nightsStayedBefore;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNightsStayedBefore(int nightsStayedBefore) {
        this.nightsStayedBefore = nightsStayedBefore;
    }



    public String getMembershipType() {
        return membershipType;
    }

    public String getAddress() {
        return address;
    }

    public int getNightsStayedBefore() {
        return nightsStayedBefore;
    }
}
