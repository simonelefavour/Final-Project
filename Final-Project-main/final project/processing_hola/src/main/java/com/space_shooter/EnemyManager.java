package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Iterator;

public class EnemyManager {
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private static ArrayList<EnemyProjectile> enemyProjectiles = new ArrayList<>();
    private GameScreenManager screenManager; // Reference to GameScreenManager

    // Constructor with GameScreenManager
    public EnemyManager(PApplet app, int level, GameScreenManager screenManager) {
        this.screenManager = screenManager; // Initialize the screenManager reference
        enemies.clear(); // Clear existing enemies
        int numEnemies = 5; // Default number of enemies

        if (level == 2) {
            numEnemies = 8; // 8 enemy rockets for level 2
        } else if (level == 3) {
            numEnemies = 10; // 10 enemy rockets for level 3
        }

        // Creating enemies and adding them to the list
        for (int i = 0; i < numEnemies; i++) {
            float startX = 50 + (i % 5) * 100;
            float startY = 50;
            float speedX = app.random(2, 5) * (app.random(1) > 0.5 ? 1 : -1);

            enemies.add(new Enemy(startX, startY, speedX, app, level));
        }
    }

    // Update and display enemies and projectiles
    public void updateAndDisplay(PApplet app, ProjectileManager projectileManager, ScoreManager scoreManager,
            Player player) {
        for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
            Enemy enemy = iterator.next();
            enemy.update(app);
            enemy.display(app);

            // Collision detection
            for (Projectile projectile : projectileManager.getProjectiles()) {
                if (PApplet.dist(enemy.getX(), enemy.getY(), projectile.getX(), projectile.getY()) < 20) {
                    enemy.takeDamage(); // Reduce enemy health
                    projectileManager.removeProjectile(projectile); // Remove projectile
                    scoreManager.increaseScore(10); // Increase score
                    break; // Break the loop
                }
            }

            // Remove enemy if it's dead
            if (enemy.isDead()) {
                iterator.remove();
            }
        }

        for (Iterator<EnemyProjectile> iterator = enemyProjectiles.iterator(); iterator.hasNext();) {
            EnemyProjectile projectile = iterator.next();
            projectile.update(app);
            projectile.display(app);

            // Collision detection with the player
            if (PApplet.dist(player.getX(), player.getY(), projectile.getX(), projectile.getY()) < 20) {
                player.setHealth(player.getHealth() - 1); // Decrease health by 1
                screenManager.triggerFlash(); // Trigger screen flash
                iterator.remove(); // Remove the projectile
            }

        }
    }

    // Add an enemy projectile
    public static void addEnemyProjectile(EnemyProjectile projectile) {
        enemyProjectiles.add(projectile);
    }

    // Check if all enemies are defeated
    public boolean allEnemiesDefeated() {
        return enemies.isEmpty();
    }
}
