package model;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

import until.ValidateInput;

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

    // Private constructor to enforce the use of the Builder
    private RentalAgreement(Builder builder) {
        this.agreementID = builder.agreementID;
        this.mainTenant = builder.mainTenant;
        this.subTenants = builder.subTenants;
        this.propertyLeased = builder.propertyLeased;
        this.host = builder.host;
        this.owner = builder.owner;
        this.period = ValidateInput.validateRentalPeriod(builder.period);
        this.contractDate = builder.contractDate;
        this.rentingFee = builder.rentingFee;
        this.status = ValidateInput.validateAgreementStatus(builder.status);

    }

    // Builder class
    public static class Builder {
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

        public Builder(String id) {
            this.agreementID = id;
        }

        public Builder mainTenant(Tenant mainTenant) {
            this.mainTenant = mainTenant;
            return this;
        }

        public Builder subTenants(List<Tenant> subTenants) {
            this.subTenants = subTenants;
            return this;
        }

        public Builder propertyLeased(Property propertyLeased) {
            this.propertyLeased = propertyLeased;
            return this;
        }

        public Builder host(Host host) {
            this.host = host;
            return this;
        }

        public Builder owner(Owner owner) {
            this.owner = owner;
            return this;
        }

        public Builder period(String period) {
            this.period = period;
            return this;
        }

        public Builder contractDate(Date contractDate) {
            this.contractDate = contractDate;
            return this;
        }

        public Builder rentingFee(double rentingFee) {
            this.rentingFee = rentingFee;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public RentalAgreement build() {
            return new RentalAgreement(this);
        }
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

    public String getMainTenantID() {
        return mainTenant.getId();
    }

    public List<Tenant> getSubTenants() {
        return subTenants;
    }

    public String getPropertyId() {
        return propertyLeased.getPropertyID();
    }

    public String getHost() {
        return host.getId();
    }

    public String getOwner() {
        return owner.getId();
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
                "%-15s | %-16s | %-25s | %-20s | %-15s | %-15s | %-10s | %-15s | $%-12.2f | %-10s",
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        String subTenantsNames = subTenants.stream()
                .map(Tenant::getId)
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None");

        return (agreementID + "," +
                mainTenant.getId() + "," +
                subTenantsNames + "," +
                propertyLeased.getPropertyID() + "," +
                host.getId() + "," +
                owner.getId() + "," +
                period + "," +
                dateFormat.format(contractDate) + "," +
                rentingFee + "," +
                status);
    }
}
