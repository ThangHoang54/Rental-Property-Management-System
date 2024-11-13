import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class Tenant extends Person {
    private List<RentalAgreement> rentalAgreements; // store rental agreements associated with the tenant
    private List<Payment> paymentRecord; // to keep track of payment transactions made by the tenant
    private static int count = 0;

    public Tenant(String name, Date dateOfBirth, String info_contact) {
        super("TN" + (++count < 10 ? "00" : "0") + count, name, dateOfBirth, info_contact);
        rentalAgreements = new ArrayList<>();
        paymentRecord = new ArrayList<>();
    }

    public void addRentalAgreement(RentalAgreement rentalAgreement) {
        rentalAgreements.add(rentalAgreement);
    }

    public void addPayment(Payment payment) {
        paymentRecord.add(payment);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
