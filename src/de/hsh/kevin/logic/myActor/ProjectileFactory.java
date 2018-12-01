package de.hsh.kevin.logic.myActor;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import de.hsh.kevin.logic.Config;

public class ProjectileFactory {

    private static ArrayList<String> projectileImages;

    static {
	if (projectileImages == null) {
	    projectileImages = new ArrayList<>();
	    projectileImages.add(Config.resLocation + "kugel.png");
	}
    }

    public static Projectile getProjectile(double x, double y) {
	return createProjectile(x, y, projectileImages);
    }

    private static Projectile createProjectile(double x, double y, ArrayList<String> images) {
	Projectile p = null;
	try {
	    if (images.size() == 1) {
		p = new Projectile(images.get(0));
	    } else {
		p = new Projectile(images);
	    }
	    p.setPos(x, y);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	
	return p;
    }

}
