package FileManager;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

public interface DataPersistenceManager {

    void saveDataToCSVFile();
    void saveDataToCSV(String filepath);
    void clearData();
}
