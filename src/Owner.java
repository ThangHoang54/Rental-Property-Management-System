import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class Owner extends Person{
    private List<Property> propertiesOwned; // store properties managed by the host
    private List<Host> hostsManagingProperties; // keep track of owners that the host cooperates with
    private List<RentalAgreement> rentalAgreements; // keep track of rental agreements associated with the host
    private static int count = 0;

    public Owner(String name, Date dateOfBirth, String info_contact) {
        super("ON" + (++count < 10 ? "00" : "0") + count, name, dateOfBirth, info_contact);
        propertiesOwned = new ArrayList<Property>();
        hostsManagingProperties = new ArrayList<Host>();
        rentalAgreements = new ArrayList<RentalAgreement>();
    }

    public void addProperty(Property property) {
        propertiesOwned.add(property);
    }

    public void addHost(Host host) {
        hostsManagingProperties.add(host);
    }

    public void addRentalAgreement(RentalAgreement rentalAgreement) {
        rentalAgreements.add(rentalAgreement);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
