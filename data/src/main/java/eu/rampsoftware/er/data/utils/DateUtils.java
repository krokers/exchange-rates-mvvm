package eu.rampsoftware.er.data.utils;

/**
 * Created by Ramps on 2017-02-18.
 */

public class DateUtils {

    private DateUtils(){
        //no instances

    }

    private static final long DAY_MILLIS = 24 * 3600 * 1000;

    public static long midnight(final long timestamp) {
        return (timestamp / DAY_MILLIS) * DAY_MILLIS;
    }
}
