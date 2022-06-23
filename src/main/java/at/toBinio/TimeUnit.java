package at.toBinio;

/**
 * Created: 22.06.2022
 *
 * @author Tobias Frischmann
 */

public enum TimeUnit {
    NANO_SECOND("ns", 1000), MICRO_SECOND("µs", 1000), MILLI_SECOND("ms", 1000), SECOND("s", 1000), MINUTE("m", 60), HOUR("h", 60);

    public final String shortName;
    public final int multiplayerToLover;

    TimeUnit(String shortName, int multiplayerToLover) {
        this.shortName = shortName;
        this.multiplayerToLover = multiplayerToLover;
    }
}
