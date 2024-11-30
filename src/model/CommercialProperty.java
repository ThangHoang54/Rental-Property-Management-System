package model;
/**
 * @author <Hoàng Minh Thắng - S3999925>
 */
import until.ValidateInput;
public class CommercialProperty extends Property {
    private String businessType; /*(Retail, Office, Hotel, Factory, Warehouse)*/
    private int parkingSpace;
    private double squareFootage;


    // Constructor
    public CommercialProperty(String id, String address, double price, String status, Owner owner,
                              String businessType, int parkingSpace, double squareFootage) {
        super(id, address, price, status, owner);
        this.businessType = ValidateInput.validateBusinessType(businessType);
        this.parkingSpace = parkingSpace;
        this.squareFootage = squareFootage;

    }

    /**
     * Returns a string representation of the object, formatted to display
     * key property details, including property ID, address, price, status,
     * owner ID, associated host IDs, business type, parking spaces, and
     * square footage.
     *
     * @return a formatted string containing the details of the property
     */
    @Override
    public String toString() {
        String hostIDs;
        // Convert the hostsManagingProperties list into a stream
        try {
            hostIDs = super.getHosts().stream()
                    // Map each Host object to its HostID using the getId method
                    .map(Host::getId)
                    // Reduce the stream by concatenating the hostIDs with a hyphen ("-") as the separator
                    .reduce((name1, name2) -> name1 + "-" + name2)
                    .orElse("None"); // If the stream is empty, return
        } catch (NullPointerException e) {
            hostIDs = "None";
        }

        return String.format("%-12s | %-25s | $%-9.2f | %-20s | %-8s | %-25s | %-15s | %-14d | %-14.2f",
                super.getPropertyID(),
                super.getAddress(),
                super.getPrice(),
                super.getStatus(),
                (super.getOwner() != null ? super.getOwner().getId() : "None"),
                hostIDs,
                businessType,
                parkingSpace,
                squareFootage);
    }

    /**
     * Converts the object's properties into a CSV formatted string.
     * The resulting string includes the property ID, address, price, status,
     * owner ID, associated host IDs, business type, parking spaces, and
     * square footage, separated by commas.
     *
     * @return a CSV formatted string representing the object's data
     */
    public String toCSV() {
        String hostIDs;
        // Convert the hostsManagingProperties list into a stream
        try {
            hostIDs = super.getHosts().stream()
                    // Map each Host object to its HostID using the getId method
                    .map(Host::getId)
                    // Reduce the stream by concatenating the hostIDs with a hyphen ("-") as the separator
                    .reduce((name1, name2) -> name1 + "-" + name2)
                    .orElse("None"); // If the stream is empty, return
        } catch (NullPointerException e) {
            hostIDs = "None";
        }

        // Return a CSV formatted string
        return super.getPropertyID() + "," +
                super.getAddress() + "," +
                super.getPrice() + "," +
                super.getStatus() + "," +
                (super.getOwner() != null ? super.getOwner().getId() : "None") + "," +
                hostIDs + "," +
                businessType + "," +
                parkingSpace + "," +
                squareFootage;

    }
}
