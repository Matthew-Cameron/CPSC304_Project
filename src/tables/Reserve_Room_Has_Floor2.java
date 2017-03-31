package tables;

import java.sql.Date;

/**
 * Created by Matthew on 2017-03-23.
 */
public class Reserve_Room_Has_Floor2 implements Table {

    private int gUserId;
    private Date toDate;
    private Date fromDate;
    private int bookingNo;
    private int floorNo;
    private int roomNo;
    private String typeOfRoom;
    private int numOfBeds;

    public Reserve_Room_Has_Floor2(int gUserId, Date toDate, Date fromDate, int bookingNo, int floorNo, int roomNo, String typeOfRoom, int numOfBeds){
        this.gUserId = gUserId;
        this.toDate = toDate;
        this.fromDate = fromDate;
        this.bookingNo = bookingNo;
        this.floorNo = floorNo;
        this.roomNo = roomNo;
        this.typeOfRoom = typeOfRoom;
        this.numOfBeds = numOfBeds;
    }

    public void setgUserId(int gUserId){ this.gUserId = gUserId; }

    public void setToDate(Date toDate){ this.toDate = toDate; }

    public void setFromDate(Date fromDate){ this.fromDate = fromDate; }

    public void setBookingNo(int bookingNo){ this.bookingNo = bookingNo; }

    public void setFloorNo(int floorNo){ this.gUserId = floorNo; }

    public void setRoomNo(int roomNo){ this.roomNo = roomNo; }

    public void setType(String typeOfRoom){ this.typeOfRoom = typeOfRoom; }
    
    public void setNumofBeds(int roomNo){ this.numOfBeds = numOfBeds; }

    public int getgUserId(){ return gUserId; }

    public Date getToDate(){ return toDate; }

    public Date getFromDate(){ return fromDate; }

    public int getBookingNo(){ return bookingNo; }

    public int getFloorNo(){ return floorNo; }

    public int getRoomNo(){ return roomNo; }

    public String getTypeOfRoom(){ return typeOfRoom; }

     public int getNumOfBeds(){ return numOfBeds; }
}
