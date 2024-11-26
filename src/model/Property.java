package model;
import java.util.ArrayList;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

import until.ValidateInput;

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
        this.hosts = new ArrayList<>();
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
    public List<Host> getHosts() {
        return hosts;
    }

    public void addHost(Host host) {
        hosts.add(host);
    }

}
