package com.space_shooter;

import processing.core.PApplet;
import java.util.LinkedList;

public class ProjectileManager {
    private LinkedList<Projectile> projectiles = new LinkedList<>();

    public void addProjectile(float x, float y) {
        projectiles.add(new Projectile(x, y));
    }

    public void updateAndDisplay(PApplet app, Enemy enemy, ScoreManager scoreManager) {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile p = projectiles.get(i);
            p.update(app);
            p.display(app);

            if (p.isOutOfBounds()) {
                projectiles.remove(i);
            } else if (p.collidesWith(enemy, app)) {
                scoreManager.increaseScore(10);
                enemy.takeDamage();
                projectiles.remove(i);
            }
        }
    }
}
