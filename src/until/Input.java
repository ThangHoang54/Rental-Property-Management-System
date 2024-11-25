package until;
import java.util.Scanner;

/**
 *  @author <Hoàng Minh Thắng - S3999925>
 */

/* Skeleton pattern */
public class Input {
    private static Input input;
    private Scanner sc;

    private Input() {
        sc = new Scanner(System.in);
    }

    public static Input getDataInput() {
        if (input == null) {
            input = new Input();
        }
        return input;
    }

    public Scanner getScanner() {
        return sc;
    }
}
