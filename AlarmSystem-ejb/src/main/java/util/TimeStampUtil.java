package util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimeStampUtil {

    public static Timestamp CURRENT_TIME()
    {
       return Timestamp.valueOf(LocalDateTime.now());
    }
}
