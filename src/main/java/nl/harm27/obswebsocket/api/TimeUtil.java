package nl.harm27.obswebsocket.api;

import java.time.Duration;
import java.util.Optional;
import java.util.regex.Pattern;

public class TimeUtil {
    private static final Pattern NUMERIC_FORMAT_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    private TimeUtil() {
    }

    public static Optional<Duration> parseDuration(String timecode) {
        String[] arrayPart1 = timecode.split(":");
        if (arrayPart1.length != 3)
            return Optional.empty();
        String[] arrayPart2 = arrayPart1[2].split(Pattern.quote("."));
        if (arrayPart2.length != 2)
            return Optional.empty();

        String hours = arrayPart1[0];
        if (isNotNumeric(hours))
            return Optional.empty();

        String minutes = arrayPart1[1];
        if (isNotNumeric(minutes))
            return Optional.empty();

        String seconds = arrayPart2[0];
        if (isNotNumeric(seconds))
            return Optional.empty();

        String millis = arrayPart2[1];
        if (isNotNumeric(millis))
            return Optional.empty();

        return Optional.ofNullable(Duration.ofHours(Long.parseLong(hours))
                .plusMinutes(Long.parseLong(minutes))
                .plusSeconds(Long.parseLong(seconds))
                .plusMillis(Long.parseLong(millis)));
    }

    private static boolean isNotNumeric(String numberString) {
        return !NUMERIC_FORMAT_PATTERN.matcher(numberString).matches();
    }
}
