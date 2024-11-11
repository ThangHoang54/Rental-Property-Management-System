import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class Host extends Person {
    private List<Property> propertiesManaged;
    private List<Owner> cooperatingOwners;
    private List<RentalAgreement> rentalAgreements;

    public Host(String name, Date dateOfBirth, String info_contact) {
        super(name, dateOfBirth, info_contact);
        propertiesManaged = new ArrayList<Property>();
        cooperatingOwners = new ArrayList<Owner>();
        rentalAgreements = new ArrayList<RentalAgreement>();
    }
}
