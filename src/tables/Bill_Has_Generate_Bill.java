package tables;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Janet on 2017-03-23.
 */
public class Bill_Has_Generate_Bill implements Table {

    private float amountDue;
    private int billId;
    private float amountPaid;
    private Timestamp day;
    private int gUserid;
    private int roomNo;
    private int cUserid;


    public Bill_Has_Generate_Bill(float amountDue, int billId, float amountPaid, Timestamp day, int gUserid, int roomNo, int cUserid){
        this.amountDue = amountDue;
        this.billId = billId;
        this.amountPaid = amountPaid;
        this.day = day;
        this.gUserid = gUserid;
        this.roomNo = roomNo;
        this.cUserid = cUserid;
    }

    public void setAmountDue(float amountDue) {
        this.amountDue = amountDue;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setDay(Timestamp day) {
        this.day = day;
    }

    public void setgUserid(int gUserid) {
        this.gUserid = gUserid;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public void setcUserid(int cUserid) {
        this.cUserid = cUserid;
    }


    public float getAmountDue(float amountDue){ return amountDue;}

    public int getBillId(int billId) {
        return billId;
    }

    public float getAmountPaid(float amountPaid) {
        return amountPaid;
    }

    public Timestamp getDay(Timestamp day) {
        return day;
    }

    public int getgUserid(int gUserid) {
        return gUserid;
    }

    public int getRoomNo(int roomNo) {
        return roomNo;
    }

    public int getcUserid(int cUserid) {
        return cUserid;
    }

}
