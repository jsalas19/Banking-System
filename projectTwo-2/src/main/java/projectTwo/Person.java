package projectTwo;

/**
 * @author Joshua Salas
 * missing extends
 */
public class Person{
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String address;
    private String phone_no;

    public void set_First_name(String first_name) {
        this.first_name = first_name;
    }

    public void set_Last_name(String last_name) {
        this.last_name = last_name;
    }

    public void set_Date_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void set_Address(String address) {
        this.address = address;
    }

    public void set_Phone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String get_First_name() {
        return first_name;
    }

    public String get_Last_name() {
        return last_name;
    }

    public String get_Date_of_birth() {
        return date_of_birth;
    }

    public String get_Address() {
        return address;
    }

    public String get_Phone_no() {
        return phone_no;
    }
}
