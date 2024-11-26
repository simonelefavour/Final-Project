/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The enemy class deals with the enemy and it's behavior. 
 */

package com.space_shooter;

import processing.core.PApplet;

public class Enemy extends GameObject {
    private int health = 50;
    private int shootCooldown = 1000; // Enemy shooting interval
    private long lastShotTime = 0;

    public Enemy(float x, float y) {
        super(x, y);
    }

    @Override
    public void update(PApplet app) {
        // Movement logic here (e.g., zigzag)
        if (app.millis() - lastShotTime >= shootCooldown) {
            shoot(app);
            lastShotTime = app.millis();
        }
    }

    @Override
    public void display(PApplet app) {
        app.fill(255, 0, 0); // Red
        app.triangle(x - 10, y + 10, x + 10, y + 10, x, y - 20);
    }

    public void takeDamage() {
        if (health > 0) {
            health -= 10;
        }
    }

    public boolean isDead() {
        return health <= 0;
    }

    private void shoot(PApplet app) {
        EnemyProjectile projectile = new EnemyProjectile(x, y + 15);
        EnemyManager.addEnemyProjectile(projectile); // Add to manager
    }
}
