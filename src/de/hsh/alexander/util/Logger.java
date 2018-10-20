package de.hsh.alexander.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Logs information on the console.
 *
 * @author Alexander Komischke
 */
public class Logger {

    private static final String separator = "\t : \t";

    public static String log( Object o, Object argument ) {
        return log( o.toString() + separator + argument.toString() );
    }

    public static String log( String message ) {
        if ( Configuration.shouldLog() ) {
            Calendar         calendar         = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyy.mm.dd HH:mm:ss" );
            String           new_message      = simpleDateFormat.format( calendar.getTime() ) + separator + message;
            System.out.println( new_message );
            return new_message;
        }
        return "";
    }

}
