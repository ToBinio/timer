package at.toBinio.avgTimer;

import at.toBinio.NamedTimer;
import at.toBinio.TimerException;

/**
 * Created: 25.06.2022
 *
 * @author Tobias Frischmann
 */

public class AvgTimer extends NamedTimer {
    private long avgTime;
    private int count;

    private long startTime = -1;

    public AvgTimer(String name) {
        super(name);
    }

    @Override
    public void start() {
        if (startTime != -1) throw new TimerException(String.format("%s is already started", getTimerPrefix()));

        startTime = System.nanoTime();
    }

    @Override
    public void stop() {
        if (startTime == -1) throw new TimerException(String.format("%s was never started", getTimerPrefix()));

        avgTime = (avgTime * count + (System.nanoTime() - startTime)) / (count + 1);
        count++;

        startTime = -1;

        System.out.println(getTimerPrefix() + " - " + nanoToString(avgTime));
    }
}
