package common.config;

public class Authors {

    private static final String seperator = " : ";

    public static final String[] authorNames = new String[] {
            "Alexander Komischke",
            "Dennis Sellemann",
            "Julian Sender",
            "Daniel Diele",
            "Kevin",
            "Amir-Hossein Ebrahimzadeh"
    };

    public static final String[] authorNamesWithTitles = new String[] {
            WindowConfig.alexander_title + seperator  + "Alexander Komischke",
            WindowConfig.dennis_title + seperator + "Dennis Sellemann",
            WindowConfig.julian_title + seperator + "Julian Sender",
            WindowConfig.daniel_title + seperator + "Daniel Diele",
            WindowConfig.kevin_title + seperator + "Kevin Jeske",
            WindowConfig.amir_title + seperator + "Amir-Hossein Ebrahimzadeh"
    };

}
