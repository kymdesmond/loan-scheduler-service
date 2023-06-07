package com.desmond.loanschedulerservice.util;

import java.sql.Timestamp;
import java.util.Calendar;

public class Utils {

    public static Timestamp AddSecondsToTimestamp(Timestamp timestamp, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.SECOND, sec);
        return new Timestamp(cal.getTime().getTime());
    }

    public static Timestamp SubtractSecondsToTimestamp(Timestamp timestamp, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.SECOND, -sec);
        return new Timestamp(cal.getTime().getTime());
    }
}
