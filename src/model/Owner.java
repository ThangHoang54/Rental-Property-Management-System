package model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class Owner extends Person{
    private List<Property> propertiesOwned; // store properties managed by the host
    private List<Host> hostsManagingProperties; // keep track of owners that the host cooperates with
    private List<RentalAgreement> rentalAgreements; // keep track of rental agreements associated with the host

    // Constructor
    public Owner(String id, String name, Date birthDate, String info_contact) {
        super(id, name, birthDate, info_contact);
        propertiesOwned = new ArrayList<>();
        hostsManagingProperties = new ArrayList<>();
        rentalAgreements = new ArrayList<>();
    }

    // Getter
    public List<RentalAgreement> getRentalAgreements() {return rentalAgreements;}
    public List<Host> getHostsManagingProperties() {return hostsManagingProperties;}
    public List<Property> getPropertiesOwned() {return propertiesOwned;}

    public void addProperty(Property property) {
        propertiesOwned.add(property);
    }
    public void removeProperty(Property property) {
        propertiesOwned.remove(property);
    }

    public void addHost(Host host) {
        hostsManagingProperties.add(host);
    }
    public void removeHost(Host host) {
        hostsManagingProperties.remove(host);
    }

    public void addRentalAgreement(RentalAgreement rentalAgreement) {
        rentalAgreements.add(rentalAgreement);
    }
    public void removeRentalAgreement(RentalAgreement rentalAgreement) {
        rentalAgreements.remove(rentalAgreement);
    }


    @Override
    public String toString() {
        // Convert the propertiesOwned list into a stream
        String propertyIDs = propertiesOwned.stream()
                // Map each Property object to its propertyID using the getPropertyID method
                .map(Property::getPropertyID)
                // Reduce the stream by concatenating the propertyIDs with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None"); // If the stream is empty, return "None" as the default value


        // Convert the hostsManagingProperties list into a stream
        String hostIDs = hostsManagingProperties.stream()
                // Map each Host object to its HostID using the getId method
                .map(Host::getId)
                // Reduce the stream by concatenating the hostIDs with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None"); // If the stream is empty, return "None" as the default value

        // Convert the rentalAgreements list into a stream
        String rentalAgreementIDs = rentalAgreements.stream()
                // Map each RentalAgreement object to its AgreementID using the getAgreementID method
                .map(RentalAgreement::getAgreementID)
                // Reduce the stream by concatenating the AgreementID with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None"); // If the stream is empty, return "None" as the default value

        return super.toString() + String.format("| %-15s | %-15s | %-20s", propertyIDs, hostIDs, rentalAgreementIDs);
    }

    public String toCSV() {
        // Format the birthdate
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
        String formattedBirthDate = formatDate.format(super.getDateOfBirth());

        // Convert the propertiesOwned list into a stream
        String propertyIDs = propertiesOwned.stream()
                // Map each Property object to its propertyID using the getPropertyID method
                .map(Property::getPropertyID)
                // Reduce the stream by concatenating the propertyIDs with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None"); // If the stream is empty, return "None" as the default value


        // Convert the hostsManagingProperties list into a stream
        String hostIDs = hostsManagingProperties.stream()
                // Map each Host object to its HostID using the getId method
                .map(Host::getId)
                // Reduce the stream by concatenating the hostIDs with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None"); // If the stream is empty, return "None" as the default value

        // Convert the rentalAgreements list into a stream
        String rentalAgreementIDs = rentalAgreements.stream()
                // Map each RentalAgreement object to its AgreementID using the getAgreementID method
                .map(RentalAgreement::getAgreementID)
                // Reduce the stream by concatenating the AgreementID with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None"); // If the stream is empty, return "None" as the default value


        // Return a CSV formatted string
        return super.getId() + "," +
                super.getName() + "," +
                formattedBirthDate + "," +
                super.getInfo_contact() + "," +
                propertyIDs + "," +
                hostIDs + "," +
                rentalAgreementIDs;
    }
}
