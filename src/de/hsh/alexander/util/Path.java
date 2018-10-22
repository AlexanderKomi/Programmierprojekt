package de.hsh.alexander.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Path {

    public static void test() {
        String execLoc = getExecutionLocation();
        Logger.log( "Files from : " + execLoc, "\t", getAllFileNames( execLoc ), "" );
    }

    public static String[] getFileNames( String path ) {
        File   file  = new File( path );
        File[] files = file.listFiles();
        if ( files != null ) {
            String[] result = new String[ files.length ];
            for ( int i = 0 ; i < result.length ; i++ ) {
                result[ i ] = files[ i ].getName();
            }
            return result;
        }
        return new String[] { "" };
    }

    public static String[] getAllFileNames( String path ) {
        String[] s = getFileNames( path );
        if ( s == null ) {
            return new String[] { "" };
        }
        ArrayList<String> res     = new ArrayList<>();
        int               counter = 0;

        for ( String fileName : s ) {
            if ( !fileName.isEmpty() ) {
                res.add( addTabs( counter ) + fileName );
                String new_path = path + fileName;
                if ( !(path + fileName).endsWith( "/" ) ) {
                    new_path += "/";
                }
                String[] temp = getAllFileNames( new_path );
                counter++;
                for ( String t : temp ) {
                    if ( !t.isEmpty() ) {
                        res.add( addTabs( counter ) + t );
                    }
                }
                counter--;
            }

        }

        return res.toArray( new String[ res.size() ] );
    }

    private static String addTabs( int amount ) {
        StringBuilder s = new StringBuilder();
        for ( int i = 0 ; i < amount ; i++ ) {
            s.append( "\t" );
        }
        return s.toString();
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
