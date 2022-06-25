package at.toBinio;

import at.toBinio.avgTimer.AvgTimer;

/**
 * Created: ${DATE}
 *
 * @author Tobias Frischmann
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {

        AvgTimer cool = new AvgTimer("cool");

        for (int i = 0; i < 100; i++) {
            cool.start();
            Thread.sleep(200);
            cool.stop();
        }
    }
}