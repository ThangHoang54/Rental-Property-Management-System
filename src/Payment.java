import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class Payment {
    private String paymentID;
    private double amount; // The amount of money that is being paid in the transaction.
    private Date paymentDate; // Records the date when the payment was made
    private String paymentMethod; // The method of payment used (e.g., "Credit Card", "Cash", "Bank Transfer").
    private static int count = 0;

    // Constructor
    public Payment() {
        amount = 0.0;
        paymentDate = null;
        paymentMethod = "";
    }
    public Payment(double amount, Date paymentDate, String paymentMethod) {
        this.paymentID = "PM" + (++count < 10 ? "00" : "0") + count;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = ValidateInput.validatePaymentMethod(paymentMethod);
    }

    // Getter
    public String getPaymentID() {
        return paymentID;
    }

    @Override
    public String toString() {
        // Convert Date format in form dd/mm/yyyy
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        return "Amount = " + amount +
                ", Payment Date = " + formatDate.format(paymentDate) +
                ", Payment Method = " + paymentMethod;
    }
}
