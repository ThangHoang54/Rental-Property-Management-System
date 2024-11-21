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
    private static int count = 0;

    // Constructor
    public Host(String id, String name, Date birthDate, String info_contact) {
        super(id, name, birthDate, info_contact);
        propertiesManaged = new ArrayList<>();
        cooperatingOwners = new ArrayList<>();
        rentalAgreements = new ArrayList<>();
        count = Integer.parseInt(id.substring(2));
    }
    public Host(String name, Date dateOfBirth, String info_contact) {
        super("HS" + (++count < 10 ? "00" : "0") + count, name, dateOfBirth, info_contact);
        propertiesManaged = new ArrayList<Property>();
        cooperatingOwners = new ArrayList<Owner>();
        rentalAgreements = new ArrayList<RentalAgreement>();
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
        return super.toString();
    }

    public String toCSV() {
        // Format the birthdate
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
        String formattedBirthDate = formatDate.format(super.getDateOfBirth());

        // Prepare lists of IDs for properties managed, owners, and rental agreements
        StringBuilder propertyIDs = new StringBuilder();
        for (Property property : propertiesManaged) {
            propertyIDs.append(property.getPropertyID()).append("-");
        }

        StringBuilder ownerIDs = new StringBuilder();
        for (Owner owner : cooperatingOwners) {
            ownerIDs.append(owner.getId()).append("-");
        }

        StringBuilder rentalAgreementIDs = new StringBuilder();
        for (RentalAgreement rentalAgreement : rentalAgreements) {
            rentalAgreementIDs.append(rentalAgreement.getAgreementID()).append("-");
        }

        // Remove trailing semicolons
        if (!propertyIDs.isEmpty()) {
            propertyIDs.setLength(propertyIDs.length() - 1);
        } if (!ownerIDs.isEmpty()) {
            ownerIDs.setLength(ownerIDs.length() - 1);
        } if (!rentalAgreementIDs.isEmpty()) {
            rentalAgreementIDs.setLength(rentalAgreementIDs.length() - 1);
        }

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
