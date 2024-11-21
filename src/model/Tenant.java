package model;
import java.text.SimpleDateFormat;
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

    public Tenant(String id, String name, Date birthDate, String info_contact) {
        super(id, name, birthDate, info_contact);
        rentalAgreements = new ArrayList<>();
        paymentRecord = new ArrayList<>();
        count = Integer.parseInt(id.substring(2));
    }
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

    public String toCSV() {
        // Format the birthdate
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
        String formattedBirthDate = formatDate.format(super.getDateOfBirth());

        // Prepare lists of IDs for rental agreements and payment
        StringBuilder paymentIDs = new StringBuilder();
        for (Payment payment : paymentRecord) {
            paymentIDs.append(payment.getPaymentID()).append(";");
        }

        StringBuilder rentalAgreementIDs = new StringBuilder();
        for (RentalAgreement rentalAgreement : rentalAgreements) {
            rentalAgreementIDs.append(rentalAgreement.getAgreementID()).append(";");
        }

        // Remove trailing semicolons
        if (!paymentIDs.isEmpty()) {
            paymentIDs.setLength(paymentIDs.length() - 1);
        } if (!rentalAgreementIDs.isEmpty()) {
            rentalAgreementIDs.setLength(rentalAgreementIDs.length() - 1);
        }

        // Return a CSV formatted string
        return super.getId() + "," +
                super.getName() + "," +
                formattedBirthDate + "," +
                super.getInfo_contact() + "," +
                paymentIDs + "," +
                rentalAgreementIDs;
    }
}
