/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */
import ModelManager.ModelManager;
import RentalAgreementManager.RentalManagerImp;
import until.Input;

public class RentalManagerUI {
    // Constructor
    public RentalManagerUI() {}
    public void mainMenu(RentalManagerImp manager, ModelManager model) {
        while (true) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. View Models (Tenant, Host, Owner, Residential Property, Commercial Property)");
            System.out.println("2. Manage Rental Agreements");
            System.out.println("0. Exit");

            int option = validateChoice(2);
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
    public void modelsMenu(RentalManagerImp manager, ModelManager model) {

        while (true) {
            System.out.println("\n===== Model Menu =====");
            System.out.println("1. View All Tenants");
            System.out.println("2. View All Hosts");
            System.out.println("3. View All Owners");
            System.out.println("4. View All Residential Properties");
            System.out.println("5. View All Commercial Properties");
            System.out.println("0. Back to Main Menu");

            int option = validateChoice(5);
            System.out.println(option);
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
    public void rentalAgreementMenu(RentalManagerImp manager, ModelManager model) {
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

            int option = validateChoice(7);
            System.out.println(option);

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
        ModelManager model = new ModelManager();

        // Print out welcome message
        System.out.println("=== Welcome to Rental Agreement Management System ===\n");

        RentalManagerUI ui = new RentalManagerUI();
        ui.mainMenu(manager, model);

        // Clear all the data before ending the program
        manager.saveAndClearData();
        model.clearData();

        // Ending message
        System.out.println("Goodbye, I hope you will have the great time \u9999!!!");
        System.exit(0); // Exit terminating the current process
    }

    /**
     * This method continuously prompts the user until a valid integer between
     * 0 and the specified upper bound (inclusive) is entered. It validates
     * the input to ensure it is not empty and can be parsed as an integer.
     *
     * @param upperBound the upper bound for the valid range of integers
     * @return a valid integer selected by the user, which is between 0 and
     *         the specified upper bound (inclusive)
     */
    private int validateChoice(int upperBound) {
        int value = 0;

        while (true) {
            System.out.print("Please select an appropriate option: ");
            String userInput = Input.getDataInput().getScanner().nextLine(); // Read the entire line
            if (userInput.isEmpty() || !isInteger(userInput)
                    || (value = Integer.parseInt(userInput)) < 0
                    || value > upperBound) {
                System.out.println("Invalid input. Please enter a valid integer between 0 and " + upperBound + ".");
            } else {
                return value; // Return the valid integer
            }
        }
    }

    /**
     * Checks if the provided string can be parsed as an integer.
     * @param str the string to be checked
     * @return {@code true} if the string can be parsed as an integer;
     *         {@code false} otherwise
     * @throws NullPointerException if the input string is {@code null}
     */
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
