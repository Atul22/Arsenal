package Utils;

public class ContactInfo {
    public String name;
    public String surname;
    public String email;
    public static final String NAME_PREFIX = "Name_";
    public static final String SURNAME_PREFIX = "Surname_";
    public static final String EMAIL_PREFIX = "email_";

    public ContactInfo(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
