import java.text.SimpleDateFormat;
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
    /**
     * Used when the admin want to update the RentalAgreement and load data from RentalAgreement.csv (Keeping the ID/ Auto update ID)
     */
    public RentalAgreement(String id, Tenant mainTenant, List<Tenant> subTenants, Property propertyLeased, Host host, Owner owner,
                           String period, Date contractDate, double rentingFee, String status) {
        this.agreementID = (!id.isEmpty()) ? id : ("RA" + (++count < 10 ? "00" : "0") + count);
        this.mainTenant = mainTenant;
        this.subTenants = subTenants;
        this.propertyLeased = propertyLeased;
        this.host = host;
        this.owner = owner;
        this.period = ValidateInput.validateRentalPeriod(period);
        this.contractDate = contractDate;
        this.rentingFee = rentingFee;
        this.status = ValidateInput.validateAgreementStatus(status);

        count = (!id.isEmpty()) ? Integer.parseInt(id.substring(2)) : count;
    }

    // Getter
    public String getAgreementID() {
        return agreementID;
    }
    public String getStatus() {
        return status;
    }
    public String getOwnerName() {
        return owner.getName();
    }
    public String getPropertyAddress() {
        return propertyLeased.getAddress();
    }

    // Setter
    public void setMainTenant(Tenant mainTenant) {
        this.mainTenant = mainTenant;
    }
    public void setSubTenants(List<Tenant> subTenants) {
        this.subTenants = subTenants;
    }
    public void setPropertyLeased(Property propertyLeased) {
        this.propertyLeased = propertyLeased;
    }
    public void setHost(Host host) {
        this.host = host;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }
    public void setRentingFee(double rentingFee) {
        this.rentingFee = rentingFee;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String subTenantsNames = subTenants.stream()
                .map(Tenant::getId)
                .reduce((name1, name2) -> name1 + ", " + name2)
                .orElse("None");

        return String.format(
                "Agreement ID: %-6s | Main Tenant ID: %-6s | Sub-Tenants ID: %-20s | Property ID: %-6s | Host ID: %-6s | Owner ID: %-6s | Period: %-10s | Contract Date: %-10s | Renting Fee: $%-10.2f | Status: %-10s",
                agreementID,
                mainTenant.getId(),
                subTenantsNames,
                propertyLeased.getPropertyID(),
                host.getId(),
                owner.getId(),
                period,
                dateFormat.format(contractDate),
                rentingFee,
                status);
    }

    public String toCSV() {
        return "j";
    }
}
