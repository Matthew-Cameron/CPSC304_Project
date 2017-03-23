package tables;

/**
 * Created by Matthew on 2017-03-22.
 */
public class Discounts implements Table {

    private int amount;
    private int billid;
    private int mUserid;

    public Discounts(int amount, int billid, int mUserid){
        this.amount = amount;
        this.mUserid = mUserid;
        this.billid = billid;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public int getBillid() {
        return billid;
    }

    public void setmUserid(int mUserid) {
        this.mUserid = mUserid;
    }

    public int getmUserid() {
        return mUserid;
    }
}
