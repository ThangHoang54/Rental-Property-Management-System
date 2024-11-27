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

public class RentalManagerImp implements RentalManager {
    private DataPersistenceImp model_list;

    // Constructor
    public RentalManagerImp() {
        model_list = new DataPersistenceImp();
    }

    public void saveData() {
        model_list.saveDataToCSVFile();
    }
    // Add a method for save to csv file and cleanup (useful for manual resource management)
    public void clearData() {
        model_list.clearData();
    }
    // Getter
    public List<RentalAgreement> getRentalAgreementList() {
        return model_list.getRentalAgreements();
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
            if (a.getOwnerName().equals(name)) {
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
            if (a.getPropertyAddress().equals(address)) {
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
        Scanner scanner = Input.getDataInput().getScanner();
        Tenant mainTenant; Tenant subTenant; Host host;
        Owner owner; Property property; String num = "-1"; int number = 0;

        // Rental Agreement ID
        // Get the latest ID number of Rental Agreement
        int size = Integer.parseInt(model_list.getRentalAgreements().getLast().getAgreementID().substring(2));
        String id = "RA" + ((size < 10) ? "00" : "0") + (size + 1);

        // Main Tenant
        do {
            do {
                System.out.print("Enter Main Tenant ID as a number: ");
                num = scanner.nextLine();
            } while (!ValidateInput.isInteger(num));
            number = Integer.parseInt(num);
            String mainTenantID = "TN" + ((number < 10) ? "00" : "0") + number;
            mainTenant = DataPersistenceImp.getTenant(model_list.getTenants(), mainTenantID);  // Assuming a method to retrieve Tenant by ID

        } while (mainTenant == null || number < 1 || number > model_list.getTenants().size());

        // Sub-Tenant
        Set<Tenant> subTenants = new LinkedHashSet<>();
        System.out.print("Enter number of Sub-Tenants: ");
        int subTenantCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < subTenantCount; i++) {
            do {
                do {
                    System.out.print("Enter Sub-Tenant ID " + (i + 1) + "as a number: ");
                    num = scanner.nextLine();
                } while (!ValidateInput.isInteger(num) || Integer.parseInt(num) == Integer.parseInt(mainTenant.getId().substring(2)));
                number = Integer.parseInt(num);
                String subTenantID = "TN" + ((number < 10) ? "00" : "0") + number;;
                subTenant = DataPersistenceImp.getTenant(model_list.getTenants(), subTenantID);
            } while (subTenant == null || number < 1 || number > model_list.getTenants().size());
            subTenants.add(subTenant);
        }
        // Convert Set to List
        List<Tenant> subTenantList = new ArrayList<>(subTenants);

        Property propertyLeased = checkPropertyExits();
        do {
            do {
                System.out.print("Enter Host ID as a number: ");
                num = scanner.nextLine();
            } while (!ValidateInput.isInteger(num));
            number = Integer.parseInt(num);
            String hostID = "HS" + ((number < 10) ? "00" : "0") + number;
            host = DataPersistenceImp.getHost(model_list.getHosts(), hostID); // Assuming a method to retrieve Host by ID
        } while (host == null || number < 1 || number > model_list.getHosts().size());

        do {
            do {
                System.out.print("Enter Owner ID as a number: ");
                num = scanner.nextLine();
            } while (!ValidateInput.isInteger(num));
            number = Integer.parseInt(num);
            String ownerID = "ON" + ((number < 10) ? "00" : "0") + number;
            owner = DataPersistenceImp.getOwner(model_list.getOwners(), ownerID); // Assuming a method to retrieve Owner by ID
        } while (owner == null || number < 1 || number > model_list.getOwners().size());

        System.out.print("Enter Rental Period (daily, weekly, fortnightly, monthly): ");
        String period = ValidateInput.validateRentalPeriod(scanner.nextLine());
        
        Date contractDate = ValidateInput.validateContactDate();

        System.out.print("Enter Renting Fee (double type): ");
        double rentingFee = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Status (e.g., 'New', 'Active', 'Completed'): ");
        String status = ValidateInput.validateAgreementStatus(scanner.nextLine());

        // Create new Rental Agreement object using Builder pattern
        RentalAgreement rentalAgreement = new RentalAgreement.Builder(id).mainTenant(mainTenant)
                .subTenants(subTenantList).propertyLeased(propertyLeased).host(host).owner(owner)
                .period(period).contractDate(contractDate).rentingFee(rentingFee).status(status).build();

        // Modify other model
        for (Tenant t : model_list.getTenants()) {
            if (t.getId().equals(mainTenant.getId())) {
                t.addRentalAgreement(DataPersistenceImp.getRentalAgreement(model_list.getRentalAgreements(),id));
            }
        }
        for (Tenant t : model_list.getTenants()) {
            for (Tenant sub : subTenantList) {
                if (t.getId().equals(sub.getId())) {
                    t.addRentalAgreement(DataPersistenceImp.getRentalAgreement(model_list.getRentalAgreements(), id));
                }
            }
        }
        for (Host t : model_list.getHosts()) {
            if (t.getId().equals(host.getId())) {
                t.addRentalAgreement(DataPersistenceImp.getRentalAgreement(model_list.getRentalAgreements(),id));
            }
        }
        for (Owner t : model_list.getOwners()) {
            if (t.getId().equals(owner.getId())) {
                t.addRentalAgreement(DataPersistenceImp.getRentalAgreement(model_list.getRentalAgreements(),id));
            }
        }

        // Add rentalAgreement to the list or database (depending on your implementation)
        model_list.getRentalAgreements().add(rentalAgreement); // Assuming a List<RentalAgreement> rentalAgreements
        System.out.println("Rental Agreement added successfully");
    }
    @Override
    public void updateRentalAgreement(String id, int num_) {
        Scanner scanner = Input.getDataInput().getScanner();
        Tenant mainTenant; Tenant subTenant; Host host;
        Owner owner; Property property; String num = "-1"; int number = 0;

        switch (num_) {
               case 1 -> {
                   // Ask the user input valid Tenant ID
                   do {
                       do {
                           System.out.print("Enter new Main Tenant ID as a number: ");
                           num = scanner.nextLine();
                       } while (!ValidateInput.isInteger(num));
                       number = Integer.parseInt(num);
                       String mainTenantID = "TN" + ((number < 10) ? "00" : "0") + number;
                       mainTenant = DataPersistenceImp.getTenant(model_list.getTenants(), mainTenantID);  // Assuming a method to retrieve Tenant by ID
                   } while (mainTenant == null || number < 1 || number > model_list.getTenants().size());
                   // Updating new Main Tenant
                   for (RentalAgreement a : model_list.getRentalAgreements()) {
                       if (a.getAgreementID().equals(id)) {
                           a.setMainTenant(mainTenant);
                       }
                   }
               }
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
                               System.out.print("Enter Sub-Tenant ID " + (i + 1) + "as a number: ");
                               num = scanner.nextLine();
                           } while (!ValidateInput.isInteger(num) || Integer.parseInt(num) == Integer.parseInt(t.substring(2)));
                           number = Integer.parseInt(num);
                           String subTenantID = "TN" + ((number < 10) ? "00" : "0") + number;;
                           subTenant = DataPersistenceImp.getTenant(model_list.getTenants(), subTenantID);
                       } while (subTenant == null || number < 1 || number > model_list.getTenants().size());
                       subTenants.add(subTenant);
                   }
                   // Convert Set to List
                   List<Tenant> subTenantList = new ArrayList<>(subTenants);

                   // Updating new Sub Tenant
                   for (RentalAgreement a : model_list.getRentalAgreements()) {
                       if (a.getAgreementID().equals(id)) {
                           a.setSubTenants(subTenantList);
                       }
                   }
               }
               case 3 -> {
                   // Ask user input valid Property ID
                   Property propertyLeased = checkPropertyExits();
                   // Updating Property Leased
                   for (RentalAgreement a : model_list.getRentalAgreements()) {
                       if (a.getAgreementID().equals(id)) {
                           a.setPropertyLeased(propertyLeased);
                       }
                   }
               }
           }
           System.out.println("Rental Agreement updated");
    }
    @Override
    public void deleteRentalAgreement(String id) {
        if (checkRentalAgreementExits(id)) {
            // Removes the rental agreement from the list if its ID matches the specified ID.
            model_list.getRentalAgreements().removeIf(a -> a.getAgreementID().equals(id));

            List<Tenant> tenants = model_list.getTenants();
            for (Tenant tenant : tenants) {
                tenant.getRentalAgreements().removeIf(a -> a.getAgreementID().equals(id));
            }

            List<Host> hosts = model_list.getHosts();
            for (Host host : hosts) {
                host.getRentalAgreements().removeIf(a -> a.getAgreementID().equals(id));
            }

            List<Owner> owners = model_list.getOwners();
            for (Owner owner : owners) {
                owner.getRentalAgreements().removeIf(a -> a.getAgreementID().equals(id));
            }
        }
        System.out.println("Rental Agreement deleted");
    }
    @Override
    public void viewAllRentalAgreements() {
        List<RentalAgreement>agreements = model_list.getRentalAgreements();
        sortRentalAgreements(agreements);
        System.out.println("\n====== All Rental Agreement Table ======\n");

        // Generating the Heading
        System.out.printf("%-15s | %-16s | %-25s | %-20s | %-15s | %-15s | %-10s | %-15s | %-12s | %-10s\n",
                "Agreement ID", "Main Tenant ID", "Sub-Tenants ID", "Property ID", "Host ID", "Owner ID", "Period", "Contract Date", "Renting Fee", "Status");
        System.out.println("-".repeat(170));

        // Loop through rentalAgreement
        for (RentalAgreement a : agreements) {
            System.out.println(a); // Display info on the terminal
        }

        // Generate report option
        System.out.print("\nDo you want to generate the report and export into csv file? (Y/N): ");
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
        System.out.printf("%-15s | %-16s | %-25s | %-20s | %-15s | %-15s | %-10s | %-15s | %-12s | %-10s\n",
                "Agreement ID", "Main Tenant ID", "Sub-Tenants ID", "Property ID", "Host ID", "Owner ID", "Period", "Contract Date", "Renting Fee", "Status");
        System.out.println("-".repeat(170));

        // Loop through rentalAgreement
        for (RentalAgreement a : agreements) {
            if (a.getOwnerName().equals(ownerName)) {
                System.out.println(a);
                report.add(a);
            }
        }

        // Generate report option
        System.out.print("\nDo you want to generate the report and export into csv file? (Y/N): ");
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
        System.out.printf("%-15s | %-16s | %-25s | %-20s | %-15s | %-15s | %-10s | %-15s | %-12s | %-10s\n",
                "Agreement ID", "Main Tenant ID", "Sub-Tenants ID", "Property ID", "Host ID", "Owner ID", "Period", "Contract Date", "Renting Fee", "Status");
        System.out.println("-".repeat(150));

        // Loop through rentalAgreement
        for (RentalAgreement a : agreements) {
            if (a.getPropertyAddress().equals(address)) {
                System.out.println(a);
                report.add(a);
            }
        }

        // Generate report option
        System.out.print("\nDo you want to generate the report and export into csv file? (Y/N): ");
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
        System.out.printf("%-15s | %-16s | %-25s | %-20s | %-15s | %-15s | %-10s | %-15s | %-12s | %-10s\n",
                "Agreement ID", "Main Tenant ID", "Sub-Tenants ID", "Property ID", "Host ID", "Owner ID", "Period", "Contract Date", "Renting Fee", "Status");
        System.out.println("-".repeat(170));

        // Loop through rentalAgreement
        for (RentalAgreement a : agreements) {
            if (a.getStatus().equalsIgnoreCase(status)) {
                System.out.println(a);
                report.add(a);
            }
        }

        // Generate report option
        System.out.print("\nDo you want to generate the report and export into csv file? (Y/N): ");
        if (Input.getDataInput().getScanner().nextLine().toUpperCase().startsWith("Y")) {
            generateReport(report);
        }
    }

    /**
     * Sort the given list by the RentalAgreement's id in ascending other
     * @param list - A list of all rental agreements
     */
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
       String filename = Input.getDataInput().getScanner().next();
       try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/data/" + filename + ".csv"))) {
           writer.write("AgreementID,MainTenantID,SubTenantIDs,PropertyID,HostID,OwnerID,Period,ContractDate,RentingFee,Status");
           writer.newLine();
           for (RentalAgreement r : list) {
               writer.write(r.toCSV());
               writer.newLine();
           }

           System.out.println("Report generated successfully in src/data/" + filename + ".csv");
       } catch (IOException e) {
           System.err.println("Error saving data to CSV: IOException");
       }
   }
}
