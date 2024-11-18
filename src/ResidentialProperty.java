/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class ResidentialProperty extends Property {
    private int bedrooms;
    private boolean isGardened;
    private boolean petFriendly;
    private static int count = 0;

    // Constructor
    public ResidentialProperty(String id, String address, double price, String status, Owner owner,
                               int bedrooms, boolean isGardened, boolean getFriendly) {
        super(id, address, price, status, owner);
        this.bedrooms = bedrooms;
        this.isGardened = isGardened;
        this.petFriendly = getFriendly;

        count = Integer.parseInt(id.substring(2));
    }
    public ResidentialProperty(String address, double price, String status, Owner owner, int bedrooms, boolean isGardened, boolean petFriendly) {
        super("RP" + (++count < 10 ? "00" : "0") + count, address, price, status, owner);
        this.bedrooms = bedrooms;
        this.isGardened = isGardened;
        this.petFriendly = petFriendly;
    }

    @Override
    public String toString() {
        return String.format("Property ID: %-6s | Address: %-25s | Pricing: $%-5.2f | Status: %-20s | Owner ID: %-6s | Number of bedroom: %-2d | Include Garden: %-5b | Pet-Friendly: %-5b",
                super.getPropertyID(),
                super.getAddress(),
                super.getPrice(),
                super.getStatus(),
                super.getOwner().getId(),
                bedrooms,
                isGardened,
                petFriendly);
    }

    public String toCSV() {
        return "j";
    }
}
