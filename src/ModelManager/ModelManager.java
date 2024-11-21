package ModelManager;
import java.util.Comparator;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

import FileManager.DataPersistenceManager;
import model.*;

public class ModelManager {
    private DataPersistenceManager model_list;

    // Constructor
    public ModelManager() {
        model_list = new DataPersistenceManager();
    }

    // A method for cleanup (useful for manual resource management)
    public void clearData() {
        model_list.clearData();
    }

    // Method
    /**
     * 5 methods below served for display specific model (Tenant, Host, Owner, Residential/Commercial Property)
     * in the console
     */
    public void viewAllTenants() {
        List<Tenant> tenants = model_list.getTenants();
        sortTenant(tenants);
        System.out.println("\n====== All Tenants Table ======\n");

        // Generating the Heading
        System.out.printf("%-6s | %-20s | %-15s | %-20s",
                "ID", "Name", "Date of Birth", "Contact Info");
        System.out.println("-".repeat(60));

        // Loop through tenant
        for (Tenant t : tenants) {
            // Heading
            System.out.println(t.toString()); // Display info on the terminal
        }
    }
    public void viewAllHosts() {
        List<Host> hosts = model_list.getHosts();
        sortHost(hosts);
        System.out.println("\n====== All Hosts Table ======\n");

        // Generating the Heading
        System.out.printf("%-6s | %-20s | %-15s | %-20s",
                "ID", "Name", "Date of Birth", "Contact Info");
        System.out.println("-".repeat(60));

        // Loop through host
        for (Host h : hosts) {
            System.out.println(h.toString()); // Display info on the terminal
        }
    }
    public void viewAllOwners() {
        List<Owner> owners = model_list.getOwners();
        sortOwner(owners);
        System.out.println("\n====== All Owners Table ======\n");

        // Generating the Heading
        System.out.printf("%-6s | %-20s | %-15s | %-20s",
                "ID", "Name", "Date of Birth", "Contact Info");
        System.out.println("-".repeat(60));

        // Loop through owners
        for (Owner owner : owners) {
            System.out.println(owner.toString()); // Display info on the terminal
        }
    }
    public void viewAllResidentialProperties() {
        List<ResidentialProperty> residentialProperties = model_list.getResidentialProperties();
        sortResidentialProperties(residentialProperties);
        System.out.println("\n====== All Residential Properties Table ======\n");

        // Generating the Heading
        System.out.printf("%-12s | %-25s | %-10s | %-20s | %-8s | %-19s | %-15s | %-12s\n",
                "Property ID", "Address", "Pricing", "Status", "Owner ID", "Number of bedroom", "Include Garden", "Pet-Friendly");
        System.out.println("-".repeat(100));

        // Loop through residentialProperties
        for (ResidentialProperty p : residentialProperties) {
            System.out.println(p.toString()); // Display info on the terminal
        }
    }
    public void viewAllCommercialProperties() {
        List<CommercialProperty> commercialProperties = model_list.getCommercialProperties();
        sortCommercialProperty(commercialProperties);
        System.out.println("\n====== All Commercial Properties Table ======\n");
        // Generating the Heading
        System.out.printf("%-12s | %-25s | %-10s | %-20s | %-8s | %-15s | %-14s | %-15s\n",
                "Property ID", "Address", "Pricing", "Status", "Owner ID", "Business Type", "Parking Space", "Square Footage");
        System.out.println("-".repeat(100));
        // Loop through commercialProperties
        for (CommercialProperty p : commercialProperties) {
            System.out.println(p.toString()); // Display info on the terminal
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
}
