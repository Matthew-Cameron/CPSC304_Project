package tables;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Janet on 2017-03-23.
 */
public class Stays_In implements Table {

    private Timestamp fromDate;
    private Timestamp toDate;
    private int roomNo;
    private int gUserid;

    public Stays_In(Timestamp fromDate, Timestamp toDate, int roomNo, int gUserid){
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.roomNo = roomNo;
        this.gUserid = gUserid;

    }

    public void setgUserId(int gUserid) {
        this.gUserid = gUserid;
    }

    public void setFloorNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public int getmUserId() {
        return mUserid;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public Date getToDate() {
        return toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }
}
