import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */
public class RentalManagerImp implements RentalManager {
    private DataPersistenceManager model_list;

    // Constructor
    public RentalManagerImp() {
        model_list = new DataPersistenceManager();
    }

    /**
     * Loop through all rental agreement to determine is the give ID exists
     * @param id -- ID of the Rental Agreement
     * @return boolean
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

    @Override
    public void addRentalAgreement() {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter Main Tenant ID: ");
        String mainTenantID = "TN" + scanner.nextLine();
        Tenant mainTenant = DataPersistenceManager.getTenant(model_list.getTenants(), mainTenantID);  // Assuming a method to retrieve Tenant by ID

        List<Tenant> subTenants = new ArrayList<>();
        System.out.print("Enter number of Sub-Tenants: ");
        int subTenantCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < subTenantCount; i++) {
            System.out.print("Enter Sub-Tenant ID " + (i + 1) + ": ");
            String subTenantID = "TN" + scanner.nextLine();
            Tenant subTenant = DataPersistenceManager.getTenant(model_list.getTenants(), subTenantID);
            subTenants.add(subTenant);
        }

        System.out.print("Enter Property ID: ");
        String propertyID = "PP" + scanner.nextLine();
        Property propertyLeased = DataPersistenceManager.getProperty(model_list.getCommercialProperties(),
                model_list.getResidentialProperties(), propertyID); // Assuming a method to retrieve Property by ID

        System.out.print("Enter Host ID: ");
        String hostID = "HS" + scanner.nextLine();
        Host host = DataPersistenceManager.getHost(model_list.getHosts(), hostID); // Assuming a method to retrieve Host by ID

        System.out.print("Enter Owner ID: ");
        String ownerID = scanner.nextLine();
        Owner owner = DataPersistenceManager.getOwner(model_list.getOwners(), ownerID); // Assuming a method to retrieve Owner by ID

        System.out.print("Enter Rental Period (e.g., 'monthly'): ");
        String period = scanner.nextLine();

        System.out.print("Enter Contract Date (yyyy-MM-dd): ");
        String contractDateString = scanner.nextLine();
        Date contractDate;
        try {
            contractDate = new SimpleDateFormat("yyyy-MM-dd").parse(contractDateString);
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return;
        }

        System.out.print("Enter Renting Fee: ");
        double rentingFee = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Status (e.g., 'New', 'Active', 'Completed'): ");
        String status = ValidateInput.validateAgreementStatus(scanner.nextLine());

        // Create new Rental Agreement object
        RentalAgreement rentalAgreement = new RentalAgreement("", mainTenant, subTenants, propertyLeased,
                host, owner, period, contractDate, rentingFee, status
        );

        // Add rentalAgreement to the list or database (depending on your implementation)
        model_list.getRentalAgreements().add(rentalAgreement); // Assuming a List<RentalAgreement> rentalAgreements

        System.out.println("Rental Agreement added successfully: " + rentalAgreement);
    }
    @Override
    public void updateRentalAgreement(String id) {
        if (checkRentalAgreementExits(id)) {
            boolean found = false;
        }
        System.out.println("Rental Agreement updated");
    }
    @Override
    public void deleteRentalAgreement(String id) {
        if (checkRentalAgreementExits(id)) {
            // Removes the rental agreement from the list if its ID matches the specified ID.
            model_list.getRentalAgreements().removeIf(a -> a.getAgreementID().equals(id));
        }
        System.out.println("Rental Agreement deleted");
    }
    @Override
    public void viewAllRentalAgreements() {
        List<RentalAgreement>agreements = model_list.getRentalAgreements();
        sortRentalAgreements(agreements);
        System.out.println("\n====== All Rental Agreement Table ======\n");

        // Loop through rentalAgreement
        for (RentalAgreement a : agreements) {
            System.out.println(a.toString()); // Display info on the terminal
        }
    }
    @Override
    public void viewRentalAgreementsByOwnerName(String ownerName) {
        List<RentalAgreement>agreements = model_list.getRentalAgreements();
        if (agreements == null) {
            System.out.println("No rental agreements available.");
            return;
        }
        System.out.println("\n===== All Rental Agreement that have Owner name (" + ownerName + ") =====\n");
        // Loop through rentalAgreement
        int view = 1;
        for (RentalAgreement a : agreements) {
            if (a.getOwnerName().equals(ownerName)) {
                view++;
                System.out.println(a.toString());
            }
        }

        if (view == 1) {
            System.out.println("There is no Owner with this name that have Rental Agreements.");
        }
    }
    @Override
    public void viewRentalAgreementsByPropertyAddress(String address) {
        List<RentalAgreement>agreements = model_list.getRentalAgreements();
        if (agreements == null) {
            System.out.println("No rental agreements available.");
            return;
        }
        System.out.println("\n===== All Rental Agreement that have Property address (" + address + ") =====\n");
        int view = 1;
        // Loop through rentalAgreement
        for (RentalAgreement a : agreements) {
            if (a.getPropertyAddress().equals(address)) {
                view++;
                System.out.println(a.toString());
            }
        }

        if (view == 1) {
            System.out.println("There is no Property with this address that have Rental Agreements.");
        }

    }
    @Override
    public void viewRentalAgreementsByStatus(String status) {
        List<RentalAgreement>agreements = model_list.getRentalAgreements();
        if (agreements == null) {
            System.out.println("No rental agreements available.");
            return;
        }
        System.out.println("\n===== All Rental Agreement that have Status (" + status + ") =====\n");
        // Loop through rentalAgreement
        for (RentalAgreement a : agreements) {
            if (a.getStatus().equalsIgnoreCase(status)) {
                System.out.println(a.toString());
            }
        }
    }

    /**
     * Sort the given list by the RentalAgreement's id in ascending other
     * @param list - A list of all rental agreements
     */
   private static void sortRentalAgreements(List<RentalAgreement> list) {
       list.sort((o1, o2)
               -> o1.getAgreementID().compareTo(
               o2.getAgreementID()));
   }
}
