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

    private static int count = 0;

    // Constructor
    public Property() {
        propertyID = "";
        address = "";
        price = 0.0;
        status = "";
        owner = null;
        hosts = new ArrayList<Host>();
    }

    public Property(String address, double price, String status, Owner owner) {
        this.propertyID = String.valueOf(++count);
        this.address = address;
        this.price = price;
        this.status = ValidateInput.validatePropertyStatus(status);
        owner = owner;
        hosts = new ArrayList<>();
    }


}
