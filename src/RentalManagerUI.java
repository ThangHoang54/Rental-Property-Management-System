/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */
import FileManager.DataPersistenceImp;
import ModelManager.ModelManagerImp;
import RentalAgreementManager.RentalManagerImp;
import model.RentalAgreement;
import until.ValidateInput;
import until.Input;

import java.util.Date;

public class RentalManagerUI {
    // Constructor
    private void mainMenu(RentalManagerImp manager, ModelManagerImp model) {
        while (true) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. View Models (Tenant, Host, Owner, Residential Property, Commercial Property)");
            System.out.println("2. Manage Rental Agreements");
            System.out.println("0. Exit");

            int option = ValidateInput.validateChoice(2);
            switch (option) {
                case 1 -> modelsMenu(model);
                case 2 -> rentalAgreementMenu(manager);
                case 0 -> {
                    return; // Break the while loop and end the program
                }
                default -> System.out.println("Invalid option, please try again");
            }
        }
    }
    private void modelsMenu(ModelManagerImp model) {

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
    private void rentalAgreementMenu(RentalManagerImp manager) {
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
            String id = ""; String owner_name = "";
            String property_address = ""; String status = "";

            switch (option) {
                case 1 -> manager.addRentalAgreement();
                case 2 -> {
                    do {
                        System.out.print("Enter Rental Agreement ID you wish to update in form (RAxxx) (e.g RA009): ");
                        id = Input.getDataInput().getScanner().nextLine();
                    } while (!manager.checkRentalAgreementExits(id)); // Ensure the ID is exist
                    updateRentalAgreementMenu(manager, id);
                }
                case 3 -> {
                    do {
                        System.out.print("Enter Rental Agreement ID you wish to delete in form (RAxxx) (e.g, RA009): ");
                        id = Input.getDataInput().getScanner().nextLine();
                    } while (!manager.checkRentalAgreementExits(id)); // Ensure the ID is exist
                    manager.deleteRentalAgreement(id);
                }
                case 4 -> manager.viewAllRentalAgreements();
                case 5 -> {
                    DataPersistenceImp.viewAllExitsChoice(manager.getRentalAgreementList(),1); // Show all existing for Owner;s name
                    do {
                        System.out.print("\nEnter appropriate Owner Name that mention above: ");
                        owner_name = Input.getDataInput().getScanner().nextLine();
                    } while (!manager.checkOwnerNameExits(owner_name));
                    manager.viewRentalAgreementsByOwnerName(owner_name);
                }
                case 6 -> {
                    DataPersistenceImp.viewAllExitsChoice(manager.getRentalAgreementList(),2); // Show all existing Property's address
                    do {
                        System.out.print("\nEnter appropriate Property Address that mention above: ");
                        property_address = Input.getDataInput().getScanner().nextLine();
                    } while (!manager.checkPropertyAddressExits(property_address));
                    manager.viewRentalAgreementsByPropertyAddress(property_address);
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
    private void updateRentalAgreementMenu(RentalManagerImp manager, String agreementID) {
        while (true) {
            System.out.println("\n--- Update Rental Agreement: " + agreementID + " ---");
            System.out.println("1. Update Main Tenant");
            System.out.println("2. Update Sub-Tenants");
            System.out.println("3. Update Property Leased");
            System.out.println("4. Update Host");
            System.out.println("5. Update Owner");
            System.out.println("6. Update Rental Period");
            System.out.println("7. Delete Rental Contact Date");
            System.out.println("8. Update Renting Fee");
            System.out.println("9. Update Status");
            System.out.println("0. Finish update (Back to Rental Agreement Menu)");

            int choice = ValidateInput.validateChoice(9);

            switch (choice) {
                case 1 -> {
                    System.out.print("Updating new main Tenant: ");
                    manager.updateRentalAgreement(agreementID, 1);
                    System.out.println("Main Tenant updated successfully.");
                }
                case 2 -> {
                    System.out.println("Updating Sub-Tenants: ");
                    manager.updateRentalAgreement(agreementID, 2);
                    System.out.println("Sub-Tenants updated successfully.");
                }
                case 3 -> {
                    System.out.println("Updating Host: ");
                    manager.updateRentalAgreement(agreementID, 3);
                    System.out.println("Host updated successfully.");
                }
                case 4 -> {
                    System.out.println("Updating Owner: ");
                    manager.updateRentalAgreement(agreementID, 4);
                    System.out.println("Owner updated successfully.");
                }
                case 5 -> {
                    System.out.println("Updating Property Leased :");
                    manager.updateRentalAgreement(agreementID, 5);
                    System.out.println("Property Leased updated successfully.");
                }
                case 6 -> {
                    System.out.print("Enter new Rental Period (e.g., daily, weekly, monthly): ");
                    String newPeriod = ValidateInput.validateRentalPeriod(Input.getDataInput().getScanner().nextLine());
                    for (RentalAgreement a : manager.getRentalAgreementList()) {
                        if (a.getAgreementID().equals(agreementID)) {
                            a.setPeriod(newPeriod);
                        }
                    }
                    System.out.println("Rental Period updated successfully.");
                }
                case 7 -> {
                    Date newContactDate = ValidateInput.validateContactDate();
                    for (RentalAgreement a : manager.getRentalAgreementList()) {
                        if (a.getAgreementID().equals(agreementID)) {
                            a.setContractDate(newContactDate);
                        }
                    }
                    System.out.println("Rental Contact Date updated successfully.");
                }
                case 8 -> {
                    System.out.print("Enter new Renting Fee: ");
                    double newRentingFee = Input.getDataInput().getScanner().nextDouble();
                    for (RentalAgreement a : manager.getRentalAgreementList()) {
                        if (a.getAgreementID().equals(agreementID)) {
                            a.setRentingFee(newRentingFee);
                        }
                    }
                    System.out.println("Renting Fee updated successfully.");
                }
                case 9 -> {
                    System.out.print("Enter new Status (e.g., New, Active, Completed): ");
                    String newStatus = ValidateInput.validateAgreementStatus(Input.getDataInput().getScanner().nextLine());
                    for (RentalAgreement a: manager.getRentalAgreementList()) {
                        if(a.getAgreementID().equals(agreementID)) {a.setStatus(newStatus);}
                    }
                    System.out.println("Status updated successfully.");
                }
                case 0 -> {return;}
                default -> System.out.println("Invalid choice. Please try again.");
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

        System.out.println("\nImportance Note: If user type anything without y or yes, " +
                            "the data won't save back to load file.");
        System.out.print("Do you want to save all the data back to the load file for next used (y/n): ");
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
