import java.util.Date;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class Payment {
    private double amount; // The amount of money that is being paid in the transaction.
    private Date paymentDate; // Records the date when the payment was made
    private String paymentMethod; // The method of payment used (e.g., "Credit Card", "Cash", "Bank Transfer").

    public Payment() {
        amount = 0.0;
        paymentDate = null;
        paymentMethod = "";
    }
    public Payment(double amount, Date paymentDate, String paymentMethod) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Amount =" + amount +
                ", Payment Date =" + paymentDate +
                ", Payment Method ='" + paymentMethod;
    }
}
