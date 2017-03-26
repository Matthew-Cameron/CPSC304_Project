package tables;

import java.sql.Timestamp;

/**
 * Created by Matthew on 2017-03-23.
 */
public class Reserve_Room_Has_Floor2 implements Table {

    private int gUserId;
    private Timestamp toDate;
    private Timestamp fromDate;
    private int bookingNo;
    private int floorNo;
    private int roomNo;
    private String typeOfRoom;
    private int numOfBeds;

    public Reserve_Room_Has_Floor2(int gUserId, Timestamp toDate, Timestamp fromDate, int bookingNo, int floorNo, int roomNo, String typeOfRoom, int numOfBeds){
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

    public void setToDate(Timestamp toDate){ this.toDate = toDate; }

    public void setFromDate(Timestamp fromDate){ this.fromDate = fromDate; }

    public void setBookingNo(int bookingNo){ this.bookingNo = bookingNo; }

    public void setFloorNo(int floorNo){ this.gUserId = floorNo; }

    public void setRoomNo(int roomNo){ this.roomNo = roomNo; }

    public void setType(String typeOfRoom){ this.typeOfRoom = typeOfRoom; }
    
    public void setNumofBeds(int roomNo){ this.numOfBeds = numOfBeds; }

    public int getgUserId(){ return gUserId; }

    public Timestamp getToDate(){ return toDate; }

    public Timestamp getFromDate(){ return fromDate; }

    public int getBookingNo(){ return bookingNo; }

    public int getFloorNo(){ return floorNo; }

    public int getRoomNo(){ return roomNo; }

    public String getTypeOfRoom(){ return typeOfRoom; }

     public int getNumOfBeds(){ return numOfBeds; }
}
