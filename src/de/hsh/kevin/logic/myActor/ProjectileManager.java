package de.hsh.kevin.logic.myActor;

import de.hsh.kevin.logic.Config;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Projectile> toRemove = new ArrayList<>();

        projectile.stream().parallel().forEach(proj -> {
            if (proj.getPos()[1] < 0) {
                toRemove.add(proj);
            } else {
                proj.move();
            }
        });

        for (int i = 0; i < toRemove.size(); i++) {
            projectile.remove(toRemove.get(i));
        }

        for (int i = 0; i < projectile.size(); i++) {

        }
    }

    public void draw(Canvas canvas) {
        for (Projectile p : projectile) {
            p.draw(canvas);
        }
    }

    public void remove(Projectile p) {
        projectile.remove(p);
    }

}
