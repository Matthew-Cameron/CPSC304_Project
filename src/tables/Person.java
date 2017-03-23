package tables;

/**
 * Created by Matthew on 2017-03-22.
 */
public class Person implements Table {

    String name;
    String phone;
    String email;

    public Person(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
