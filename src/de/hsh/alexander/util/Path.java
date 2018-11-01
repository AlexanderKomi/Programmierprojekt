package de.hsh.alexander.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class can be used as a tool for any operations regarding the path of files.
 *
 * @author Alexander Komischke
 */
public class Path {

    public static void main( String[] args ) {
        //standAlone( args );

        Logger.log( "Dir : " + getExecutionLocation(), Path.getAllFileNames() );
    }

    public static String[] getAllFileNames() {
        return getAllFileNames( getExecutionLocation() );
    }

    private static void test() {
    }

    /**
     * Lists all files and files in dirs and so on... from a given path.
     * Its implemented by using recursion, so be careful.
     *
     * @author Alexander Komischke
     */
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
        return res.toArray( new String[ 0 ] );
    }

    public static void standAlone( String[] args ) {
        ArrayList<String> argsList = new ArrayList<>( Arrays.asList( args ) );
        if ( argsList.size() > 0 ) {
            boolean all = false;
            if ( argsList.remove( "--all" ) || argsList.remove( "-a" ) ) {
                all = true;
            }

            for ( String a : argsList ) {
                String temp = getExecutionLocation() + a + "/";
                if ( all ) {
                    Logger.log( "All Files from : " + temp, "\t", getAllFileNames( temp ), "" );
                }
                else {
                    Logger.log( "Files from : " + temp, "\t", getFileNames( temp ), "" );
                }
            }
        }
        else {
            Logger.log( Path.getAllFileNames( getExecutionLocation() ) );
        }
    }

    /**
     * Concats an amount of tabs together.
     *
     * @param amount
     *         the amount of tabs needed.
     *
     * @return Amount times tabs concatenated together.
     */
    private static String addTabs( int amount ) {
        StringBuilder s = new StringBuilder();
        for ( int i = 0 ; i < amount ; i++ ) {
            s.append( "\t" );
        }
        return s.toString();
    }

    public static URL getClassLocation( Class c ) {
        return c.getProtectionDomain().getCodeSource().getLocation();
    }

    /**
     * Prints all file names in the given path.
     *
     * @param url
     *         Url to the directory
     *
     * @return A String Array, each index represents a file.
     *
     * @author Alexander Komischke
     */
    public static String[] getFileNames( URL url ) {
        return getFileNames( url.getPath() );
    }

    /**
     * Prints all file names in the given path.
     *
     * @param path
     *         Path of the directory
     *
     * @return A String Array, each index represents a file.
     *
     * @author Alexander Komischke
     */
    public static String[] getFileNames( String path ) {
        if ( !(path).endsWith( "/" ) ) {
            path += "/";
        }
        File file = new File( path );
        if ( !file.exists() ) { // Path does not exist.
            //Logger.log( "File does not exists : " + path );
            return new String[] { "" };
        }
        File[] files = file.listFiles();
        if ( files == null ) { // file-path does not contain any files.
            return new String[] { "" };
        }
        String[] result = new String[ files.length ];
        for ( int i = 0 ; i < result.length ; i++ ) {
            result[ i ] = files[ i ].getName();
        }
        return result;
    }

    public static String listFiles() {
        return listFiles( getExecutionLocation() );
    }

    public static String listFiles( String directory ) {
        return Logger.log( "Files from : " + directory, "\t", getAllFileNames( directory ), "" );
    }

    /**
     * Gets the execution location of the main program.
     *
     * @return a String representation.
     *
     * @author Alexander Komischke
     */
    public static String getExecutionLocation() {
        return getClassLocation( Path.class ).getPath();
    }
}
