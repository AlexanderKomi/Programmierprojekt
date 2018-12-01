package de.hsh.kevin.logic.myActor;

import java.util.ArrayList;
import java.util.List;

import de.hsh.kevin.logic.Config;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class ProjectileManager {

    private ArrayList<Projectile> projectile;
    private long lastProjectileSpawn;

    public ProjectileManager() {
	projectile = new ArrayList<>();
    }

    public List<Projectile> getProjectiles() {
	return projectile;
    }

    public void createProjectile(double[] playerPos, double offset) {

	long curMillis = System.currentTimeMillis();
	if (curMillis >= lastProjectileSpawn + Config.projectileSpawnDelay) {
	    Projectile p = ProjectileFactory.getProjectile(playerPos[0] + offset, playerPos[1]);
	    projectile.add(p);
	    lastProjectileSpawn = curMillis;
	}
    }


    public void move() {
	for (int i = 0; i < projectile.size(); i++) {
	    if (projectile.get(i).getPos()[1] < 0) {
		projectile.remove(i);
	    } else {
		projectile.get(i).move();
	    }
	}
    }

    public void draw(Canvas canvas) {
	for (Projectile p : projectile) {
	    p.draw(canvas, 0, 0);
	}
    }

}
