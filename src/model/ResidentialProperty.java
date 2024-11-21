package model;
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
        return String.format("%-12s | %-25s | $%-9.2f | %-20s | %-8s | %-19d | %-15b | %-12b",
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
        // Return a CSV formatted string
        return super.getPropertyID() + "," +
                super.getAddress() + "," +
                super.getPrice() + "," +
                super.getStatus() + "," +
                (super.getOwner() != null ? super.getOwner().getId() : "") + "," +

                bedrooms + "," +
                isGardened + "," +
                petFriendly;
    }
}
