/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */
import ModelManager.ModelManagerImp;
import RentalAgreementManager.RentalManagerImp;
import until.ValidateInput;
import until.Input;

public class RentalManagerUI {
    // Constructor
    public RentalManagerUI() {}
    public void mainMenu(RentalManagerImp manager, ModelManagerImp model) {
        while (true) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. View Models (Tenant, Host, Owner, Residential Property, Commercial Property)");
            System.out.println("2. Manage Rental Agreements");
            System.out.println("0. Exit");

            int option = ValidateInput.validateChoice(2);
            switch (option) {
                case 1 -> modelsMenu(manager, model);
                case 2 -> rentalAgreementMenu(manager, model);
                case 0 -> {
                    return; // Break the while loop and end the program
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }
    public void modelsMenu(RentalManagerImp manager, ModelManagerImp model) {

        while (true) {
            System.out.println("\n===== Model Menu =====");
            System.out.println("1. View All Tenants");
            System.out.println("2. View All Hosts");
            System.out.println("3. View All Owners");
            System.out.println("4. View All Residential Properties");
            System.out.println("5. View All Commercial Properties");
            System.out.println("0. Back to Main Menu");

            int option = ValidateInput.validateChoice(5);

            switch (option) {
                case 1 -> model.viewAllTenants();
                case 2 -> model.viewAllHosts();
                case 3 -> model.viewAllOwners();
                case 4 -> model.viewAllResidentialProperties();
                case 5 -> model.viewAllCommercialProperties();
                case 0 -> {return;}

                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public void rentalAgreementMenu(RentalManagerImp manager, ModelManagerImp model) {
        while (true) {
            // Display the Main Menu
            System.out.println("\n================== Rental Agreement Menu ====================");
            System.out.println(" 1. Add Rental Agreement");
            System.out.println(" 2. Update Rental Agreement");
            System.out.println(" 3. Delete Rental Agreement");
            System.out.println(" 4. View All Rental Agreements");
            System.out.println(" 5. view All Rental Agreements by Owner Name");
            System.out.println(" 6. view All Rental Agreements by Property Address");
            System.out.println(" 7. view All Rental Agreements by Status");
            System.out.println(" 0. Back to Main Menu");

            int option = ValidateInput.validateChoice(7);

            String id = ""; String status = "";
            System.out.println();

            switch (option) {
                case 1 -> manager.addRentalAgreement();
                case 2 -> {
                    do {
                        System.out.print("Enter Rental Agreement ID you wish to update: ");
                        id = Input.getDataInput().getScanner().nextLine();
                    } while (manager.checkRentalAgreementExits(id)); // Ensure the ID is exist
                    manager.updateRentalAgreement(id);
                }
                case 3 -> {
                    do {
                        System.out.print("Enter Rental Agreement ID you wish to delete: ");
                        id = Input.getDataInput().getScanner().nextLine();
                    } while (manager.checkRentalAgreementExits(id)); // Ensure the ID is exist
                    manager.deleteRentalAgreement(id);
                }
                case 4 -> manager.viewAllRentalAgreements();
                case 5 -> {
                    System.out.print("Enter appropriate Owner Name that includes Rental Agreement: ");
                    manager.viewRentalAgreementsByOwnerName(Input.getDataInput().getScanner().nextLine());
                }
                case 6 -> {
                    System.out.print("Enter appropriate Property Address: ");
                    manager.viewRentalAgreementsByPropertyAddress(Input.getDataInput().getScanner().nextLine());
                }
                case 7 -> {
                    do {
                        System.out.print("Enter appropriate Status that includes Rental Agreement(New/Active/Completed): ");
                        status = Input.getDataInput().getScanner().nextLine();
                    } while (!(status.equalsIgnoreCase("New") || status.equalsIgnoreCase("Active") || status.equalsIgnoreCase("Completed")));
                    manager.viewRentalAgreementsByStatus(status);
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static void main(String[] args) {
        // Declare variable
        RentalManagerImp manager = new RentalManagerImp();
        ModelManagerImp model = new ModelManagerImp();

        // Print out welcome message
        System.out.println("=== Welcome to Rental Agreement Management System ===");

        RentalManagerUI ui = new RentalManagerUI();
        ui.mainMenu(manager, model);

        System.out.print("Do you want to save all the data back to the file for next used (y/n)> ");
        if (Input.getDataInput().getScanner().nextLine().toLowerCase().charAt(0) == 'y') {
            // Save all data back to CSV file
            manager.saveData();
            System.out.println("Data saved successfully.");
        } else {
            System.out.println("Data not saved.");
        }

        // Clear all the data before ending the program
        manager.clearData();
        model.clearData();

        // Ending message
        System.out.println("Goodbye, I hope you will have the great time \u9999!!!");
        System.exit(0); // Exit terminating the current process
    }

}
