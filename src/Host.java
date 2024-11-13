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
    private static int count = 0;

    public Host(String name, Date dateOfBirth, String info_contact) {
        super("HS" + (++count < 10 ? "00" : "0") + count, name, dateOfBirth, info_contact);
        propertiesManaged = new ArrayList<Property>();
        cooperatingOwners = new ArrayList<Owner>();
        rentalAgreements = new ArrayList<RentalAgreement>();
    }

    public void addProperty(Property property) {
        propertiesManaged.add(property);
    }

    public void addOwner(Owner owner) {
        cooperatingOwners.add(owner);
    }

    public void addRentalAgreement(RentalAgreement rentalAgreement) {
        rentalAgreements.add(rentalAgreement);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
