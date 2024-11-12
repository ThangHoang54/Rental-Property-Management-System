import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class Tenant extends Person {
    private List<RentalAgreement> rentalAgreements; // store rental agreements associated with the tenant
    private List<Payment> paymentRecord; // to keep track of payment transactions made by the tenant

    public Tenant(String name, Date dateOfBirth, String info_contact) {
        super(name, dateOfBirth, info_contact);
        rentalAgreements = new ArrayList<>();
        paymentRecord = new ArrayList<>();
    }
}
