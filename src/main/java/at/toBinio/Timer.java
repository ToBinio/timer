package at.toBinio;

/**
 * Created: 22.06.2022
 *
 * @author Tobias Frischmann
 */

public class Timer {
    private final String name;
    private SubTimer total;

    public Timer(String name, boolean start) {
        this.name = name;
        if (start)
            start();
    }

    public Timer(String name) {
        this(name, false);
    }

    public void start() {

        if (total != null) throw new TimerException(String.format("%s is already started", getTimerPrefix()));

        total = new SubTimer("total");

        System.out.printf("%s began%n", getTimerPrefix());
    }

    public void begin() {
        start();
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

    public void end() {
        stop();
    }

    public void stopSubTimer() {
        if (total == null) throw new TimerException(String.format("%s was never started", getTimerPrefix()));

        total.stopSubTimer(1);
    }

    public void endSubTimer() {
        stopSubTimer();
    }

    private String getTimerPrefix() {
        return "Timer-" + name;
    }

    public static String nanoToString(long timeDif) {
        double first = timeDif;
        TimeUnit firstTimeUnit = TimeUnit.NANO_SECOND;

        double second = 0;
        TimeUnit secondTimeUnit = TimeUnit.NANO_SECOND;

        for (int i = 1; i < TimeUnit.values().length; i++) {
            TimeUnit value = TimeUnit.values()[i];

            if (first / value.multiplayerToLover < 1) break;

            second = first % value.multiplayerToLover;
            secondTimeUnit = firstTimeUnit;

            first = first / value.multiplayerToLover;
            firstTimeUnit = value;
        }

        return String.format("%.0f%s %.0f%s", first, firstTimeUnit.shortName, second, secondTimeUnit.shortName);
    }
}
