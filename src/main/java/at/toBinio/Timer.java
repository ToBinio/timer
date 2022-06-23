package at.toBinio;

/**
 * Created: 22.06.2022
 *
 * @author Tobias Frischmann
 */

public class Timer {
    private long startNano = -1;
    private final String name;

    public Timer(String name, boolean start) {
        this.name = name;
        if (start)
            start();
    }

    public Timer(String name) {
        this(name, false);
    }

    public void start() {

        if (startNano != -1) throw new TimerException(String.format("%s is already started", getTimerPrefix()));

        startNano = System.nanoTime();

        System.out.printf("%s started%n", getTimerPrefix());
    }

    public void stop() {
        if (startNano == -1) throw new TimerException(String.format("%s was never started", getTimerPrefix()));

        long nano = (System.nanoTime() - startNano);
        startNano = -1;

        System.out.printf("%s stopped%n total: %s%n", getTimerPrefix(), nanoToString(nano));
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
