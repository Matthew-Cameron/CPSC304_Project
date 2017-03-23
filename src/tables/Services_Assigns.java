package tables;

/**
 * Created by Janet on 2017-03-23.
 */
public class Services_Assigns implements Table {

    String phone;
    String name;
    int mUserid;
    int floorNo;


    public Gives_Tasks(String phone, int mUserid, String name, int floorNo){
        this.mUserid = mUserid;
        this.phone = phone;
        this.name = name;
        this.floorNo = floorNo;
    }

    public void setmUserId(int mUserid) {
        this.mUserid = mUserid;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getmUserId() {
        return mUserid;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
