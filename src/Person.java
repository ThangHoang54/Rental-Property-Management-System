import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */
public abstract class Person {
    private String id;
    private String name;
    private Date dateOfBirth;
    private String info_contact; /*Email*/


    // Constructor
    public Person() {
        id = null;
        name = null;
        dateOfBirth = null;
        info_contact = null;
    }

    public Person(String id, String name, Date dateOfBirth, String info_contact) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.info_contact = info_contact;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setter
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        // Convert Date format in form dd/mm/yyyy
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("ID: %-6s | Name: %-20s | Date of Birth: %-10s | Contact Info: %-20s",
                id,
                name,
                formatDate.format(dateOfBirth),
                info_contact);
    }
}
