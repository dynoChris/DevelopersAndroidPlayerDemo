package com.oliverstudio.developersandroidplayer.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatUtils {

    public static String modifyDate(String incomeDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date d = sdf.parse(incomeDate);
            SimpleDateFormat sdfOut = new SimpleDateFormat("dd LLL yyyy, K:mm");
            String dateAsString = sdfOut.format(d);
            return dateAsString;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return "";
    }

//    private static String capitalizeFirstLetter(String string) {
//        return string.substring(0, 1).toUpperCase() + string.substring(1);
//    }
}
