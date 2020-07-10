package common.util;

import java.net.URL;

/**
 * This class can be used as a tool for any operations regarding the path of files.
 *
 * @author Alexander Komischke
 */
public final class Path {

    public static URL getClassLocation( Class c ) {
        return c.getProtectionDomain().getCodeSource().getLocation();
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
