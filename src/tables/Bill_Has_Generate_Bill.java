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
    private java.sql.Date dayOfBill;
    private int gUserid;
    private int roomNo;
    private int mUserid;


    public Bill_Has_Generate_Bill(float amountDue, int billId, float amountPaid, java.sql.Date dateOfBill, int gUserid, int roomNo, int mUserid){
        this.amountDue = amountDue;
        this.billId = billId;
        this.amountPaid = amountPaid;
        this.dayOfBill = dateOfBill;
        this.gUserid = gUserid;
        this.roomNo = roomNo;
        this.mUserid = mUserid;
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

    public void setDateOfBill(java.sql.Date dayOfBill) {
        this.dayOfBill = dayOfBill;
    }

    public void setgUserid(int gUserid) {
        this.gUserid = gUserid;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public void setcUserid(int cUserid) {
        this.mUserid = mUserid;
    }


    public float getAmountDue(){ return amountDue;}

    public int getBillId() {
        return billId;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public Date getDateOfBill() {
        return dayOfBill;
    }

    public int getgUserid() {
        return gUserid;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public int getmUserid() {
        return mUserid;
    }

}
