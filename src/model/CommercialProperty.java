package model;
/**
 * @author <Hoàng Minh Thắng - S3999925>
 */
import until.ValidateInput;
public class CommercialProperty extends Property {
    private String businessType; /*(Retail, Office, Hotel, Factory, Warehouse)*/
    private int parkingSpace;
    private double squareFootage;
    private static int count = 0;

    // Constructor
    public CommercialProperty(String id, String address, double price, String status, Owner owner,
                              String businessType, int parkingSpace, double squareFootage) {
        super(id, address, price, status, owner);
        this.businessType = ValidateInput.validateBusinessType(businessType);
        this.parkingSpace = parkingSpace;
        this.squareFootage = squareFootage;

        count = Integer.parseInt(id.substring(2));
    }
    public CommercialProperty(String address, double price, String status, Owner owner, String businessType, int parkingSpace, double squareFootage) {
        super("CP" + (++count < 10 ? "00" : "0") + count, address, price, status, owner);
        this.businessType = ValidateInput.validateBusinessType(businessType);
        this.parkingSpace = parkingSpace;
        this.squareFootage = squareFootage;
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-25s | $%-9.2f | %-20s | %-8s | %-15s | %-14d | %-14.2f",
                super.getPropertyID(),
                super.getAddress(),
                super.getPrice(),
                super.getStatus(),
                super.getOwner().getId(),
                businessType,
                parkingSpace,
                squareFootage);
    }

    public String toCSV() {
        // Return a CSV formatted string
        return super.getPropertyID() + "," +
                super.getAddress() + "," +
                super.getPrice() + "," +
                super.getStatus() + "," +
                (super.getOwner() != null ? super.getOwner().getId() : "") + "," +
                businessType + "," +
                parkingSpace + "," +
                squareFootage;

    }
}
