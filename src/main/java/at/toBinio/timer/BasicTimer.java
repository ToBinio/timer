package at.toBinio.timer;

import at.toBinio.NamedTimer;
import at.toBinio.TimerException;

/**
 * Created: 22.06.2022
 *
 * @author Tobias Frischmann
 */

public class BasicTimer extends NamedTimer {
    private SubTimer total;

    public BasicTimer(String name, boolean start) {
        super(name);
        if (start)
            start();
    }

    public BasicTimer(String name) {
        this(name, true);
    }

    public void start() {

        if (total != null) throw new TimerException(String.format("%s is already started", getTimerPrefix()));

        total = new SubTimer("total");

        System.out.printf("%s began%n", getTimerPrefix());
    }

    public void startSubTimer(String name) {
        if (total == null) throw new TimerException(String.format("%s was never started", getTimerPrefix()));

        total.startSubTimer(name, 1);
    }

    public void beginSubTimer(String name) {
        startSubTimer(name);
    }

    public void stop() {
        if (total == null) throw new TimerException(String.format("%s was never started", getTimerPrefix()));

        total.stop();

        System.out.printf("%s ended%n", getTimerPrefix());
        total.print(1);

        total = null;
    }

    public void stopSubTimer() {
        if (total == null) throw new TimerException(String.format("%s was never started", getTimerPrefix()));

        total.stopSubTimer(1);
    }

    public void endSubTimer() {
        stopSubTimer();
    }
}
