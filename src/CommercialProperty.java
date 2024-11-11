/**
 * @author <Hoàng Minh Thắng - S3999925>
 */

public class CommercialProperty extends Property {
    private String businessType; /*(Retail, Office, Hotel, Multifamily, Industrial, Special Purpose)*/
    private int parkingSpace;
    private double squareFootage;

    // Constructor
    public CommercialProperty(String address, double price, String status, Owner owner, int parkingSpace, String businessType, double squareFootage) {
        super(address, price, status, owner);
        this.parkingSpace = parkingSpace;
        this.businessType = ValidateInput.validateBusinessType(businessType);
        this.squareFootage = squareFootage;
    }

}
