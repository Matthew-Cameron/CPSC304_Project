package tables;

import java.sql.Timestamp;

/**
 * Created by Matthew on 2017-03-23.
 */
public class Reserve_Room_Has_Floor1 implements Table {

    private int cost;
    private String typeOfRoom;

    public Reserve_Room_Has_Floor1(int cost, String typeOfRoom){
        this.cost = cost;
        this.typeOfRoom = typeOfRoom;
    }

    public void setCost(int cost){ this.cost = cost; }

    public void setType(String type){ this.typeOfRoom = type; }
    
    public int getCost(){ return cost; }

    public String getTypeOfRoom(){ return typeOfRoom; }
}
