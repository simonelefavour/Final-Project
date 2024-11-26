/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The enemy manager class
 * manages the creation, movement, interactions, and removal of all enemy ships and their projectiles.
 */

package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Iterator;

public class EnemyManager {
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private static ArrayList<EnemyProjectile> enemyProjectiles = new ArrayList<>();

    // constructor
    public EnemyManager(PApplet app, int level) {
        enemies.clear(); // clear existing enemies
        int numEnemies = 5; // default

        if (level == 2) {
            numEnemies = 8; // 8 enemy rockets for level 2
        } else if (level == 3) {
            numEnemies = 10; // 10 enemy rockets for level 3
        }

        // creating enemies and adding to list
        for (int i = 0; i < numEnemies; i++) {
            float startX = 50 + (i % 5) * 100;
            float startY = 50;
            float speedX = app.random(2, 5) * (app.random(1) > 0.5 ? 1 : -1);

            enemies.add(new Enemy(startX, startY, speedX, app, level));
        }
    }

    // update and display enemy and projectiles
    public void updateAndDisplay(PApplet app, ProjectileManager projectileManager, ScoreManager scoreManager,
            Player player) {

        for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
            Enemy enemy = iterator.next();
            enemy.update(app);
            enemy.display(app);

            // collision
            for (Projectile projectile : projectileManager.getProjectiles()) {
                if (PApplet.dist(enemy.getX(), enemy.getY(), projectile.getX(), projectile.getY()) < 20) {
                    enemy.takeDamage(); // enemy health
                    projectileManager.removeProjectile(projectile); // remove projectile
                    scoreManager.increaseScore(5); // increase score
                    break; // break loop
                }
            }

            // remove if enemy is dead
            if (enemy.isDead()) {
                iterator.remove();
            }
        }

        for (Iterator<EnemyProjectile> iterator = enemyProjectiles.iterator(); iterator.hasNext();) {
            EnemyProjectile projectile = iterator.next();
            projectile.update(app);
            projectile.display(app);

            // collision
            if (PApplet.dist(player.getX(), player.getY(), projectile.getX(), projectile.getY()) < 20) {
                player.takeDamage(); // player health
                iterator.remove(); // remove projectile
            }
        }
    }

    // adding enemy projectile
    public static void addEnemyProjectile(EnemyProjectile projectile) {
        enemyProjectiles.add(projectile);
    }

    // check if enemies defeated
    public boolean allEnemiesDefeated() {
        return enemies.isEmpty();
    }
} // end enemy manager
