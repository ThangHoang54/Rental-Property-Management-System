/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class ResidentialProperty extends Property {
    private int bedrooms;
    private boolean isGardened;
    private boolean getFriendly;
    private static int count = 0;

    public ResidentialProperty(String address, double price, String status, Owner owner, int bedrooms, boolean isGardened, boolean getFriendly) {
        super("RP" + (++count < 10 ? "00" : "0") + count, address, price, status, owner);
        this.bedrooms = bedrooms;
        this.isGardened = isGardened;
        this.getFriendly = getFriendly;
    }

    @Override
    public String toString() {
        return String.format("Property ID: %-6s | Address: %-30s | Pricing: $%-10.2f | Status: %-15s | Owner ID: %-6s | Number of bedroom: %-2d | Include Garden: %-4s | User-Friendly: %-4s",
                super.getPropertyID(),
                super.getAddress(),
                super.getPrice(),
                super.getStatus(),
                super.getOwner().getId(),
                bedrooms,
                isGardened,
                getFriendly);
    }
}
