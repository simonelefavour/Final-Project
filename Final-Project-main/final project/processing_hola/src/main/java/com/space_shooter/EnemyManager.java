package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;

public class EnemyManager {
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private static ArrayList<EnemyProjectile> enemyProjectiles = new ArrayList<>();

    public EnemyManager(PApplet app) {
        for (int i = 0; i < 5; i++) {
            float startX = 50 + i * 100; // Horizontal spacing
            float startY = 50; // Fixed vertical position
            float speedX = app.random(2, 5) * (app.random(1) > 0.5 ? 1 : -1); // Random speed and direction
            enemies.add(new Enemy(startX, startY, speedX)); // Pass all required arguments
        }
    }

    public void updateAndDisplay(PApplet app, ProjectileManager projectileManager, ScoreManager scoreManager,
            Player player) {
        for (Enemy enemy : enemies) {
            enemy.update(app);
            enemy.display(app);

            // Check for collisions with projectiles
            for (Projectile projectile : projectileManager.getProjectiles()) {
                if (PApplet.dist(enemy.getX(), enemy.getY(), projectile.getX(), projectile.getY()) < 20) {
                    enemy.takeDamage();
                    projectileManager.removeProjectile(projectile);
                    scoreManager.increaseScore(5);
                    break;
                }
            }

            if (enemy.isDead()) {
                enemies.remove(enemy);
                break;
            }
        }

        // Update and display enemy projectiles
        for (EnemyProjectile projectile : enemyProjectiles) {
            projectile.update(app);
            projectile.display(app);

            // Check for collisions with the player
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
