package de.hsh.alexander.util;

import java.net.URL;

public class Path {

    public static String getMainLocation() {
        return getClassLocationAsURL().getPath();
    }

    public static URL getClassLocationAsURL() {
        return Path.class.getProtectionDomain().getCodeSource().getLocation();
    }
}
