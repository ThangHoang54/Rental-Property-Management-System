/**
 * @author <Hoàng Minh Thắng - S3999925>
 */

public class CommercialProperty extends Property {
    private String businessType; /*(Retail, Office, Hotel, Factory, Warehouse)*/
    private int parkingSpace;
    private double squareFootage;
    private static int count = 0;

    // Constructor
    public CommercialProperty(String address, double price, String status, Owner owner, String businessType, int parkingSpace, double squareFootage) {
        super("CP" + (++count < 10 ? "00" : "0") + count, address, price, status, owner);
        this.parkingSpace = parkingSpace;
        this.businessType = ValidateInput.validateBusinessType(businessType);
        this.squareFootage = squareFootage;
    }

    @Override
    public String toString() {
        return String.format("Property ID: %-6s | Address: %-30s | Pricing: $%-10.2f | Status: %-15s | Owner ID: %-6s | Business Type: %-15s | Parking Space: %-6d | Square Footage: %-4.2f",
                super.getPropertyID(),
                super.getAddress(),
                super.getPrice(),
                super.getStatus(),
                super.getOwner().getId(),
                businessType,
                parkingSpace,
                squareFootage);
    }
}
