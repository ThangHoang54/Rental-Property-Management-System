package model;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public class Host extends Person {
    private List<Property> propertiesManaged;
    private List<Owner> cooperatingOwners;
    private List<RentalAgreement> rentalAgreements;

    // Constructor
    public Host(String id, String name, Date birthDate, String info_contact) {
        super(id, name, birthDate, info_contact);
        propertiesManaged = new ArrayList<>();
        cooperatingOwners = new ArrayList<>();
        rentalAgreements = new ArrayList<>();
    }

    // Getter
    public List<RentalAgreement> getRentalAgreements() {
        return rentalAgreements;
    }

    public void addProperty(Property property) {
        propertiesManaged.add(property);
    }

    public void addOwner(Owner owner) {
        cooperatingOwners.add(owner);
    }

    public void addRentalAgreement(RentalAgreement rentalAgreement) {
        rentalAgreements.add(rentalAgreement);
    }

    @Override
    public String toString() {
        // Convert the propertiesManaged list into a stream
        String propertyIDs = propertiesManaged.stream()
                // Map each Property object to its propertyID using the getPropertyID method
                .map(Property::getPropertyID)
                // Reduce the stream by concatenating the propertyIDs with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None"); // If the stream is empty, return "None" as the default value


        // Convert the cooperatingOwners list into a stream
        String ownerIDs = cooperatingOwners.stream()
                // Map each Owner object to its OwnerID using the getId method
                .map(Owner::getId)
                // Reduce the stream by concatenating the ownerIDs with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None"); // If the stream is empty, return "None" as the default value

        // Convert the rentalAgreements list into a stream
        String rentalAgreementIDs = rentalAgreements.stream()
                // Map each RentalAgreement object to its AgreementID using the getAgreementID method
                .map(RentalAgreement::getAgreementID)
                // Reduce the stream by concatenating the AgreementID with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None"); // If the stream is empty, return "None" as the default value

        return super.toString() + String.format("| %-15s | %-15s | %-20s", propertyIDs, ownerIDs, rentalAgreementIDs);
    }

    public String toCSV() {
        // Format the birthdate
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
        String formattedBirthDate = formatDate.format(super.getDateOfBirth());

        // Convert the propertiesManaged list into a stream
        String propertyIDs = propertiesManaged.stream()
                // Map each Property object to its propertyID using the getPropertyID method
                .map(Property::getPropertyID)
                // Reduce the stream by concatenating the propertyIDs with a hyphen ("-") as the separator
                .reduce((name1, name2) -> name1 + "-" + name2)
                .orElse("None"); // If the stream is empty, return "None" as the default value


        // Convert the cooperatingOwners list into a stream
        String ownerIDs = cooperatingOwners.stream()
                // Map each Owner object to its OwnerID using the getId method
                .map(Owner::getId)
                // Reduce the stream by concatenating the ownerIDs with a hyphen ("-") as the separator
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
                ownerIDs + "," +
                rentalAgreementIDs;
    }
}
