package tables;

/**
 * Created by Matthew on 2017-03-22.
 */
public class Floor implements Table {
    private int floorNo;

    public Floor(int floorNo){
        this.floorNo = floorNo;
    }

    public void setFloorNo(int floorNo){ this.floorNo = floorNo; }

    public int getFloorNo(){ return floorNo; }
}
