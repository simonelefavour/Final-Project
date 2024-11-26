package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;

public class EnemyManager {
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private static ArrayList<EnemyProjectile> enemyProjectiles = new ArrayList<>();

    // Constructor now accepts a PApplet object
    public EnemyManager(PApplet app, int level) {
        // Clear any existing enemies
        enemies.clear();

        // Set number of enemies based on the level
        int numEnemies = 5; // Default for level 1

        if (level == 2) {
            numEnemies = 9; // 9 enemies for level 2
        } else if (level == 3) {
            numEnemies = 12; // 12 enemies for level 3
        }

        // Create enemies with unique positions and speeds
        for (int i = 0; i < numEnemies; i++) {
            float startX = 50 + i * 100; // Horizontal spacing
            float startY = 50 + (i / 3) * 50; // Change vertical position every 3 enemies for variety
            float speedX = app.random(2, 5) * (app.random(1) > 0.5 ? 1 : -1); // Random speed and direction
            enemies.add(new Enemy(startX, startY, speedX, app)); // Pass app to enemy constructor
        }
    }

    public void updateAndDisplay(PApplet app, ProjectileManager projectileManager, ScoreManager scoreManager,
            Player player) {
        // Update and display enemies
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
