package tables;

import java.sql.Timestamp;

/**
 * Created by Matthew on 2017-03-23.
 */
public class Reserve_Room_Has_Floor implements Table {

    private int gUserId;
    private Timestamp toDate;
    private Timestamp fromDate;
    private int bookingNo;
    private int floorNo;

    public Reserve_Room_Has_Floor(int gUserId, Timestamp toDate, Timestamp fromDate, int bookingNo, int floorNo){
        this.gUserId = gUserId;
        this.toDate = toDate;
        this.fromDate = fromDate;
        this.bookingNo = bookingNo;
        this.floorNo = floorNo;
    }

    public void setgUserId(int gUserId){ this.gUserId = gUserId; }

    public void setToDate(Timestamp toDate){ this.toDate = toDate; }

    public void setFromDate(Timestamp fromDate){ this.fromDate = fromDate; }

    public void setBookingNo(int bookingNo){ this.bookingNo = bookingNo; }

    public void setFloorNo(int floorNo){ this.gUserId = floorNo; }
    
    public int getgUserId(){ return gUserId; }

    public Timestamp getToDate(){ return toDate; }

    public Timestamp getFromDate(){ return fromDate; }

    public int getBookingNo(){ return bookingNo; }

    public int getFloorNo(){ return floorNo; }
}
