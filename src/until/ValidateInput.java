package until;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class ValidateInput {
    /**
     * Validate the payment method provided by user
     * @param method - the payment method to validate
     * @return a string representing the validated payment method
     */
    public static String validatePaymentMethod(String method) {
        while (true) {
            switch (method.toLowerCase()) {
                case "credit card" -> {
                    return "Credit Card";
                }
                case "cash" -> {
                    return "Cash";
                }
                case "bank transfer" -> {
                    return "Bank Transfer";
                }
                default -> {
                    System.out.println("Invalid Method, " +
                            "Please choose 1 from those methods (Credit Card, Cash, Bank Transfer)");
                    method = Input.getDataInput().getScanner().nextLine();
                }
            }
        }
    }
    /**
     * Validate the Property's status provided by user
     * @param status - Status of the property to validate
     * @return a string representing the validated Property's status
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
                    status = Input.getDataInput().getScanner().nextLine();
                }
            }
        }
    }

    /**
     * Validate the Business Type for commercial Property provided by user
     * @param type - Business type for commercial Property to validate
     * @return a string representing the validated Business type for commercial Property
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
                    type = Input.getDataInput().getScanner().nextLine();
                }
            }
        }
    }

    /**
     * Validate Rental Agreement Period (daily, weekly, fortnightly, monthly)
     * @param period - Rental period giving by user
     * @return String - Valid period
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
                    period = Input.getDataInput().getScanner().nextLine();
                }
            }
        }
    }

    /**
     * Validate Rental Agreement Status (new, active, complete)
     * @param status - Status of the agreement to validate
     * @return String - Valid status
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
                    status = Input.getDataInput().getScanner().nextLine();
                }
            }
        }
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
    public static int validateChoice(int upperBound) {
        int value = 0;

        while (true) {
            System.out.print("Please select an appropriate option: ");
            String userInput = Input.getDataInput().getScanner().nextLine(); // Read the entire line
            if (userInput.isEmpty() || isInteger(userInput)
                    || (value = Integer.parseInt(userInput)) < 0
                    || value > upperBound) {
                System.out.println("Invalid input. Please enter a valid integer between 0 and " + upperBound + ".");
            } else {
                return value; // Return the valid integer
            }
        }
    }

    /**
     * Prompts the user to enter a date in the format dd/MM/yyyy, validates the input,
     * and returns a valid Date object.
     *
     * @return a valid Date object entered by the user.
     */
    public static Date validateContactDate() {
        Scanner scanner = Input.getDataInput().getScanner();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false); // Ensure strict date parsing
        Date date = null;
        while (date == null) {
            System.out.print("Please enter a date (dd/MM/yyyy): ");
            String input = scanner.nextLine();
            int date_ = Integer.parseInt(input.substring(0,2));
            int month_ = Integer.parseInt(input.substring(3,5));
            int year_ = Integer.parseInt(input.substring(6));
            try {
                // Parse the input date
                date = dateFormat.parse(input);
                // Validate day, month, and year
                if (!isValidDate(date_, month_, year_)) {
                    System.out.println("Invalid date. Please ensure the day, month, and year are correct.");
                    date = null;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
        return date;
    }

    /**
     * Checks if the given year is a leap year.
     *
     * @param year the year to check
     * @return true if the year is a leap year; false otherwise
     */
    private static boolean isLeapYear (int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * Checks if the given day is valid for the specified month and year.
     *
     * @param day   the day to check
     * @param month the month to check (1-12)
     * @param year  the year to check
     * @return true if the day is valid for the month and year; false otherwise
     */
    private static boolean isValidDate ( int day, int month, int year){
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> // 31 days
                    day <= 31;
            case 4, 6, 9, 11 -> // 30 days
                    day <= 30;
            case 2 -> // February
                    day <= (isLeapYear(year) ? 29 : 28);
            default -> false; // Invalid month
        };
    }


        /**
         * Checks if the provided string can be parsed as an integer.
         * @param str the string to be checked
         * @return {@code true} if the string can be parsed as an integer;
         *         {@code false} otherwise
         * @throws NullPointerException if the input string is {@code null}
         */
    public static boolean isInteger (String str){
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}

