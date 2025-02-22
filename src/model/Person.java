package model;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */
public abstract class Person {
    private final String id;
    private String name;
    private Date dateOfBirth;
    private String info_contact; /*Email*/


    // Constructor
    public Person(String id, String name, Date dateOfBirth, String info_contact) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.info_contact = info_contact;
    }

    // Getter
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public String getInfo_contact() {
        return info_contact;
    }

    @Override
    public String toString() {
        // Convert Date format in form dd/mm/yyyy
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%-6s | %-20s | %-15s | %-30s",
                id,
                name,
                formatDate.format(dateOfBirth),
                info_contact);
    }

}
