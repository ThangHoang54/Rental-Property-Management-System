package FileManager;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <Hoàng Minh Thắng - S3999925>
 */

import model.*;
import until.Input;

public class DataPersistenceImp implements DataPersistenceManager{
    private List<RentalAgreement> rentalAgreements;
    private List<Tenant> tenants;
    private List<Host> hosts;
    private List<Owner> owners;
    private List<ResidentialProperty> residentialProperties;
    private List<CommercialProperty> commercialProperties;
    private List<Payment> payments;

    // Constructor
    public DataPersistenceImp() {
        rentalAgreements = new ArrayList<>();
        tenants = new ArrayList<>();
        hosts = new ArrayList<>();
        owners = new ArrayList<>();
        residentialProperties = new ArrayList<>();
        commercialProperties = new ArrayList<>();
        payments = new ArrayList<>();
        // Load all data from 7 csv files
        loadDataToList();
    }

    // Getter
    public List<RentalAgreement> getRentalAgreements() {
        return rentalAgreements;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public List<ResidentialProperty> getResidentialProperties() {
        return residentialProperties;
    }

    public List<CommercialProperty> getCommercialProperties() {
        return commercialProperties;
    }

    /**
     * Loads data from multiple CSV files into the application.
     * <p>
     * This method iterates over a predefined array of file paths, each corresponding
     * to different data entities such as payments, tenants, hosts, owners,
     * residential properties, commercial properties, and rental agreements.
     * It calls the {@link #loadFromFile(String)} method for each file to read
     * the data and populate the respective collections in the application.
     */
    private void loadDataToList() {
        final String[] filePaths1 = {
                "src/data/Payment.csv",
                "src/data/Tenant.csv",
                "src/data/Host.csv",
                "src/data/Owner.csv",
                "src/data/ResidentialProperty.csv",
                "src/data/CommercialProperty.csv",
                "src/data/RentalAgreement.csv"
        };

        // First Populated from .csv file(s)
        for (String filePath : filePaths1) {
            loadFromFile(filePath);
        }

        final String[] filePaths2 = {
                "src/data/Tenant.csv",
                "src/data/Host.csv",
                "src/data/Owner.csv",
        };

        // Second Populated from .csv file(s)
        for (String filePath : filePaths2) {
            loadFullDataFromFile(filePath);
        }
    }

    public void saveDataToCSVFile() {
        final String[] filePaths = {
                "src/data/Payment.csv",
                "src/data/Tenant.csv",
                "src/data/Host.csv",
                "src/data/Owner.csv",
                "src/data/ResidentialProperty.csv",
                "src/data/CommercialProperty.csv",
                "src/data/RentalAgreement.csv"
        };

        // Save to .csv file(s)
        for (String filePath : filePaths) {
            saveDataToCSV(filePath);
        }
    }

    /**
     * Loads data from a specified CSV file and populates the corresponding lists of
     * tenants, hosts, owners, residential properties, commercial properties,
     * rental agreements, and payments.
     *
     * @param filepath the path to the CSV file to be loaded
     * @throws RuntimeException if there is an error parsing the date or reading the file
     */
    private void loadFromFile(String filepath) {

        String line = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                switch (data[0].substring(0, 2)) {
                    case "TN" -> {
                        Tenant tenant = new Tenant(data[0], data[1], dateFormat.parse(data[2]), data[3]);
                        // Read each the paymentId provide in "Tenant.csv" file
                        String[] pay = data[4].split("-");

                        for (String id : pay) {
                            tenant.addPayment(getPayment(payments, id));
                        }
                        this.tenants.add(tenant);
                    }
                    case "HS" -> {
                        Host host = new Host(data[0], data[1], dateFormat.parse(data[2]), data[3]);
                        this.hosts.add(host);
                    }
                    case "ON" -> {
                        Owner owner = new Owner(data[0], data[1], dateFormat.parse(data[2]), data[3]);
                        this.owners.add(owner);
                    }
                    case "RP" -> {
                        ResidentialProperty property = new ResidentialProperty(data[0], data[1], Double.parseDouble(data[2]), data[3],
                                getOwner(owners, data[4]), Integer.parseInt(data[6]), Boolean.parseBoolean(data[7]), Boolean.parseBoolean(data[8]));

                        // Read each the Host provide in "ResidentialProperty.csv" file
                        String[] owners_list = data[5].split("-");

                        for (String id : owners_list) {
                            property.addHost(getHost(hosts, id)); // Add the Host to an object
                        }

                        this.residentialProperties.add(property);
                    }
                    case "CP" -> {
                        CommercialProperty property = new CommercialProperty(data[0], data[1], Double.parseDouble(data[2]), data[3],
                                getOwner(owners, data[4]), data[6], Integer.parseInt(data[7]), Double.parseDouble(data[8]));

                        // Read each the Host provide in "CommercialProperty.csv" file
                        String[] owner_list = data[5].split("-");

                        for (String id : owner_list) {
                            property.addHost(getHost(hosts, id)); // Add the Host to an object
                        }

                        this.commercialProperties.add(property);
                    }
                    case "RA" -> {
                        // Read each the subTenant provide in "RentalAgreement.csv" file
                        String[] subTenants = data[2].split("-");
                        List<Tenant> sub = new ArrayList<>();

                        for (String subTenant : subTenants) {
                            sub.add(getTenant(tenants, subTenant));
                        }
                        // Get the Property from the id in csv file
                        Property property_from_csv = null;
                        if (data[3].startsWith("CP")) {
                            property_from_csv = (CommercialProperty) getProperty(commercialProperties, residentialProperties, data[3]);
                        } else if (data[3].startsWith("RP")) {
                            property_from_csv = (ResidentialProperty) getProperty(commercialProperties, residentialProperties, data[3]);
                        }

                        RentalAgreement agreement =  new RentalAgreement.Builder(data[0]).mainTenant(getTenant(tenants, data[1])).subTenants(sub)
                                .propertyLeased(property_from_csv).host(getHost(hosts, data[4])).owner(getOwner(owners, data[5])).period(data[6])
                                .contractDate(dateFormat.parse(data[7]))
                                .rentingFee(Double.parseDouble(data[8])).status(data[9]).build();
                        rentalAgreements.add(agreement);
                    }
                    case "PM" -> {
                        Payment each_payment = new Payment(data[0], Double.parseDouble(data[1]), dateFormat.parse(data[2]), data[3]);
                        payments.add(each_payment);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (ParseException | IOException e) {
            System.out.println("Error parsing data or IOException");
        }
    }

    /**
     * Loads full data from a specific CSV file and populates the application
     * data structures with tenants, hosts, and owners based on the information
     * contained in the file.
     *
     * @param filepath the path to the CSV file from which data is to be loaded
     * @throws IOException if an I/O error occurs while reading the file
     */
    private void loadFullDataFromFile(String filepath)  {

        String line = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                switch (data[0].substring(0, 2)) {
                    case "TN" -> {
                        Tenant tenant = getTenant(tenants, data[0]);

                        // Read each the id of agreement and payment provide in "Tenant.csv" file
                        String[] agreement_id_list = data[5].split("-");

                        for (String id : agreement_id_list) {
                            assert tenant != null; // Ensure tenant is not null
                            tenant.addRentalAgreement(getRentalAgreement(rentalAgreements, id)); // Add the RentalAgreements to an object
                        }

                    }
                    case "HS" -> {
                        Host host = getHost(hosts, data[0]);

                       // Read each the id of property, owner, agreement provided in "Tenant.csv" file
                        String[] property_id_list = data[4].split("-");
                        String[] owner_id_list = data[5].split("-");
                        String[] agreement_id_list = data[6].split("-");

                        for (String id : property_id_list) {
                            assert host != null; // Ensure host is not null
                            host.addProperty((Property) getProperty(commercialProperties, residentialProperties, id)); // Add the Property to an object
                        }

                        for (String id : owner_id_list) {
                            assert host != null; // Ensure host is not null
                            host.addOwner(getOwner(owners, id)); // Add the Owner to an object
                        }

                        for (String id : agreement_id_list) {
                            assert host != null; // Ensure host is not null
                            host.addRentalAgreement(getRentalAgreement(rentalAgreements, id)); // Add the RentalAgreements to an object
                        }

                    }
                    case "ON" -> {
                        Owner owner = getOwner(owners, data[0]);

                        // Read each the id of property, owner, agreement provided in "Tenant.csv" file
                        String[] property_id_list = data[4].split("-");
                        String[] host_id_list = data[5].split("-");
                        String[] agreement_id_list = data[6].split("-");

                        for (String id : property_id_list) {
                            assert owner != null; // Ensure owner is not null
                            owner.addProperty((Property) getProperty(commercialProperties, residentialProperties, id)); // Add the Property to an object
                        }

                        for (String id : host_id_list) {
                            assert owner != null; // Ensure owner is not null
                            owner.addHost(getHost(hosts, id)); // Add the Host to an object
                        }

                        for (String id : agreement_id_list) {
                            assert owner != null; // Ensure owner is not null
                            owner.addRentalAgreement(getRentalAgreement(rentalAgreements, id)); // Add the RentalAgreements to an object
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    /**
     * Saves the data of various entities to a CSV file specified by the given filepath.
     * The method writes headers and the corresponding data for the following entities:
     * - Payment
     * - Tenant
     * - Host
     * - Owner
     * - ResidentialProperty
     * - CommercialProperty
     * - RentalAgreement
     *
     * @param filepath the path to the CSV file where the data will be saved
     * @throws IOException if an I/O error occurs while writing to the file
     */
    @Override
    public void saveDataToCSV(String filepath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            switch (filepath) {
                case "src/data/Payment.csv":
                    writer.write("PaymentID,Amount,Date,PaymentMethod");
                    writer.newLine();
                    for (Payment p : payments) {
                        writer.write(p.toCSV());
                        writer.newLine();
                    }
                    break;
                case "src/data/Tenant.csv":
                    writer.write("TenantID,FullName,DateOfBirth,ContactInfo,PaymentsID,RentalAgreementsID");
                    writer.newLine();
                    for (Tenant t : tenants) {
                        writer.write(t.toCSV());
                        writer.newLine();
                    }
                    break;
                case "src/data/Host.csv":
                    writer.write("HostID,FullName,DateOfBirth,ContactInfo,PropertyID,OwnerID,AgreementID");
                    writer.newLine();
                    for (Host h : hosts) {
                        writer.write(h.toCSV());
                        writer.newLine();
                    }
                    break;
                case "src/data/Owner.csv":
                    writer.write("OwnerID,FullName,DateOfBirth,ContactInfo,PropertyID,HostID,AgreementID");
                    writer.newLine();
                    for (Owner o : owners) {
                        writer.write(o.toCSV());
                        writer.newLine();
                    }
                    break;
                case "src/data/ResidentialProperty.csv":
                    writer.write("PropertyID,Address,Pricing,Status,OwnerID,HostIDs,Bedrooms,GardenAvailable,PetFriendly");
                    writer.newLine();
                    for (ResidentialProperty rp : residentialProperties) {
                        writer.write(rp.toCSV());
                        writer.newLine();
                    }
                    break;
                case "src/data/CommercialProperty.csv":
                    writer.write("PropertyID,Address,Pricing,Status,OwnerID,HostIDs,BusinessType,ParkingSpaces,SquareFootage");
                    writer.newLine();
                    for (CommercialProperty cp : commercialProperties) {
                        writer.write(cp.toCSV());
                        writer.newLine();
                    }
                    break;
                case "src/data/RentalAgreement.csv":
                    writer.write("AgreementID,MainTenantID,SubTenantIDs,PropertyID,HostID,OwnerID,Period,ContractDate,RentingFee,Status");
                    writer.newLine();
                    for (RentalAgreement r : rentalAgreements) {
                        writer.write(r.toCSV());
                        writer.newLine();
                    }
                    break;
            }

        } catch (IOException e) {
            System.err.println("Error saving data to CSV: IOException");
        }
    }

    /**
     * Clears all data from the collections of 7 entitie
     * This method effectively resets the state of these collections to be empty.
     */
    @Override
    public void clearData() {
        rentalAgreements.clear();
        tenants.clear();
        hosts.clear();
        owners.clear();
        residentialProperties.clear();
        commercialProperties.clear();
        payments.clear();
    }

    public static Object getProperty(List<CommercialProperty> properties1, List<ResidentialProperty> properties2, String id) {
        if (id.equals("None"))
            return null;

        if (id.startsWith("CP")) {
            for (CommercialProperty property : properties1) {
                if (property.getPropertyID().equals(id)) {
                    return property;
                }
            }
        } else if (id.startsWith("RP")) {
            for (ResidentialProperty property : properties2) {
                if (property.getPropertyID().equals(id)) {
                    return property;
                }
            }
        }
        System.out.println("Property not found");
        return null; // Can not find that Property with the id
    }

    public static Owner getOwner(List<Owner> owners, String id) {
        if (id.equals("None"))
            return null;

        for (Owner owner : owners) {
            if (owner.getId().equals(id)) {
                return owner;
            }
        }
        System.out.println("Can't find Owner with id: " + id);
        return null; // Can not find that Owner with the id
    }

    public static Host getHost(List<Host> hosts, String id) {
        if (id.equals("None"))
            return null;

        for (Host host : hosts) {
            if (host.getId().equals(id)) {
                return host;
            }
        }
        System.out.println("Can't find Host with id: " + id);
        return null; // Can not find that Host with the id
    }

    public static Tenant getTenant(List<Tenant> tenants, String id) {
        if (id.equals("None"))
            return null;

        for (Tenant tenant : tenants) {
            if (tenant.getId().equals(id)) {
                return tenant;
            }
        }
        System.out.println("Can't find Tenant with id: " + id);
        return null; // Can not find that Tenant with the id
    }

    public static Payment getPayment(List<Payment> payments, String id) {
        if (id.equals("None"))
            return null;

        for (Payment payment : payments) {
            if (payment.getPaymentID().equals(id)) {
                return payment;
            }
        }
        System.out.println("Can't find Payment with id: " + id);
        return null; // Can not find that Payment with the id
    }

    public static RentalAgreement getRentalAgreement(List<RentalAgreement> agreements, String id) {
        if (id.equals("None"))
            return null;

        for (RentalAgreement agreement : agreements) {
            if (agreement.getAgreementID().equals(id)) {
                return agreement;
            }
        }
        System.out.println("Can't find RentalAgreement with id: " + id);
        return null; // Can not find that RentalAgreement with the id
    }

    /**
     * Displays all existing Owner's name or Property's address based on the user's choice.
     * If choice 1 is selected, it prints the names of all owners associated with rental agreements,
     * formatted to show six names per line. If choice 2 is selected,
     * it prints the addresses of all properties associated with rental agreements,
     * also formatted to show six addresses per line.
     *
     * @param agreements a list of RentalAgreement objects to be displayed if choice 2 is selected
     * @param num an integer representing the user's choice (1 for owner names, 2 for property addresses from rental agreements)
     */
    public static void viewAllExitsChoice(List<RentalAgreement> agreements, int num) {
        int counter = 1;
        switch (num) {
            case 1:
                System.out.println("\nRental Agreement Owner's name option are shown below: ");
                for (RentalAgreement a : agreements) {
                    if (counter % 6 == 0) {System.out.println();}
                    System.out.print(a.getOwnerName() + ", ");
                    counter++;
                }
                break;
           case 2:
               System.out.println("\nRental Agreement Property's address option are shown below:");
               for (RentalAgreement a : agreements) {
                   if (counter % 6 == 0) {System.out.println();}
                   System.out.print(a.getPropertyAddress() + ", ");
                   counter++;
               }
               break;
        }
        System.out.println();
    }
}
