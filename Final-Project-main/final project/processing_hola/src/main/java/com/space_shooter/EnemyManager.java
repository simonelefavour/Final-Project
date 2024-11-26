package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;

public class EnemyManager {
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private static ArrayList<EnemyProjectile> enemyProjectiles = new ArrayList<>();

    public EnemyManager(PApplet app) {
        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(app.random(50, app.width - 50), app.random(50, 200)));
        }
    }

    public void updateAndDisplay(PApplet app, ProjectileManager projectileManager, ScoreManager scoreManager,
            Player player) {
        for (Enemy enemy : enemies) {
            enemy.update(app);
            enemy.display(app);

            // Check for collisions between enemy and projectiles
            for (Projectile projectile : projectileManager.getProjectiles()) {
                if (PApplet.dist(enemy.getX(), enemy.getY(), projectile.getX(), projectile.getY()) < 20) {
                    enemy.takeDamage();
                    projectileManager.removeProjectile(projectile); // Use ProjectileManager to remove
                    scoreManager.increaseScore(5);
                    break;
                }
            }

            // Remove dead enemies
            if (enemy.isDead()) {
                enemies.remove(enemy);
                break;
            }
        }

        // Update and display enemy projectiles
        for (EnemyProjectile projectile : enemyProjectiles) {
            projectile.update(app);
            projectile.display(app);

            // Check for collisions with player
            if (PApplet.dist(player.getX(), player.getY(), projectile.getX(), projectile.getY()) < 20) {
                player.takeDamage();
                enemyProjectiles.remove(projectile);
                break;
            }
        }
    }

    public static void addEnemyProjectile(EnemyProjectile projectile) {
        enemyProjectiles.add(projectile);
    }

    public boolean allEnemiesDefeated() {
        return enemies.isEmpty();
    }
}
