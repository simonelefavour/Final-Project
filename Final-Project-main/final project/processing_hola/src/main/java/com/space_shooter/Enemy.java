/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The enemy class
 * manages the enemy ships, including their movement, health, shooting mechanics, and interactions.
 */

package com.space_shooter;

import processing.core.PApplet;

public class Enemy extends GameObject {
    private int health = 50; // initial health
    private int shootCooldown; // enemy shots
    private long lastShotTime = 0; // last time enemy shot
    private float speedX; // speed

    // constructor
    public Enemy(float x, float y, float speedX, PApplet app, int level) {
        super(x, y);
        this.speedX = speedX;

        // shooting depend on level
        if (level == 3) {
            this.shootCooldown = (int) app.random(500, 1500); // faster shooting for 3
        } else {
            this.shootCooldown = (int) app.random(1000, 3000); // default
        }
    }

    // enemy position and shooting
    @Override
    public void update(PApplet app) {
        x += speedX;

        // bounce off screen
        if (x < 0 || x > app.width) {
            speedX *= -1; // reverse
        }

        // shooting
        if (app.millis() - lastShotTime >= shootCooldown) {
            shoot(app);
            lastShotTime = app.millis(); // update last shot
        }
    }

    // displaying enemy
    @Override
    public void display(PApplet app) {
        app.pushMatrix();
        app.translate(x, y);
        app.rotate(PApplet.PI);
        app.fill(255, 0, 0);
        app.triangle(-10, 10, 10, 10, 0, -20); // enemy triangle
        app.popMatrix();
    }

    // reduce enemy health when shot
    public void takeDamage() {
        if (health > 0) {
            health -= 10;
        }
    }

    // check if dead
    public boolean isDead() {
        return health <= 0;
    }

    // shooting projectile
    private void shoot(PApplet app) {
        EnemyProjectile projectile = new EnemyProjectile(x, y + 20);
        EnemyManager.addEnemyProjectile(projectile);
    }
} // end enemy class
