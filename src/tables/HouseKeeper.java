package tables;

/**
 * Created by Matthew on 2017-03-22.
 */
public class HouseKeeper extends User implements Table{
    private int wage;
    private String cleaningSpecialty;

    public HouseKeeper(String name, String phone, String email, int userid, String username, String password, int wage, String cleaningSpecialty) {
        super(name, phone, email, userid, username, password);
        this.wage = wage;
        this.cleaningSpecialty = cleaningSpecialty;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public void setCleaningSpecialty(String cleaningSpecialty) {
        this.cleaningSpecialty = cleaningSpecialty;
    }

    public int getWage() {
        return wage;
    }

    public String getCleaningSpecialty() {
        return cleaningSpecialty;
    }
}
