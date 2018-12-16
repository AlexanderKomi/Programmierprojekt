package common.util;

/**
 * General configuration settings, which should be applied to every program, launched.
 * Be careful, when changing settings.
 *
 * @author Alexander Komischke
 */
public final class Configuration {

    /**
     * Returns if everything should be logged.
     *
     * @return Returns true, when everything should be logged to the console.
     */
    static boolean shouldLog() {
        return true;
    }
}
