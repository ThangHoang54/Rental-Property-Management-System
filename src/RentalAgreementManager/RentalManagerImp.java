package RentalAgreementManager;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

import FileManager.DataPersistenceImp;
import model.*;
import until.*;
import Build.BuilderRentalAgreement;

public class RentalManagerImp implements RentalManager {
    private DataPersistenceImp model_list;

    // Constructor
    public RentalManagerImp() {
        model_list = new DataPersistenceImp();
    }

    // save whole list to corresponding csv files
    public void saveData() {
        model_list.saveDataToCSVFile();
    }
    // cleanup whole object list (useful for manual resource management)
    public void clearData() {
        model_list.clearData();
    }
    // Getter
    public List<RentalAgreement> getRentalAgreementList() {
        return model_list.getRentalAgreements();
    }
    public DataPersistenceImp getModelList() {
        return model_list;
    }
    /**
     * Checks if a rental agreement with the specified ID exists in the list of rental agreements.
     *
     * @param id the ID of the rental agreement to check for
     * @return true if a rental agreement with the specified ID exists; false otherwise
     */
    public boolean checkRentalAgreementExits(String id) {
        for (RentalAgreement a : model_list.getRentalAgreements()) {
            if (a.getAgreementID().equals(id)) {
                return true;
            }
        }
        System.out.println("Rental Agreement ID not exits, please try again");
        return false; // The give ID not exist
    }

    /**
     * Checks if a rental agreement with the specified owner's name exists in the list of rental agreements.
     *
     * @param name the name of owner that associate with rental agreement to check for
     * @return true if a rental agreement with the specified owner's exists; false otherwise
     */
    public boolean checkOwnerNameExits(String name) {
        for (RentalAgreement a : model_list.getRentalAgreements()) {
            if (a.getOwner().getName().equals(name)) {
                return true;
            }
        }
        System.out.println("Owner's name not exits, please try again");
        return false; // The give Owner's name not exist
    }

    /**
     * Checks if a rental agreement with the specified property's address exists in the list of rental agreements.
     *
     * @param address the address of property that associate with rental agreement to check for
     * @return true if a rental agreement with the specified property's address exists; false otherwise
     */
    public boolean checkPropertyAddressExits(String address) {
        for (RentalAgreement a : model_list.getRentalAgreements()) {
            if (a.getPropertyLeased().getAddress().equals(address)) {
                return true;
            }
        }
        System.out.println("Property's address not exits, please try again");
        return false; // The give Property's address not exist
    }

    /**
     * Checks if a Property's ID exists in entire program.
     *
     * @return Exist Property object
     */
    private Property checkPropertyExits() {
        Property propertyLeased = null;
        do {
            System.out.print("Enter Property ID in form (RPxxx or CPxxx): ");
            String propertyID = Input.getDataInput().getScanner().nextLine();

            if (propertyID.startsWith("RP")) {
                propertyLeased = (ResidentialProperty) DataPersistenceImp.getProperty(model_list.getCommercialProperties(),
                        model_list.getResidentialProperties(), propertyID); // Assuming a method to retrieve Property by ID
            } else if (propertyID.startsWith("CP")) {
                propertyLeased = (CommercialProperty) DataPersistenceImp.getProperty(model_list.getCommercialProperties(),
                        model_list.getResidentialProperties(), propertyID); // Assuming a method to retrieve Property by ID
            }
        } while (propertyLeased == null);
        return propertyLeased;
    }


    @Override
    public void addRentalAgreement() {
        System.out.println();
        Scanner scanner = Input.getDataInput().getScanner();
        Tenant mainTenant; Tenant subTenant; Host host;
        Owner owner; String num; int number;
        sortRentalAgreements(model_list.getRentalAgreements());

        // Rental Agreement ID
        // Get the latest ID number of Rental Agreement
        int size = Integer.parseInt(model_list.getRentalAgreements().getLast().getAgreementID().substring(2));
        String id = "RA" + ((size < 10) ? "00" : "0") + (size + 1);

        // Ask admin to input a valid Main Tenant
        do {
            do {
                System.out.print("Enter Main Tenant ID as a number: ");
                num = scanner.nextLine();
            } while (ValidateInput.isInteger(num));
            number = Integer.parseInt(num);
            String mainTenantID = "TN" + ((number < 10) ? "00" : "0") + number;
            mainTenant = DataPersistenceImp.getTenant(model_list.getTenants(), mainTenantID);  // Assuming a method to retrieve Tenant by ID

        } while (mainTenant == null || number < 1 || number > model_list.getTenants().size());

        // Ask admin to input a valid Sub-Tenant(s)
        Set<Tenant> subTenants = new LinkedHashSet<>();
        System.out.print("Enter number of Sub-Tenants: ");
        int subTenantCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < subTenantCount; i++) {
            do {
                do {
                    System.out.print("Enter Sub-Tenant ID " + (i + 1) + " as a number: ");
                    num = scanner.nextLine();
                } while (ValidateInput.isInteger(num) || Integer.parseInt(num) == Integer.parseInt(mainTenant.getId().substring(2)));
                number = Integer.parseInt(num);
                String subTenantID = "TN" + ((number < 10) ? "00" : "0") + number;
                subTenant = DataPersistenceImp.getTenant(model_list.getTenants(), subTenantID);
            } while (subTenant == null || number < 1 || number > model_list.getTenants().size());
            subTenants.add(subTenant);
        }
        // Convert Set to List
        List<Tenant> subTenantList = new ArrayList<>(subTenants);
        // Ask admin to input a valid propertyLeased
        Property propertyLeased = checkPropertyExits();
        // Ask admin to input a valid Host
        do {
            do {
                System.out.print("Enter Host ID as a number: ");
                num = scanner.nextLine();
            } while (ValidateInput.isInteger(num));
            number = Integer.parseInt(num);
            String hostID = "HS" + ((number < 10) ? "00" : "0") + number;
            host = DataPersistenceImp.getHost(model_list.getHosts(), hostID); // Assuming a method to retrieve Host by ID
        } while (host == null || number < 1 || number > model_list.getHosts().size());
        // Ask admin to input a valid Owner
        do {
            do {
                System.out.print("Enter Owner ID as a number: ");
                num = scanner.nextLine();
            } while (ValidateInput.isInteger(num));
            number = Integer.parseInt(num);
            String ownerID = "ON" + ((number < 10) ? "00" : "0") + number;
            owner = DataPersistenceImp.getOwner(model_list.getOwners(), ownerID); // Assuming a method to retrieve Owner by ID
        } while (owner == null || number < 1 || number > model_list.getOwners().size());
        // Ask admin to input a valid Rental Agreement period
        System.out.print("Enter Rental Period (daily, weekly, fortnightly, monthly): ");
        String period = ValidateInput.validateRentalPeriod(scanner.nextLine());
        // Ask admin to input a valid Contact Date
        Date contactDate = ValidateInput.validateContactDate();
        // Ask admin to input a valid Renting Fee
        do {
            System.out.print("Enter Renting Fee (double type): ");
            num = scanner.nextLine();
        } while (ValidateInput.isDouble(num));
        double rentingFee = Double.parseDouble(num);
        // Ask admin to input a valid Status
        System.out.print("Enter Status (e.g., 'New', 'Active', 'Completed'): ");
        String status = ValidateInput.validateAgreementStatus(scanner.nextLine());

        // Create new Rental Agreement object using Builder pattern
        RentalAgreement rentalAgreement = new BuilderRentalAgreement(id).mainTenant(mainTenant)
                .subTenants(subTenantList).propertyLeased(propertyLeased).host(host).owner(owner)
                .period(period).contractDate(contactDate).rentingFee(rentingFee).status(status).build();

        // Add rentalAgreement to the list or database (depending on your implementation)
        model_list.getRentalAgreements().add(rentalAgreement); // Assuming a List<RentalAgreement> rentalAgreements
        System.out.println("Rental Agreement added successfully");


        // Modify other model
        for (Tenant t : model_list.getTenants()) {
            if (t.getId().equals(mainTenant.getId()) && !t.getRentalAgreements().contains(rentalAgreement)) {
                if (t.getRentalAgreements().contains(null)) {t.getRentalAgreements().removeIf(Objects::isNull);}
                t.getRentalAgreements().add(rentalAgreement);
            }
            for (Tenant sub : subTenantList) {
                if (t.getId().equals(sub.getId()) && !t.getRentalAgreements().contains(rentalAgreement)) {
                    if (t.getRentalAgreements().contains(null)) {t.getRentalAgreements().removeIf(Objects::isNull);}
                    t.getRentalAgreements().add(rentalAgreement);
                }
            }
        }
        for (Host t : model_list.getHosts()) {
            if (t.getId().equals(host.getId())) {
                if (t.getRentalAgreements().contains(null)) {t.getRentalAgreements().removeIf(Objects::isNull);}
                t.getRentalAgreements().add(rentalAgreement);
                if (!t.getCooperatingOwners().contains(rentalAgreement.getOwner())) {
                    if (t.getCooperatingOwners().contains(null)) {t.getCooperatingOwners().removeIf(Objects::isNull);}
                    t.getCooperatingOwners().add(rentalAgreement.getOwner());
                }
                if (!t.getProperties().contains(rentalAgreement.getPropertyLeased())) {
                    if (t.getProperties().contains(null)) {t.getProperties().removeIf(Objects::isNull);}
                    t.getProperties().add(rentalAgreement.getPropertyLeased());
                }
            }
        }
        for (Owner t : model_list.getOwners()) {
            if (t.getId().equals(owner.getId())) {
                if(t.getRentalAgreements().contains(null)) {t.getRentalAgreements().removeIf(Objects::isNull);}
                t.getRentalAgreements().add(rentalAgreement);
                if (!t.getHostsManagingProperties().contains(rentalAgreement.getHost())) {
                    if(t.getHostsManagingProperties().contains(null)) {t.getHostsManagingProperties().removeIf(Objects::isNull);}
                    t.getHostsManagingProperties().add(rentalAgreement.getHost());
                }
                if (!t.getPropertiesOwned().contains(rentalAgreement.getPropertyLeased())) {
                    if (t.getPropertiesOwned().contains(null)) {t.getPropertiesOwned().removeIf(Objects::isNull);}
                    t.getPropertiesOwned().add(rentalAgreement.getPropertyLeased());
                }
            }
        }
        if (propertyLeased.getPropertyID().startsWith("RP")) {
            for(ResidentialProperty rp : model_list.getResidentialProperties()) {
                if (rp.getPropertyID().equals(propertyLeased.getPropertyID())) {
                    // Add the owner in ResidentialProperty
                    rp.setOwner(rentalAgreement.getOwner());
                    // Add the host in ResidentialProperty
                    if (!propertyLeased.getHosts().contains(rentalAgreement.getHost())) {
                        if(rp.getHosts().contains(null)) {rp.getHosts().removeIf(Objects::isNull);}
                        rp.getHosts().add(rentalAgreement.getHost());
                    }
                }
            }
        }
        else if (propertyLeased.getPropertyID().startsWith("CP")) {
            for (CommercialProperty cp : model_list.getCommercialProperties()) {
                if (cp.getPropertyID().equals(propertyLeased.getPropertyID())) {
                    // Add the owner in CommercialProperty
                    cp.setOwner(rentalAgreement.getOwner());
                    // Add the host in ResidentialProperty
                    if (!propertyLeased.getHosts().contains(rentalAgreement.getHost())) {
                        if(cp.getHosts().contains(null)) {cp.getHosts().removeIf(Objects::isNull);}
                        cp.getHosts().add(rentalAgreement.getHost());
                    }
                }
            }
        }
    }
    @Override
    public void updateRentalAgreement(String id, int num_) {
        Scanner scanner = Input.getDataInput().getScanner();
        Tenant mainTenant; Tenant subTenant; Host host;
        Owner owner; String num; int number;
        RentalAgreement agreementUpdate = DataPersistenceImp.getRentalAgreement(model_list.getRentalAgreements(),id);
        assert agreementUpdate != null;
        String oldPropertyID = agreementUpdate.getPropertyLeased().getPropertyID();

        switch (num_) {
            // Update mainTenant
            case 1 -> {
                // Ask the user input valid Tenant ID
                do {
                    do {
                        System.out.print("\nEnter new Main Tenant ID as a number: ");
                        num = scanner.nextLine();
                    } while (ValidateInput.isInteger(num));
                    number = Integer.parseInt(num);
                    String mainTenantID = "TN" + ((number < 10) ? "00" : "0") + number;
                    mainTenant = DataPersistenceImp.getTenant(model_list.getTenants(), mainTenantID);  // Assuming a method to retrieve Tenant by ID
                } while (mainTenant == null || number < 1 || number > model_list.getTenants().size());// Updating new Main Tenant

                // Modify other model
                for (Tenant t : model_list.getTenants()) {
                    // Remove the previous Rental Agreement
                    if (t.getId().equals(agreementUpdate.getMainTenantID()) && t.getRentalAgreements() != null) {
                        t.getRentalAgreements().remove(agreementUpdate);
                    }
                    // Add the new Rental Agreement
                    if (t.getId().equals(mainTenant.getId()) && !t.getRentalAgreements().contains(agreementUpdate)) {
                        if (t.getRentalAgreements().contains(null)) {t.getRentalAgreements().removeIf(Objects::isNull);}
                        t.getRentalAgreements().add(agreementUpdate);
                        System.out.println(t.getRentalAgreements());
                    }
                }
                // Updating new MainTenant
                for (RentalAgreement a : model_list.getRentalAgreements()) {
                    if (a.getAgreementID().equals(id)) {
                        a.setMainTenant(mainTenant);
                    }
                }
            }
            // Update sub-Tenant
            case 2 -> {
                String t = "0";
                for (RentalAgreement mainTenant_ : model_list.getRentalAgreements()) {
                    if (mainTenant_.getAgreementID().equals(id)) {
                        t = mainTenant_.getMainTenantID();
                    }
                }
                // Ask user input valid Sub-Tenant ID
                Set<Tenant> subTenants = new LinkedHashSet<>();
                System.out.print("Enter number of Sub-Tenants: ");
                int subTenantCount = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < subTenantCount; i++) {
                    do {
                        do {
                            System.out.print("Enter Sub-Tenant ID " + (i + 1) + " as a number: ");
                            num = scanner.nextLine();
                        } while (ValidateInput.isInteger(num) || Integer.parseInt(num) == Integer.parseInt(t.substring(2)));
                        number = Integer.parseInt(num);
                        String subTenantID = "TN" + ((number < 10) ? "00" : "0") + number;
                        subTenant = DataPersistenceImp.getTenant(model_list.getTenants(), subTenantID);
                    } while (subTenant == null || number < 1 || number > model_list.getTenants().size());
                    subTenants.add(subTenant);
                }
                // Convert Set to List
                List<Tenant> subTenantList = new ArrayList<>(subTenants);

                // Modify other model
                for (Tenant tenant : model_list.getTenants()) {
                    // Remove new RentalAgreement to Tenant
                    for (Tenant sub : agreementUpdate.getSubTenants()) {
                        if (tenant.getId().equals(sub.getId()) && tenant.getRentalAgreements() != null) {
                            tenant.getRentalAgreements().remove(agreementUpdate);
                        }
                    }
                    // Add new RentalAgreement to Tenant
                    for (Tenant sub : subTenantList) {
                        if (tenant.getId().equals(sub.getId()) && !tenant.getRentalAgreements().contains(agreementUpdate)) {
                            if(tenant.getRentalAgreements().contains(null)) {tenant.getRentalAgreements().removeIf(Objects::isNull);}
                            tenant.getRentalAgreements().add(agreementUpdate);
                        }
                    }
                }
                // Updating new Sub Tenant
                for (RentalAgreement a : model_list.getRentalAgreements()) {
                    if (a.getAgreementID().equals(id)) {
                        a.setSubTenants(subTenantList);
                    }
                }
            }
            // Update Host
            case 3 -> {
                // Ask valid Host ID
                do {
                    do {
                        System.out.print("Enter Host ID as a number: ");
                        num = scanner.nextLine();
                    } while (ValidateInput.isInteger(num));
                    number = Integer.parseInt(num);
                    String hostID = "HS" + ((number < 10) ? "00" : "0") + number;
                    host = DataPersistenceImp.getHost(model_list.getHosts(), hostID); // Assuming a method to retrieve Host by ID
                } while (host == null || number < 1 || number > model_list.getHosts().size());

                // Modify other model
                for (Host h : model_list.getHosts()) {
                    if (h.getId().equals(agreementUpdate.getHost().getId())) {
                        // Remove the previous Property
                        h.getProperties().removeIf(p -> p != null && h.getProperties() != null && p.getPropertyID().equals(agreementUpdate.getPropertyLeased().getPropertyID()));
                        // Remove the previous Owner
                        h.getCooperatingOwners().removeIf(o -> o != null && h.getCooperatingOwners() != null && o.getId().equals(agreementUpdate.getOwner().getId()));
                        // Remove the previous Rental Agreement
                        h.getRentalAgreements().removeIf(a -> a != null && h.getProperties() != null && a.getAgreementID().equals(id));
                    }
                    if (h.getId().equals(host.getId())) {
                        if(h.getProperties().contains(null)) {h.getRentalAgreements().removeIf(Objects::isNull);}
                        if(h.getCooperatingOwners().contains(null)) {h.getRentalAgreements().removeIf(Objects::isNull);}
                        if(h.getRentalAgreements().contains(null)) {h.getRentalAgreements().removeIf(Objects::isNull);}
                        // Add the new Property
                        if(!h.getProperties().contains(agreementUpdate.getPropertyLeased())){h.getProperties().add(agreementUpdate.getPropertyLeased());}
                        // Add the new Owner
                        if(!h.getCooperatingOwners().contains(agreementUpdate.getOwner())){h.getCooperatingOwners().add(agreementUpdate.getOwner());}
                        // Add the new Rental Agreement
                        if(!h.getRentalAgreements().contains(agreementUpdate)){h.getRentalAgreements().add(agreementUpdate);}
                    }
                }
                // Updating new Host
                for (RentalAgreement a : model_list.getRentalAgreements()) {
                    if (a.getAgreementID().equals(id)) {
                        a.setHost(host);
                    }
                }
            }
            // Update Owner
            case 4 -> {
                // Ask valid Owner ID
                do {
                    do {
                        System.out.print("Enter Owner ID as a number: ");
                        num = scanner.nextLine();
                    } while (ValidateInput.isInteger(num));
                    number = Integer.parseInt(num);
                    String ownerID = "ON" + ((number < 10) ? "00" : "0") + number;
                    owner = DataPersistenceImp.getOwner(model_list.getOwners(), ownerID); // Assuming a method to retrieve Owner by ID
                } while (owner == null || number < 1 || number > model_list.getOwners().size());

                // Modify other model
                for (Owner h : model_list.getOwners()) {
                    if (h.getId().equals(agreementUpdate.getOwner().getId())) {
                        // Remove the previous Property
                        h.getPropertiesOwned().removeIf(p -> p != null && h.getPropertiesOwned() != null && p.getPropertyID().equals(agreementUpdate.getPropertyLeased().getPropertyID()));
                        // Remove the previous Owner
                        h.getHostsManagingProperties().removeIf(o -> o != null && h.getHostsManagingProperties() != null && o.getId().equals(agreementUpdate.getHost().getId()));
                        // Remove the previous Rental Agreement
                        h.getRentalAgreements().removeIf(a -> a != null && h.getRentalAgreements() != null && a.getAgreementID().equals(id));
                    }
                    if (h.getId().equals(owner.getId())) {
                        if(h.getPropertiesOwned().contains(null)) {h.getPropertiesOwned().removeIf(Objects::isNull);}
                        if(h.getHostsManagingProperties().contains(null)) {h.getHostsManagingProperties().removeIf(Objects::isNull);}
                        if(h.getRentalAgreements().contains(null)) {h.getRentalAgreements().removeIf(Objects::isNull);}
                        // Add the new Property
                        if(!h.getPropertiesOwned().contains(agreementUpdate.getPropertyLeased())){h.getPropertiesOwned().add(agreementUpdate.getPropertyLeased());}
                        // Add the new Host
                        if(!h.getHostsManagingProperties().contains(agreementUpdate.getHost())){h.getHostsManagingProperties().add(agreementUpdate.getHost());}
                        // Add the new Rental Agreement
                        if(!h.getRentalAgreements().contains(agreementUpdate)){h.getRentalAgreements().add(agreementUpdate);}
                    }
                }
                // Updating new Owner
                for (RentalAgreement a : model_list.getRentalAgreements()) {
                    if (a.getAgreementID().equals(id)) {
                        a.setOwner(owner);
                    }
                }
            }
            // Update Property
            case 5 -> {
                // Ask user input valid Property ID
                Property propertyLeased = checkPropertyExits();
                String newPropertyID = propertyLeased.getPropertyID();

                // Modify other model
                if (oldPropertyID.startsWith("RP")) {
                    for (ResidentialProperty rp : model_list.getResidentialProperties()) {
                        // Delete the previous owner in ResidentialProperty
                        if (rp.getPropertyID().equals(oldPropertyID) && rp.getHosts() != null) {
                            rp.getHosts().remove(agreementUpdate.getHost());
                        }
                    }
                } else if (oldPropertyID.startsWith("CP")) {
                    for (CommercialProperty cp : model_list.getCommercialProperties()) {
                        // Delete the previous owner in CommercialProperty
                        if (cp.getPropertyID().equals(oldPropertyID) && cp.getHosts() != null) {
                            cp.getHosts().remove(agreementUpdate.getHost());
                        }
                    }
                }
                if (newPropertyID.startsWith("RP")) {
                    for (ResidentialProperty rp : model_list.getResidentialProperties()) {
                        if (rp.getPropertyID().equals(newPropertyID)) {
                            // Add the new owner in ResidentialProperty
                            rp.setOwner(agreementUpdate.getOwner());
                            // Add the new host in ResidentialProperty
                            if (rp.getHosts().contains(null)) {
                                rp.getHosts().removeIf(Objects::isNull);
                            }
                            if (!propertyLeased.getHosts().contains(agreementUpdate.getHost())) {
                                rp.getHosts().add(agreementUpdate.getHost());

                            }
                        }
                    }
                } else if (newPropertyID.startsWith("CP")) {
                    for (CommercialProperty cp : model_list.getCommercialProperties()) {
                        if (cp.getPropertyID().equals(newPropertyID)) {
                            // Add the owner in CommercialProperty
                            cp.setOwner(agreementUpdate.getOwner());
                            // Add the host in CommercialProperty
                            if (cp.getHosts().contains(null)) {
                                cp.getHosts().removeIf(Objects::isNull);
                            }
                            if (!propertyLeased.getHosts().contains(agreementUpdate.getHost())) {
                                cp.getHosts().add(agreementUpdate.getHost());
                            }
                        }
                    }
                }
                for (Host h : model_list.getHosts()) {
                    if (h.getId().equals(agreementUpdate.getHost().getId())) {
                        // Remove the previous Property
                        h.getProperties().removeIf(p -> p != null && h.getProperties() != null && p.getPropertyID().equals(agreementUpdate.getPropertyLeased().getPropertyID()));
                        // Remove the previous Owner
                        h.getCooperatingOwners().removeIf(o -> o != null && h.getCooperatingOwners() != null && o.getId().equals(agreementUpdate.getOwner().getId()));
                    }
                    for (Host host_ : propertyLeased.getHosts()) {
                        if (h.getId().equals(host_.getId()) && propertyLeased.getHosts() != null) {
                            if (h.getProperties().contains(null)) {
                                h.getRentalAgreements().removeIf(Objects::isNull);
                            }
                            if (h.getCooperatingOwners().contains(null)) {
                                h.getRentalAgreements().removeIf(Objects::isNull);
                            }
                            // Add the new Property
                            if (!h.getProperties().contains(propertyLeased)) {
                                h.getProperties().add(propertyLeased);
                            }
                        }
                    }
                    // Add the new Owner
                    if (!h.getCooperatingOwners().contains(agreementUpdate.getOwner()) && h.getId().equals(agreementUpdate.getHost().getId())) {
                        h.getCooperatingOwners().add(agreementUpdate.getOwner());
                    }
                }
                for (Owner h : model_list.getOwners()) {
                    if (h.getId().equals(agreementUpdate.getOwner().getId())) {
                        // Remove the previous Property
                        h.getPropertiesOwned().removeIf(p -> p != null && h.getPropertiesOwned() != null && p.getPropertyID().equals(agreementUpdate.getPropertyLeased().getPropertyID()));
                        // Remove the previous Owner
                        h.getHostsManagingProperties().removeIf(o -> o != null && h.getHostsManagingProperties() != null && o.getId().equals(agreementUpdate.getHost().getId()));
                    }
                    if (h.getId().equals(propertyLeased.getOwner().getId())) {
                        if(h.getPropertiesOwned().contains(null)) {h.getPropertiesOwned().removeIf(Objects::isNull);}
                        if(h.getHostsManagingProperties().contains(null)) {h.getHostsManagingProperties().removeIf(Objects::isNull);}
                        // Add the new Property
                        if(!h.getPropertiesOwned().contains(propertyLeased)){h.getPropertiesOwned().add(propertyLeased);}
                        // Add the new Owner
                        if(!h.getHostsManagingProperties().contains(agreementUpdate.getHost())){h.getHostsManagingProperties().add(agreementUpdate.getHost());}
                    }
                }

                // Updating new Property
                for (RentalAgreement a : model_list.getRentalAgreements()) {
                    if (a.getAgreementID().equals(id)) {
                        a.setPropertyLeased(propertyLeased);
                    }
                }
            }
        }
    }
    @Override
    public void deleteRentalAgreement(String id) {
        if (checkRentalAgreementExits(id)) {
            RentalAgreement a = DataPersistenceImp.getRentalAgreement(model_list.getRentalAgreements(), id);
            assert a != null;
            Property property = a.getPropertyLeased();


            // Modify other model
            for (Tenant t : model_list.getTenants()) {
                if (t.getId().equals(a.getMainTenantID()) && t.getRentalAgreements() != null) {
                    t.getRentalAgreements().remove(a);
                }
                for (Tenant sub : a.getSubTenants()) {
                    if (t.getId().equals(sub.getId()) && t.getRentalAgreements() != null) {
                        t.getRentalAgreements().remove(a);
                    }
                }
            }
            for (Host host : model_list.getHosts()) {
                if (host.getId().equals(a.getHost().getId())) {
                    if (host.getRentalAgreements() != null) {host.getRentalAgreements().remove(a);}
                    if (host.getProperties() != null) {host.getProperties().remove(property);}
                    if (host.getCooperatingOwners() != null) {host.getCooperatingOwners().remove(a.getOwner());}
                }
            }
            for (Owner owner : model_list.getOwners()) {
                if (owner.getId().equals(a.getOwner().getId())) {
                    if(owner.getRentalAgreements() != null) {owner.getRentalAgreements().remove(a);}
                    if(owner.getPropertiesOwned() != null) {owner.getPropertiesOwned().remove(property);}
                    if(owner.getHostsManagingProperties() != null) {owner.getHostsManagingProperties().remove(a.getHost());}
                }
            }
            if (property.getPropertyID().startsWith("RP")) {
                for (ResidentialProperty rp : model_list.getResidentialProperties()) {
                    if (rp.getPropertyID().equals(property.getPropertyID())) {
                        // Remove the host in ResidentialProperty
                        rp.getHosts().removeIf(h -> property.getHosts().contains(h));
                    }
                }
            }
            else if (property.getPropertyID().startsWith("CP")) {
                for (CommercialProperty cp : model_list.getCommercialProperties()) {
                    if (cp.getPropertyID().equals(property.getPropertyID())) {
                        // Remove the host in ResidentialProperty
                        cp.getHosts().removeIf(h -> property.getHosts().contains(h));
                    }
                }
            }

            // Removes the rental agreement from the list if its ID matches the specified ID.
            model_list.getRentalAgreements().removeIf(b -> b.getAgreementID().equals(id));
            System.out.println("Rental Agreement deleted");
        }
    }
    @Override
    public void viewAllRentalAgreements() {
        List<RentalAgreement>agreements = model_list.getRentalAgreements();
        sortRentalAgreements(agreements);
        System.out.println("\n====== All Rental Agreement Table ======\n");

        // Generating the Heading
        System.out.printf("%-15s | %-16s | %-25s | %-20s | %-15s | %-15s | %-12s | %-15s | %-12s | %-10s\n",
                "Agreement ID", "Main Tenant ID", "Sub-Tenants ID", "Property ID", "Host ID", "Owner ID", "Period", "Contract Date", "Renting Fee", "Status");
        System.out.println("-".repeat(182));

        // Loop through rentalAgreement
        for (RentalAgreement a : agreements) {
            System.out.println(a); // Display info on the terminal
        }

        // Generate report option
        System.out.println("\nImportance Note: If user type anything without y or yes, " +
                "the data won't save back to load file.");
        System.out.print("Do you want to generate the report and export into csv file? (Y/N): ");
        if (Input.getDataInput().getScanner().nextLine().toUpperCase().startsWith("Y")) {
            generateReport(agreements);
        }
    }
    @Override
    public void viewRentalAgreementsByOwnerName(String ownerName) {
        List<RentalAgreement>agreements = model_list.getRentalAgreements();
        List<RentalAgreement> report = new ArrayList<>();
        if (agreements == null) {
            System.out.println("No rental agreements available.");
            return;
        }
        System.out.println("\n===== All Rental Agreement that have Owner name (" + ownerName + ") =====\n");

        // Generating the Heading
        System.out.printf("%-15s | %-16s | %-25s | %-20s | %-15s | %-15s | %-12s | %-15s | %-12s | %-10s\n",
                "Agreement ID", "Main Tenant ID", "Sub-Tenants ID", "Property ID", "Host ID", "Owner ID", "Period", "Contract Date", "Renting Fee", "Status");
        System.out.println("-".repeat(182));

        // Loop through rentalAgreement
        for (RentalAgreement a : agreements) {
            if (a.getPropertyLeased().getOwner().getName().equals(ownerName)) {
                System.out.println(a);
                report.add(a);
            }
        }

        // Generate report option
        System.out.println("\nImportance Note: If user type anything without y or yes, " +
                "the data won't save back to load file.");
        System.out.print("Do you want to generate the report and export into csv file? (Y/N): ");
        if (Input.getDataInput().getScanner().nextLine().toUpperCase().startsWith("Y")) {
            generateReport(report);
        }
    }
    @Override
    public void viewRentalAgreementsByPropertyAddress(String address) {
        List<RentalAgreement>agreements = model_list.getRentalAgreements();
        List<RentalAgreement> report = new ArrayList<>();
        if (agreements == null) {
            System.out.println("No rental agreements available.");
            return;
        }
        System.out.println("\n===== All Rental Agreement that have Property address (" + address + ") =====\n");

        // Generating the Heading
        System.out.printf("%-15s | %-16s | %-25s | %-20s | %-15s | %-15s | %-12s | %-15s | %-12s | %-10s\n",
                "Agreement ID", "Main Tenant ID", "Sub-Tenants ID", "Property ID", "Host ID", "Owner ID", "Period", "Contract Date", "Renting Fee", "Status");
        System.out.println("-".repeat(182));

        // Loop through rentalAgreement
        for (RentalAgreement a : agreements) {
            if (a.getPropertyLeased().getAddress().equals(address)) {
                System.out.println(a);
                report.add(a);
            }
        }

        // Generate report option
        System.out.println("\nImportance Note: If user type anything without y or yes, " +
                "the data won't save back to load file.");
        System.out.print("Do you want to generate the report and export into csv file? (Y/N): ");
        if (Input.getDataInput().getScanner().nextLine().toUpperCase().startsWith("Y")) {
            generateReport(report);
        }
    }
    @Override
    public void viewRentalAgreementsByStatus(String status) {
        List<RentalAgreement>agreements = model_list.getRentalAgreements();
        List<RentalAgreement> report = new ArrayList<>();

        if (agreements == null) {
            System.out.println("No rental agreements available.");
            return;
        }
        System.out.println("\n===== All Rental Agreement that have Status (" + status + ") =====\n");

        // Generating the Heading
        System.out.printf("%-15s | %-16s | %-25s | %-20s | %-15s | %-15s | %-12s | %-15s | %-12s | %-10s\n",
                "Agreement ID", "Main Tenant ID", "Sub-Tenants ID", "Property ID", "Host ID", "Owner ID", "Period", "Contract Date", "Renting Fee", "Status");
        System.out.println("-".repeat(182));

        // Loop through rentalAgreement
        for (RentalAgreement a : agreements) {
            if (a.getStatus().equalsIgnoreCase(status)) {
                System.out.println(a);
                report.add(a);
            }
        }

        // Generate report option
        System.out.println("\nImportance Note: If user type anything without y or yes, " +
                "the data won't save back to load file.");
        System.out.print("Do you want to generate the report and export into csv file? (Y/N): ");
        if (Input.getDataInput().getScanner().nextLine().toUpperCase().startsWith("Y")) {
            generateReport(report);
        }
    }

    /**
     * Sort the given list by the RentalAgreement's id in ascending other
     * @param list - A list of all rental agreements
     * */
   private static void sortRentalAgreements(List<RentalAgreement> list) {
       list.sort(Comparator.comparing(RentalAgreement::getAgreementID));
   }

    /**
     * Generates a CSV report of rental agreements and saves it to a specified file.
     *
     * This method prompts the user for a filename and writes the details of each
     * RentalAgreement in the provided list to a CSV file. The generated file will
     * include headers for the agreement's attributes.
     *
     * @param list a list of RentalAgreement objects to be included in the report
     */
   private static void generateReport(List<RentalAgreement> list) {
       System.out.print("Please enter the name of the file you want to save into: ");
       String filename = Input.getDataInput().getScanner().nextLine();
       try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/SaveReport/" + filename + ".csv"))) {
           writer.write("AgreementID,MainTenantID,SubTenantIDs,PropertyID,HostID,OwnerID,Period,ContractDate,RentingFee,Status");
           writer.newLine();
           for (RentalAgreement r : list) {
               writer.write(r.toCSV());
               writer.newLine();
           }

           System.out.println("Report generated successfully in src/SaveReport/" + filename + ".csv");
       } catch (IOException e) {
           System.err.println("Error saving data to CSV: IOException");
       }
   }
}
