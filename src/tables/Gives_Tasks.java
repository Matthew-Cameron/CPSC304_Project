package tables;

/**
 * Created by Matthew on 2017-03-22
 */
public class Gives_Tasks implements Table {

    int mUserid;
    int cUserid;
    String description;
    int priority;

    public Gives_Tasks(int mUserid, int cUserid, String description, int priority){
        this.mUserid = mUserid;
        this.cUserid = cUserid;
        this.description = description;
        this.priority = priority;
    }

    public void setmUserId(int mUserid) {
        this.mUserid = mUserid;
    }

    public void setcUserId(int cUserid) {
        this.cUserid = cUserid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getmUserId() {
        return mUserid;
    }

    public int getcUserId() {
        return cUserid;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
