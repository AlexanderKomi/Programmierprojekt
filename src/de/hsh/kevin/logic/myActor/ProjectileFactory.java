package de.hsh.kevin.logic.myActor;

import java.util.ArrayList;

import de.hsh.kevin.logic.Config;

/**
 * Erstellt Projectile
 * 
 * @author Kevin
 *
 */
public class ProjectileFactory {

    private static ArrayList<String> projectileImages;

    static {
        if (projectileImages == null) {
            projectileImages = new ArrayList<>();
            projectileImages.add(Config.resLocation + "kugel.png");
        }
    }

    /**
     * Erstellt ein Projektil an der Stelle (x,y)
     * 
     * @param x
     *            - Wert des Projektils
     * @param y
     *            - Wert des Projektils
     * @return Projektil an der Stelle (x,y)
     */
    public static Projectile getProjectile(double x, double y) {
        return createProjectile(x, y, projectileImages);
    }

    /**
     * Erstellt Projektil mit Wechsenden Bildern
     * 
     * @param x
     *            - Wert des Projektils
     * @param y
     *            - Wert des Projektils *
     * @param images
     *            Bilder durch die das Projektil wechselt
     * @return Projektil an der Stelle (x,y)
     * 
     */
    private static Projectile createProjectile(double x, double y, ArrayList<String> images) {
        Projectile p = null;
        if (images.size() == 1) {
            p = new Projectile(images.get(0));
        } else {
            p = new Projectile(images);
        }
        p.setPos(x, y);

        return p;
    }

}
