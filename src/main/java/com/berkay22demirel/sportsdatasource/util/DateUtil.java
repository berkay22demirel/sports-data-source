package com.berkay22demirel.sportsdatasource.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String TFF_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm";

    public static Date convertToDate(String dateStr, String pattern) {
        Date date = null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
}
