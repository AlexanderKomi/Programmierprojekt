package de.hsh.alexander.util;

import java.io.File;
import java.net.URL;

public class Path {

    public static void test() {
        Logger.log( getFileNames( getExecutionLocation() ) );
    }

    public static String[] getFileNames( String path ) {
        File   file  = new File( path );
        File[] files = file.listFiles();
        assert files != null;
        String[] result = new String[ files.length ];
        for ( int i = 0 ; i < result.length ; i++ ) {
            result[ i ] = files[ i ].getName();
        }
        return result;
    }

    public static String getExecutionLocation() {
        return getClassLocationAsURL().getPath();
    }

    public static URL getClassLocationAsURL() {
        return Path.class.getProtectionDomain().getCodeSource().getLocation();
    }

    public static String[] getFileNames( URL url ) {
        return getFileNames( getPath( url ) );
    }

    public static String getPath( URL url ) {return url.getPath();}
}
