package de.hsh.kevin.logic.myActor;

import de.hsh.kevin.logic.Config;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Verwaltet Projektile
 * @author Kevin
 *
 */
public class ProjectileManager {

    private ArrayList<Projectile> projectile;
    private long lastProjectileSpawn;

    /**
     * Erstellt den ProjektilManager ohne Projektile
     */
    public ProjectileManager() {
        projectile = new ArrayList<>();
    }

    /**
     * Liefert alle Projektile
     * @return
     */
    public List<Projectile> getProjectiles() {
        return projectile;
    }

    /**
     * Erstellt ein neues Projektil bei dem Spieler
     * @param playerPos Position des Players wo Projektil erstellt werden soll
     * @param offset zur Position des Players
     */
    public void createProjectile(double[] playerPos, double offset) {

        long curMillis = System.currentTimeMillis();
        if (curMillis >= lastProjectileSpawn + Config.projectileSpawnDelay) {
            Projectile p = ProjectileFactory.getProjectile(playerPos[0] + offset, playerPos[1]);
            projectile.add(p);
            lastProjectileSpawn = curMillis;
        }
    }

    /**
     * Bewegt alle Pakete
     */
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

    /**
     * Zeichnet alle Pakete aufs Canvas
     * @param canvas
     */
    public void draw(Canvas canvas) {
        for (Projectile p : projectile) {
            p.draw(canvas);
        }
    }

    /**
     * Entfernt ein Paket
     * @param p zu entfernendes Paket
     */
    public void remove(Projectile p) {
        projectile.remove(p);
    }

}
