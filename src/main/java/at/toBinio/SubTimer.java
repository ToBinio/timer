package at.toBinio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created: 23.06.2022
 *
 * @author Tobias Frischmann
 */

public class SubTimer {
    private long startNano = -1;
    private long time;

    private final String name;
    private SubTimer activeSubTimer;

    private List<SubTimer> subTimers;

    public SubTimer(String name) {
        this.name = name;
        subTimers = new ArrayList<>();

        start();
    }

    public void start() {
        if (startNano != -1) throw new TimerException(String.format("%s is already started", getTimerPrefix()));

        startNano = System.nanoTime();
    }

    public void startSubTimer(String name, int depth) {
        if (activeSubTimer == null) {
            activeSubTimer = new SubTimer(name);

            System.out.println(" ".repeat(depth * 2) + activeSubTimer.name + " began");

        } else {
            activeSubTimer.startSubTimer(name, depth + 1);
        }
    }

    public void stop() {
        if (startNano == -1) throw new TimerException(String.format("%s was never started", getTimerPrefix()));

        time = (System.nanoTime() - startNano);
        startNano = -1;

        if (activeSubTimer != null) {
            throw new RuntimeException("subTimer-" + activeSubTimer.name + " is still running");
        }
    }

    public void stopSubTimer(int depth) {
        if (activeSubTimer == null) {
            throw new RuntimeException("no SubTimer was started");
        } else if (activeSubTimer.activeSubTimer == null) {

            activeSubTimer.stop();

            System.out.println(" ".repeat(depth * 2) + activeSubTimer.name + " ended");

            subTimers.add(activeSubTimer);
            activeSubTimer = null;

        } else {
            activeSubTimer.stopSubTimer(depth + 1);
        }
    }

    private String getTimerPrefix() {
        return "subTimer-" + name;
    }

    public void print(int depth) {
        System.out.println(" ".repeat(depth * 2) + name + " - " + Timer.nanoToString(time));
        for (SubTimer subTimer : subTimers) {
            subTimer.print(depth + 1);
        }
    }
}
