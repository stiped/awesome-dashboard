package awesome.dashboard.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonUtils {

    public static LocalDateTime fromZuluToLocalDateTime(String zuluFormat) {
        if (zuluFormat == null) {
            return null;
        }
        DateTimeFormatter zuluTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime parsedDate = LocalDateTime.parse(zuluFormat, zuluTimeFormat);
        return parsedDate;
    }
}
