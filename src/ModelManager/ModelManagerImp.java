package ModelManager;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

import FileManager.DataPersistenceImp;
import model.*;
import until.Input;

public class ModelManagerImp implements ModelManager {
    private DataPersistenceImp model_list;

    // Constructor
    public ModelManagerImp() {
        model_list = new DataPersistenceImp();
    }

    // A method for cleanup (useful for manual resource management)
    public void clearData() {
        model_list.clearData();
    }

    // Setter
    public void setWholeModelList(DataPersistenceImp list) {
        this.model_list = list;
    }
    /**
     * 5 methods below served for display specific model (Tenant, Host, Owner, Residential/Commercial Property)
     * in the console
     */
    @Override
    public void viewAllTenants() {
        List<Tenant> tenants = model_list.getTenants();
        sortTenant(tenants);
        System.out.println("\n====== All Tenants Table ======\n");

        // Generating the Heading
        System.out.printf("%-6s | %-20s | %-15s | %-30s| %-15s | %-25s\n",
                "ID", "Name", "Date of Birth", "Contact Info", "PaymentID", "RentalAgreementID");
        System.out.println("-".repeat(120));

        // Loop through tenant
        for (Tenant t : tenants) {
            System.out.println(t.toString()); // Display info on the terminal
        }

        // Generate report option
        System.out.println("\nImportance Note: If user type anything without y or yes, " +
                "the data won't save back to load file.");
        System.out.print("Do you want to generate the report and export into csv file? (Y/N): ");
        if (Input.getDataInput().getScanner().nextLine().toUpperCase().startsWith("Y")) {
            reportTenant(tenants);
        }
    }
    @Override
    public void viewAllHosts() {
        List<Host> hosts = model_list.getHosts();
        sortHost(hosts);
        System.out.println("\n====== All Hosts Table ======\n");

        // Generating the Heading
        System.out.printf("%-6s | %-20s | %-15s | %-30s| %-15s | %-15s | %-20s\n",
                "ID", "Name", "Date of Birth", "Contact Info", "PropertyID", "OwnerID" ,"RentalAgreementID");
        System.out.println("-".repeat(135));

        // Loop through host
        for (Host h : hosts) {
            System.out.println(h.toString()); // Display info on the terminal
        }

        // Generate report option
        System.out.println("\nImportance Note: If user type anything without y or yes, " +
                "the data won't save back to load file.");
        System.out.print("Do you want to generate the report and export into csv file? (Y/N): ");
        if (Input.getDataInput().getScanner().nextLine().toUpperCase().startsWith("Y")) {
            reportHost(hosts);
        }
    }
    @Override
    public void viewAllOwners() {
        List<Owner> owners = model_list.getOwners();
        sortOwner(owners);
        System.out.println("\n====== All Owners Table ======\n");

        // Generating the Heading
        System.out.printf("%-6s | %-20s | %-15s | %-30s| %-15s | %-15s | %-20s\n",
                "ID", "Name", "Date of Birth", "Contact Info", "PropertyID", "HostID" ,"RentalAgreementID");
        System.out.println("-".repeat(135));

        // Loop through owners
        for (Owner owner : owners) {
            System.out.println(owner.toString()); // Display info on the terminal
        }

        // Generate report option
        System.out.println("\nImportance Note: If user type anything without y or yes, " +
                "the data won't save back to load file.");
        System.out.print("Do you want to generate the report and export into csv file? (Y/N): ");
        if (Input.getDataInput().getScanner().nextLine().toUpperCase().startsWith("Y")) {
            reportOwners(owners);
        }
    }
    @Override
    public void viewAllResidentialProperties() {
        List<ResidentialProperty> residentialProperties = model_list.getResidentialProperties();
        sortResidentialProperties(residentialProperties);
        System.out.println("\n====== All Residential Properties Table ======\n");

        // Generating the Heading
        System.out.printf("%-12s | %-25s | %-10s | %-20s | %-8s | %-15s | %-19s | %-15s | %-12s\n",
                "Property ID", "Address", "Pricing", "Status", "Owner ID", "Host ID", "Number of bedroom", "Include Garden", "Pet-Friendly");
        System.out.println("-".repeat(161));

        // Loop through residentialProperties
        for (ResidentialProperty p : residentialProperties) {
            System.out.println(p.toString()); // Display info on the terminal
        }

        // Generate report option
        System.out.println("\nImportance Note: If user type anything without y or yes, " +
                "the data won't save back to load file.");
        System.out.print("Do you want to generate the report and export into csv file? (Y/N): ");
        if (Input.getDataInput().getScanner().nextLine().toUpperCase().startsWith("Y")) {
            reportResidentialProperties(residentialProperties);
        }
    }
    @Override
    public void viewAllCommercialProperties() {
        List<CommercialProperty> commercialProperties = model_list.getCommercialProperties();
        sortCommercialProperty(commercialProperties);
        System.out.println("\n====== All Commercial Properties Table ======\n");

        // Generating the Heading
        System.out.printf("%-12s | %-25s | %-10s | %-20s | %-8s | %-15s | %-15s | %-14s | %-15s\n",
                "Property ID", "Address", "Pricing", "Status", "Owner ID", "Host ID","Business Type", "Parking Space", "Square Footage");
        System.out.println("-".repeat(157));

        // Loop through commercialProperties
        for (CommercialProperty p : commercialProperties) {
            System.out.println(p.toString()); // Display info on the terminal
        }

        // Generate report option
        System.out.println("\nImportance Note: If user type anything without y or yes, " +
                "the data won't save back to load file.");
        System.out.print("Do you want to generate the report and export into csv file? (Y/N): ");
        if (Input.getDataInput().getScanner().nextLine().toUpperCase().startsWith("Y")) {
            reportCommercialProperties(commercialProperties);
        }
    }

    /**
     * Sort the given list by the Tenant's id in ascending other
     * @param list - A list of all Tenants
     */
    private static void sortTenant(List<Tenant> list) {
        list.sort(Comparator.comparing(Person::getId));
    }

    /**
     * Sort the given list by the Host's id in ascending other
     * @param list - A list of all Hosts
     */
    private static void sortHost(List<Host> list) {
        list.sort(Comparator.comparing(Person::getId));
    }

    /**
     * Sort the given list by the Owner's id in ascending other
     * @param list - A list of all Owner
     */
    private static void sortOwner(List<Owner> list) {
        list.sort(Comparator.comparing(Person::getId));
    }

    /**
     * Sort the given list by the Residential Property's id in ascending other
     * @param list - A list of all Residential Properties
     */
    private static void sortResidentialProperties(List<ResidentialProperty> list) {
        list.sort(Comparator.comparing(Property::getPropertyID));
    }

    /**
     * Sort the given list by the Commercial Property's id in ascending other
     * @param list - A list of all Commercial Properties
     */
    private static void sortCommercialProperty(List<CommercialProperty> list) {
        list.sort(Comparator.comparing(Property::getPropertyID));
    }

    private static void reportTenant(List<Tenant> list) {
        System.out.print("Please enter the name of the file you want to save into: ");
        String filename = Input.getDataInput().getScanner().nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/SaveReport/" + filename + ".csv"))) {
            writer.write("TenantID,FullName,DateOfBirth,ContactInfo,PaymentsID,RentalAgreementsID");
            writer.newLine();
            for (Tenant t : list) {
                writer.write(t.toCSV());
                writer.newLine();
            }
            System.out.println("Report generated successfully in src/SaveReport/" + filename + ".csv");
        } catch (IOException e) {
            System.err.println("Error saving data to CSV: IOException");
        }
    }
    private static void reportHost(List<Host> list) {
        System.out.print("Please enter the name of the file you want to save into: ");
        String filename = Input.getDataInput().getScanner().nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/SaveReport/" + filename + ".csv"))) {
            writer.write("TenantID,FullName,DateOfBirth,ContactInfo,PropertyID,OwnerID,AgreementID");
            writer.newLine();
            for (Host h : list) {
                writer.write(h.toCSV());
                writer.newLine();
            }

            System.out.println("Report generated successfully in src/data/" + filename + ".csv");
        } catch (IOException e) {
            System.err.println("Error saving data to CSV: IOException");
        }
    }
    private static void reportOwners(List<Owner> list) {
        System.out.print("Please enter the name of the file you want to save into: ");
        String filename = Input.getDataInput().getScanner().nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/SaveReport/" + filename + ".csv"))) {
            writer.write("TenantID,FullName,DateOfBirth,ContactInfo,PropertyID,HostID,AgreementID");
            writer.newLine();
            for (Owner o : list) {
                writer.write(o.toCSV());
                writer.newLine();
            }

            System.out.println("Report generated successfully in src/SaveReport/" + filename + ".csv");
        } catch (IOException e) {
            System.err.println("Error saving data to CSV: IOException");
        }
    }
    private static void reportResidentialProperties(List<ResidentialProperty> list) {
        System.out.print("Please enter the name of the file you want to save into: ");
        String filename = Input.getDataInput().getScanner().nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/SaveReport/" + filename + ".csv"))) {
            writer.write("PropertyID,Address,Pricing,Status,OwnerID,HostIDs,Bedrooms,GardenAvailable,PetFriendly");
            writer.newLine();
            for (ResidentialProperty rp : list) {
                writer.write(rp.toCSV());
                writer.newLine();
            }

            System.out.println("Report generated successfully in src/SaveReport/" + filename + ".csv");
        } catch (IOException e) {
            System.err.println("Error saving data to CSV: IOException");
        }
    }
    private static void reportCommercialProperties(List<CommercialProperty> list) {
        System.out.print("Please enter the name of the file you want to save into: ");
        String filename = Input.getDataInput().getScanner().nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/SaveReport/" + filename + ".csv"))) {
            writer.write("PropertyID,Address,Pricing,Status,OwnerID,HostIDs,BusinessType,ParkingSpaces,SquareFootage");
            writer.newLine();
            for (CommercialProperty cp : list) {
                writer.write(cp.toCSV());
                writer.newLine();
            }

            System.out.println("Report generated successfully in src/SaveReport/" + filename + ".csv");
        } catch (IOException e) {
            System.err.println("Error saving data to CSV: IOException");
        }
    }
}
