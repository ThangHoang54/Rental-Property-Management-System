package Build;

import java.util.Date;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */
import model.*;

// Builder class
public class BuilderRentalAgreement {
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

    public BuilderRentalAgreement(String id) {
        this.agreementID = id;
    }

    public BuilderRentalAgreement mainTenant(Tenant mainTenant) {
        this.mainTenant = mainTenant;
        return this;
    }

    public BuilderRentalAgreement subTenants(List<Tenant> subTenants) {
        this.subTenants = subTenants;
        return this;
    }

    public BuilderRentalAgreement propertyLeased(Property propertyLeased) {
        this.propertyLeased = propertyLeased;
        return this;
    }

    public BuilderRentalAgreement host(Host host) {
        this.host = host;
        return this;
    }

    public BuilderRentalAgreement owner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public BuilderRentalAgreement period(String period) {
        this.period = period;
        return this;
    }

    public BuilderRentalAgreement contractDate(Date contractDate) {
        this.contractDate = contractDate;
        return this;
    }

    public BuilderRentalAgreement rentingFee(double rentingFee) {
        this.rentingFee = rentingFee;
        return this;
    }

    public BuilderRentalAgreement status(String status) {
        this.status = status;
        return this;
    }

    public RentalAgreement build() {
        return new RentalAgreement(this);
    }

    public String getAgreementID() {
        return agreementID;
    }

    public Tenant getMainTenant() {
        return mainTenant;
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

    public String getPeriod() {
        return period;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public double getRentingFee() {
        return rentingFee;
    }

    public String getStatus() {
        return status;
    }
}