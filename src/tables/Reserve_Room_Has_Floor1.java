package tables;

import java.sql.Timestamp;

/**
 * Created by Matthew on 2017-03-23.
 */
public class Reserve_Room_Has_Floor1 implements Table {

    private int cost;
    private String type;

    public Reserve_Room_Has_Floor1(int cost, String type){
        this.cost = cost;
        this.type = type;
    }

    public void setCost(int cost){ this.cost = cost; }

    public void setType(String type){ this.type = type; }
    
    public int getCost(){ return cost; }

    public String getType(){ return type; }
}
