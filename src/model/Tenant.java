package model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class Tenant extends Person {
    private List<Payment> paymentRecord; // to keep track of payment transactions made by the tenant
    private List<RentalAgreement> rentalAgreements; // store rental agreements associated with the tenant

    public Tenant(String id, String name, Date birthDate, String info_contact) {
        super(id, name, birthDate, info_contact);
        rentalAgreements = new ArrayList<>();
        paymentRecord = new ArrayList<>();
    }

    // Getter
    public List<RentalAgreement> getRentalAgreements() {return rentalAgreements;}
    // Adding payment to the paymentRecord
    public void addPayment(Payment payment) {
        paymentRecord.add(payment);
    }

    /**
     * Returns a string representation of the object Tenant, formatted to display
     * key property details
     *
     * @return a formatted string containing the details of the Tenant
     */
    @Override
    public String toString() {
        String paymentIDs; String rentalAgreementIDs;
        // Convert the paymentRecord list into a stream
        try {
            paymentIDs = paymentRecord.stream()
                    // Map each Payment object to its paymentID using the getPaymentID method
                    .map(Payment::getPaymentID)
                    // Reduce the stream by concatenating the paymentIDs with a hyphen ("-") as the separator
                    .reduce((name1, name2) -> name1 + "-" + name2)
                    .orElse("None"); // If the stream is empty, return "None" as the default value
        } catch (NullPointerException e) {
            paymentIDs = "None";
        }

        // Convert the rentalAgreements list into a stream
        try {
            rentalAgreementIDs = rentalAgreements.stream()
            // Map each RentalAgreement object to its AgreementID using the getAgreementID method
            .map(RentalAgreement::getAgreementID)
            // Reduce the stream by concatenating the AgreementID with a hyphen ("-") as the separator
            .reduce((name1, name2) -> name1 + "-" + name2)
            .orElse("None"); // If the stream is empty, return "None" as the default value
        } catch (NullPointerException e) {
            rentalAgreementIDs = "None";
        }

        return super.toString() + String.format("| %-15s | %-25s",paymentIDs, rentalAgreementIDs);

    }
    /**
     * Converts the object's Tenant into a CSV formatted string.
     *
     * @return a CSV formatted string representing the object's data
     */
    public String toCSV() {
        // Format the birthdate
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
        String formattedBirthDate = formatDate.format(super.getDateOfBirth());
        String paymentIDs; String rentalAgreementIDs;
        // Convert the paymentRecord list into a stream
        try {
            paymentIDs = paymentRecord.stream()
                    // Map each Payment object to its paymentID using the getPaymentID method
                    .map(Payment::getPaymentID)
                    // Reduce the stream by concatenating the paymentIDs with a hyphen ("-") as the separator
                    .reduce((name1, name2) -> name1 + "-" + name2)
                    .orElse("None"); // If the stream is empty, return "None" as the default value
        } catch (NullPointerException e) {
            paymentIDs = "None";
        }

        // Convert the rentalAgreements list into a stream
        try {
            rentalAgreementIDs = rentalAgreements.stream()
                    // Map each RentalAgreement object to its AgreementID using the getAgreementID method
                    .map(RentalAgreement::getAgreementID)
                    // Reduce the stream by concatenating the AgreementID with a hyphen ("-") as the separator
                    .reduce((name1, name2) -> name1 + "-" + name2)
                    .orElse("None"); // If the stream is empty, return "None" as the default value
        } catch (NullPointerException e) {
            rentalAgreementIDs = "None";
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
