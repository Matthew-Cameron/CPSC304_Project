package tables;

/**
 * Created by Janet on 2017-03-23.
 */
public class Services_Assigns implements Table {

    private int sin;
    private int mUserid;
    private int floorNo;


    public Services_Assigns(int sin, int mUserid,  int floorNo){
        this.sin = sin;
        this.mUserid = mUserid;
        this.floorNo = floorNo;
    }

    public void setmUserId(int mUserid) {
        this.mUserid = mUserid;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

      public void setSin(int sin) {
        this.sin = sin;
    }


  
    public int getmUserId() {
        return mUserid;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public int getSin() {
        return sin;
    }



}
