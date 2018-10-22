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

    private static final String separator       = "\t : \t";
    private static final String topic_separator = " ------------------------- ";

    public static String log( Object o ) {
        return log( o.toString() );
    }

    public static String log( String message ) {
        return log( message, separator );
    }

    public static String log( String message, String separator ) {
        String new_message = "";
        if ( Configuration.shouldLog() ) {
            Calendar         calendar         = new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyy.mm.dd HH:mm:ss" );
            new_message = simpleDateFormat.format( calendar.getTime() ) + separator + message;
            System.out.println( new_message );
        }
        return new_message;
    }

    public static String log( Object o, Object argument ) {
        return log( o.toString() + separator + argument.toString() );
    }

    public static String log( String prefix, String message, String suffix ) {
        return log( prefix + message + suffix, separator );
    }

    public static String log( String[] messages ) {
        return log( "", messages, "" );
    }

    public static String log( String prefix, String[] messages, String suffix ) {
        if ( messages == null ) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for ( String message : messages ) {
            s.append( log( prefix + message + suffix ) );
        }
        return s.toString();
    }

    public static String log( String topic, String[] messages ) {
        return log( topic, "", messages, "" );
    }

    public static String log( String topic, String prefix, String[] messages, String suffix ) {
        String s = topic_separator + topic + topic_separator;
        return log( "Start : " + s ) + "\n" +
               log( prefix, messages, suffix ) + "\n" +
               log( "End : " + s );
    }

    public static String log( String topic, String prefix, String[] messages ) {
        return log( topic, prefix, messages, "" );
    }

}
