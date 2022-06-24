package at.toBinio;

/**
 * Created: ${DATE}
 *
 * @author Tobias Frischmann
 */

public class Main {
    public static void main(String[] args) {

        Timer test = new Timer("test", true);

        test.startSubTimer("1.0");
        test.startSubTimer("2.0");
        test.stopSubTimer();
        test.startSubTimer("2.1");
        test.startSubTimer("3");
        test.stopSubTimer();
        test.stopSubTimer();
        test.stopSubTimer();
        test.startSubTimer("1.1");
        test.stopSubTimer();
        test.stop();
    }
}