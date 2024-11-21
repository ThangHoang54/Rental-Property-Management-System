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
    private static int count = 0;

    // Constructor
    public Owner(String id, String name, Date birthDate, String info_contact) {
        super(id, name, birthDate, info_contact);
        propertiesOwned = new ArrayList<>();
        hostsManagingProperties = new ArrayList<>();
        rentalAgreements = new ArrayList<>();
        count = Integer.parseInt(id.substring(2));
    }
    public Owner(String name, Date dateOfBirth, String info_contact) {
        super("ON" + (++count < 10 ? "00" : "0") + count, name, dateOfBirth, info_contact);
        propertiesOwned = new ArrayList<Property>();
        hostsManagingProperties = new ArrayList<Host>();
        rentalAgreements = new ArrayList<RentalAgreement>();
    }

    public void addProperty(Property property) {
        propertiesOwned.add(property);
    }

    public void addHost(Host host) {
        hostsManagingProperties.add(host);
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

        // Prepare lists of IDs for properties owned, hosts, and rental agreements
        StringBuilder propertyIDs = new StringBuilder();
        for (Property property : propertiesOwned) {
            propertyIDs.append(property.getPropertyID()).append("-");
        }

        StringBuilder hostIDs = new StringBuilder();
        for (Host host : hostsManagingProperties) {
            hostIDs.append(host.getId()).append("-");
        }

        StringBuilder rentalAgreementIDs = new StringBuilder();
        for (RentalAgreement rentalAgreement : rentalAgreements) {
            rentalAgreementIDs.append(rentalAgreement.getAgreementID()).append("-");
        }

        // Remove trailing semicolons
        if (!propertyIDs.isEmpty()) {
            propertyIDs.setLength(propertyIDs.length() - 1);
        } if (!hostIDs.isEmpty()) {
            hostIDs.setLength(hostIDs.length() - 1);
        } if (!rentalAgreementIDs.isEmpty()) {
            rentalAgreementIDs.setLength(rentalAgreementIDs.length() - 1);
        }

        // Return a CSV formatted string
        return super.getId() + "," +
                super.getName() + "," +
                formattedBirthDate + "," +
                super.getInfo_contact() + "," +
                propertyIDs.toString() + "," +
                hostIDs.toString() + "," +
                rentalAgreementIDs.toString();
    }
}
