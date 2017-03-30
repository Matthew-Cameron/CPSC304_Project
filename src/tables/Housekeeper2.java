package tables;

/**
 * Created by Matthew on 2017-03-22.
 */
public class Housekeeper2 implements Table{
    private int wage;
    private String cleaningSpecialty;
    private String name;
    private String phone;
    private int sin;

    public Housekeeper2(String name, String phone, int sin,  int wage, String cleaningSpecialty) {
    	this.name = name;
    	this.phone = phone;
    	this.sin = sin;
        this.wage = wage;
        this.cleaningSpecialty = cleaningSpecialty;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public void setCleaningSpecialty(String cleaningSpecialty) {
        this.cleaningSpecialty = cleaningSpecialty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

     public void setSin(int sin) {
        this.sin = sin;
    }

     public int getSin() {
        return sin;
    }


    public int getWage() {
        return wage;
    }

    public String getCleaningSpecialty() {
        return cleaningSpecialty;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
