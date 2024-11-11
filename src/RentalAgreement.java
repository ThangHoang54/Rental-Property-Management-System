import java.util.Date;
import java.util.List;


/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class RentalAgreement {
    private String agreementID;
    private Tenant mainTenant; // Reference to the main tenant
    private List<Tenant> subTenants; // List of sub-tenants
    private Property propertyLeased; // Reference to the property being leased
    private Host host; // Reference to the host managing the property
    private Owner owner;  // Reference to the owner of the property
    private String period; // Rental period (e.g., "daily", "weekly", "monthly")
    private Date contractDate; // Date the contract was signed
    private double rentingFee; // Fee for renting the property
    private String status;  // Status of the agreement (e.g., "New", "Active", "Completed")

    private static int count = 0;

    // Constructor
    public RentalAgreement(Tenant mainTenant, List<Tenant> subTenants, Property propertyLeased, Host host, Owner owner, String period, Date contractDate, double rentingFee, String status) {
        this.agreementID = "RA" + String.valueOf(++count);
        this.mainTenant = mainTenant;
        this.subTenants = subTenants;
        this.propertyLeased = propertyLeased;
        this.host = host;
        this.owner = owner;
        this.period = ValidateInput.validateRentalPeriod(period);
        this.contractDate = contractDate;
        this.rentingFee = rentingFee;
        this.status = ValidateInput.validateAgreementStatus(status);
    }
}
