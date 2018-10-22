package de.hsh.alexander.util;

/**
 * General configuration settings, which should be applied to every program, launched.
 * Be careful, when changing settings.
 *
 * @author Alexander Komischke
 */
public class Configuration {

    private static boolean shouldLog = true;

    /**
     * Returns if everything should be logged.
     *
     * @return Returns true, when everything should be logged to the console.
     */
    public static boolean shouldLog() {
        return shouldLog;
    }
}
