package com.space_shooter;

import processing.core.PApplet;
import java.util.LinkedList;

public class ProjectileManager {
    private LinkedList<Projectile> projectiles = new LinkedList<>();

    public void addProjectile(float x, float y) {
        projectiles.add(new Projectile(x, y));
    }

    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    public void updateAndDisplay(PApplet app, EnemyManager enemyManager, ScoreManager scoreManager) {
        for (Projectile projectile : projectiles) {
            projectile.update(app);
            projectile.display(app);

            // Remove if out of bounds
            if (projectile.isOutOfBounds(app)) {
                projectiles.remove(projectile);
            }
        }
    }
}
