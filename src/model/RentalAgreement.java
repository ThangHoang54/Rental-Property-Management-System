package model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

import util.ValidateInput;
import Build.BuilderRentalAgreement;

public class RentalAgreement {

    private final String agreementID;
    private Tenant mainTenant; // Reference to the main tenant
    private List<Tenant> subTenants; // List of sub-tenants
    private Property propertyLeased; // Reference to the property being leased
    private Host host; // Reference to the host managing the property
    private Owner owner;  // Reference to the owner of the property
    private String period; // Rental period (e.g., "daily", "weekly", "monthly", "fortnightly")
    private Date contractDate; // Date the contract was signed
    private double rentingFee; // Fee for renting the property
    private String status;  // Status of the agreement (e.g., "New", "Active", "Completed")

    // Private constructor to enforce the use of the Builder
    public RentalAgreement(BuilderRentalAgreement builder) {
        this.agreementID = builder.getAgreementID();
        this.mainTenant = builder.getMainTenant();
        this.subTenants = builder.getSubTenants();
        this.propertyLeased = builder.getPropertyLeased();
        this.host = builder.getHost();
        this.owner = builder.getOwner();
        this.period = ValidateInput.validateRentalPeriod(builder.getPeriod());
        this.contractDate = builder.getContractDate();
        this.rentingFee = builder.getRentingFee();
        this.status = ValidateInput.validateAgreementStatus(builder.getStatus());
    }

    // Getter
    public String getAgreementID() {
        return agreementID;
    }
    public String getMainTenantID() {
        return mainTenant.getId();
    }
    public List<Tenant> getSubTenants() {
        return subTenants;
    }
    public Property getPropertyLeased() {
        return propertyLeased;
    }
    public Host getHost() {
        return host;
    }
    public Owner getOwner() {
        return owner;
    }
    public String getStatus() {
        return status;
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

    /**
     * Returns a string representation of the object RentalAgreement, formatted to display
     * key property details
     *
     * @return a formatted string containing the details of the RentalAgreement
     */
    @Override
    public String toString() {
        String subTenantsNames;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
             subTenantsNames = subTenants.stream()
                    .map(Tenant::getId)
                    .reduce((name1, name2) -> name1 + "-" + name2)
                    .orElse("None");
        } catch (NullPointerException e) {
            subTenantsNames = "None";
        }

        return String.format(
                "%-15s | %-16s | %-25s | %-20s | %-15s | %-15s | %-12s | %-15s | $%-12.2f | %-10s",
                agreementID,
                (mainTenant != null ? mainTenant.getId() : "None"),
                subTenantsNames,
                (propertyLeased != null ? propertyLeased.getPropertyID() : "None"),
                (host != null ? host.getId() : "None"),
                (owner != null ? owner.getId() : "None"),
                period,
                dateFormat.format(contractDate),
                rentingFee,
                status);
    }

    /**
     * Converts the object's RentalAgreement into a CSV formatted string.
     *
     * @return a CSV formatted string representing the object's data
     */
    public String toCSV() {
        String subTenantsNames;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            subTenantsNames = subTenants.stream()
                    .map(Tenant::getId)
                    .reduce((name1, name2) -> name1 + "-" + name2)
                    .orElse("None");
        } catch (NullPointerException e) {
            subTenantsNames = "None";
        }

        return (agreementID + "," +
               (mainTenant != null ? mainTenant.getId() : "None") + "," +
                subTenantsNames + "," +
                (propertyLeased != null ? propertyLeased.getPropertyID() : "None") + "," +
                (host != null ? host.getId() : "None") + "," +
                (owner != null ? owner.getId() : "None") + "," +
                period + "," +
                dateFormat.format(contractDate) + "," +
                rentingFee + "," +
                status);
    }
}
