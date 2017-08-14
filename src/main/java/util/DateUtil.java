package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by huwei on 2017/8/14.
 */
public class DateUtil {
    private final static String RFC1123_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";
    private final static String PATTERN = "yyyy-MM-dd HH:mm:ss:SSS";

    private final static DateFormat rfc1123DateFormat = new SimpleDateFormat(RFC1123_PATTERN, Locale.ENGLISH);
    private final static DateFormat dateFormat = new SimpleDateFormat(PATTERN);

    static{
        rfc1123DateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public static String currentRFC1123Date(){
        return rfc1123DateFormat.format(new Date());
    }

    public static String currentDate(){
        return dateFormat.format(new Date());
    }

}
