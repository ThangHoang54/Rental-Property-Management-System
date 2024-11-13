import java.util.Scanner;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class ValidateInput {
    private static Scanner sc = new Scanner(System.in);

    /**
     *
     * @param method - Payment method giving by user
     */
    public static String validatePaymentMethod(String method) {
        while (true) {
            switch (method.toLowerCase()) {
                case "credit card" -> {return "Credit Card";}
                case "cash" -> {return "Cash";}
                case "bank transfer" -> {return "Bank Transfer";}
                default -> {
                    System.out.println("Invalid Method, " +
                            "Please choose 1 from those methods (Credit Card, Cash, Bank Transfer)");
                    method = sc.nextLine();
                }
            }
        }
    }

    /**
     *
     * @param status - Status of the property giving by user
     */
    public static String validatePropertyStatus(String status) {
        while (true) {
            switch (status.toLowerCase()) {
                case "available" -> {return "Available";}
                case "rented", "rent"-> {return "Rented";}
                case "under maintenance" -> {return "Under Maintenance";}
                default -> {
                    System.out.println("Invalid Status, " +
                            "Please choose 1 from those status (Available, Rented, Under Maintenance)");
                    status = sc.nextLine();
                }
            }
        }
    }

    /**
     *
     * @param type - Business type for commercial Property giving by user
     */
    public static String validateBusinessType(String type) {
        while (true) {
            switch (type.toLowerCase()) {
                case "retail" -> {return "Retail";}
                case "office" -> {return "Office";}
                case "hotel" -> {return "Hotel";}
                case "warehouse" -> {return "Warehouse";}
                case "factory" -> {return "Factory";}
                default -> {
                    System.out.println("Invalid type, " +
                            "Please choose from those business types (Retail, Office, Hotel, Factory, Warehouse)");
                    type = sc.nextLine();
                }
            }
        }
    }

    /**
     *
     * @param period - Rental period giving by user
     */
    public static String validateRentalPeriod(String period) {
        while (true) {
            switch (period.toLowerCase()) {
                case "daily" -> {return "Daily";}
                case "weekly" -> {return "Weekly";}
                case "fortnightly" -> {return "Fortnightly";}
                case "monthly" -> {return "Monthly";}
                default -> {
                    System.out.println("Invalid period, " +
                            "Please choose from those period for Rental (Daily, Weekly, Fortnightly, Monthly)");
                    period = sc.nextLine();
                }
            }
        }
    }


    /**
     *
     * @param status - Status of the agreement giving by user
     */
    public static String validateAgreementStatus(String status) {
        while (true) {
            switch (status.toLowerCase()) {
                case "new" -> {return "New";}
                case "active" -> {return "Active";}
                case "completed" -> {return "Completed";}
                default -> {
                    System.out.println("Invalid Method, " +
                            "Please choose 1 from those methods (New, Active, Completed)");
                    status = sc.nextLine();
                }
            }
        }
    }
}
