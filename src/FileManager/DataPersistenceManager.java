package FileManager;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public interface DataPersistenceManager {
    /*
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
                        String[] agreement_id_list = data[4].split("-");
                        String[] payment_id_list = data[5].split("-");

                        for (String id : agreement_id_list) {
                            assert tenant != null; // Ensure tenant is not null
                            tenant.addRentalAgreement(getRentalAgreement(rentalAgreements, id)); // Add the RentalAgreements to an object
                        }

                        for (String id : payment_id_list) {
                            assert tenant != null; // Ensure tenant is not null
                            tenant.addPayment(getPayment(payments, id)); // Add the Payment to an object
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
                            host.addProperty(getProperty(commercialProperties, residentialProperties, id)); // Add the Property to an object
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
                            owner.addProperty(getProperty(commercialProperties, residentialProperties, id)); // Add the Property to an object
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
    */
    void saveDataToCSV(String filepath);

    //void saveDataToCSV(String fileName);
    void clearData();
}
