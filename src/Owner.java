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

    public Owner(String name, Date dateOfBirth, String info_contact) {
        super(name, dateOfBirth, info_contact);
        propertiesOwned = new ArrayList<Property>();
        hostsManagingProperties = new ArrayList<Host>();
        rentalAgreements = new ArrayList<RentalAgreement>();
    }
}
