import java.util.ArrayList;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public abstract class Property {
    private String propertyID;
    private String address;
    private double price;
    private String status; /*"Available", "Rented", "Under Maintenance"*/
    private Owner owner; /*reference to the owner of the property*/
    private List<Host> hosts; /*to store hosts managing this property*/


    // Constructor
    public Property(String propertyID, String address, double price, String status, Owner owner) {
        this.propertyID = propertyID;
        this.address = address;
        this.price = price;
        this.status = ValidateInput.validatePropertyStatus(status);
        this.owner = owner;
        this.hosts = new ArrayList<Host>();
    }

    // Getter
    public String getPropertyID() {
        return propertyID;
    }
    public String getAddress() {
        return address;
    }
    public Owner getOwner() {
        return owner;
    }
    public String getStatus() {
        return status;
    }
    public double getPrice() {
        return price;
    }

    public void addHost(Host host) {
        hosts.add(host);
    }

    @Override
    public String toString() {
        return String.format("Property ID: %-6s | Address: %-30s | Pricing: $%-10.2f | Status: %-15s | Owner ID: %-6s",
                propertyID,
                address,
                price,
                status,
                owner.getId());
    }
}
