import java.util.Date;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */
public abstract class Person {
    private String id;
    private String name;
    private Date dateOfBirth;
    private String info_contact;

    private static int count = 0;

    // Constructor
    public Person() {
        id = null;
        name = null;
        dateOfBirth = null;
        info_contact = null;
    }

    public Person(String name, Date dateOfBirth, String info_contact) {
        this.id = String.valueOf(++count);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.info_contact = info_contact;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", Date Of Birth=" + dateOfBirth +
                ", info_contact ='" + info_contact;
    }
}
