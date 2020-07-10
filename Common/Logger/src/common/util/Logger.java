package common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Logs information on the console.
 *
 * @author Alexander Komischke
 */
public final class Logger {

    private static final boolean shouldLog = true;
    private static final String separator       = "\t : \t";

    private Logger() {}

    private static String format(String message) {
        if (!shouldLog) {
            return "";
        }
        Calendar         calendar         = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy.MM.dd HH:mm:ss");
        return simpleDateFormat.format( calendar.getTime() ) + separator + message;
    }

    public static String log( Object o ) {
        return log( o.toString() );
    }

    public static String log( Object o, Object argument ) {
        return log( o.toString() + separator + argument.toString() );
    }

    public static String log( final String message) {
        final String m = format(message);
        if (shouldLog) {
            System.out.println(m);
        }
        return m;
    }

    public static String log( String[] messages ) {
        return logStringArray("", messages, "" );
    }

    public static String logStringArray(String prefix, String[] messages, String suffix ) {
        if ( messages == null ) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for ( String message : messages ) {
            s.append( log( prefix + message + suffix) );
        }
        return s.toString();
    }
}
