package at.toBinio;

/**
 * Created: 25.06.2022
 *
 * @author Tobias Frischmann
 */

public abstract class NamedTimer {
    protected final String name;

    public NamedTimer(String name) {
        this.name = name;
    }

    abstract public void start();

    public void begin() {
        start();
    }

    abstract public void stop();

    public void end() {
        stop();
    }

    protected String getTimerPrefix() {
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
