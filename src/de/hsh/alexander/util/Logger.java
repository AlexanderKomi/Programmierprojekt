package de.hsh.alexander.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Logger {

    private static boolean shouldLog = true;

    public static String log( String message ) {
        if ( isUsingDebugMode() ) {
            Calendar         c                = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyy.mm.dd HH:mm:ss" );
            String           new_message      = simpleDateFormat.format( c.getTime() ) + "\t\t" + message;
            System.out.println( new_message );
            return new_message;
        }
        return "";
    }

    public static boolean isUsingDebugMode() {
        return Logger.shouldLog;
    }

}
