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
    private Timestamp dateOfBill;
    private int gUserid;
    private int roomNo;
    private int mUserid;


    public Bill_Has_Generate_Bill(float amountDue, int billId, float amountPaid, Timestamp dateOfBill, int gUserid, int roomNo, int mUserid){
        this.amountDue = amountDue;
        this.billId = billId;
        this.amountPaid = amountPaid;
        this.dayOfBill = dayOfBill;
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

    public void setDateOfBill(Timestamp dateOfBill) {
        this.day = day;
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

    public Timestamp getDateOfBill() {
        return getDateOfBill;
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
