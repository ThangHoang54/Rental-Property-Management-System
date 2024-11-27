package RentalAgreementManager;
/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public interface RentalManager {
    void addRentalAgreement();
    void updateRentalAgreement(String id, int num);
    void deleteRentalAgreement(String id);
    void viewAllRentalAgreements();
    void viewRentalAgreementsByOwnerName(String ownerName);
    void viewRentalAgreementsByPropertyAddress(String address);
    void viewRentalAgreementsByStatus(String status);
}
