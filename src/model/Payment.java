package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

import until.ValidateInput;

public class Payment {
    private String paymentID;
    private double amount; // The amount of money that is being paid in the transaction.
    private Date paymentDate; // Records the date when the payment was made
    private String paymentMethod; // The method of payment used (e.g., "Credit Card", "Cash", "Bank Transfer").

    // Constructor
    public Payment(String id, double amount, Date paymentDate, String paymentMethod) {
        this.paymentID = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = ValidateInput.validatePaymentMethod(paymentMethod);
    }

    // Getter
    public String getPaymentID() {
        return paymentID;
    }

    /**
     * Converts the Payment object to a CSV (Comma-Separated Values) string.
     *
     * @return a CSV formatted string representing the Payment object
     */
    public String toCSV() {
        // Convert Date to a suitable format for CSV
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");

        // Return a CSV formatted string
        return  paymentID + "," + amount + "," + formatDate.format(paymentDate) + "," + paymentMethod;
    }
}
