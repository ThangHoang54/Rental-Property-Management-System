package model;
/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class ResidentialProperty extends Property {
    private int bedrooms;
    private boolean isGardened;
    private boolean petFriendly;

    // Constructor
    public ResidentialProperty(String id, String address, double price, String status, Owner owner,
                               int bedrooms, boolean isGardened, boolean getFriendly) {
        super(id, address, price, status, owner);
        this.bedrooms = bedrooms;
        this.isGardened = isGardened;
        this.petFriendly = getFriendly;

    }

    /**
     * Returns a string representation of the object, formatted to display
     * key property details, including property ID, address, price, status,
     * owner ID, associated host IDs, number of bedrooms, and flags for
     * gardened and pet-friendly features.
     *
     * @return a formatted string containing the details of the property
     */
    @Override
    public String toString() {
        // Convert the hostsManagingProperties list into a stream
        String hostIDs = (super.getHosts() == null) ? super.getHosts().stream()
                // Map each Host object to its HostID using the getId method
                .map(Host::getId)
                // Reduce the stream by concatenating the hostIDs with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None") : "None"; // If the stream is empty, return

        return String.format("%-12s | %-25s | $%-9.2f | %-20s | %-8s | %-15s | %-19d | %-15b | %-12b",
                super.getPropertyID(),
                super.getAddress(),
                super.getPrice(),
                super.getStatus(),
                (super.getOwner() != null ? super.getOwner().getId() : "None"),
                hostIDs,
                bedrooms,
                isGardened,
                petFriendly);
    }

    /**
     * Converts the object's properties into a CSV formatted string.
     * The resulting string includes the property ID, address, price, status,
     * owner ID, associated host IDs, number of bedrooms, and flags for
     * gardened and pet-friendly features, separated by commas.
     *
     * @return a CSV formatted string representing the object's data
     */
    public String toCSV() {
        // Convert the hostsManagingProperties list into a stream
        String hostIDs = (super.getHosts() == null) ? super.getHosts().stream()
                // Map each Host object to its HostID using the getId method
                .map(Host::getId)
                // Reduce the stream by concatenating the hostIDs with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None") : "None"; // If the stream is empty, return

        // Return a CSV formatted string
        return super.getPropertyID() + "," +
                super.getAddress() + "," +
                super.getPrice() + "," +
                super.getStatus() + "," +
                (super.getOwner() != null ? super.getOwner().getId() : "None") + "," +
                hostIDs + "," +
                bedrooms + "," +
                isGardened + "," +
                petFriendly;
    }
}
