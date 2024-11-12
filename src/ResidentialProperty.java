/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class ResidentialProperty extends Property {
    private int bedrooms;
    private boolean isGardened;
    private boolean getFriendly;

    public ResidentialProperty(String address, double price, String status, Owner owner, int bedrooms, boolean isGardened, boolean getFriendly) {
        super(address, price, status, owner);
        this.bedrooms = bedrooms;
        this.isGardened = isGardened;
        this.getFriendly = getFriendly;
    }
}
