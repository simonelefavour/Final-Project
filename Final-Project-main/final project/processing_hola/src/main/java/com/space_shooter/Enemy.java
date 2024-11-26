package com.space_shooter;

import processing.core.PApplet;

public class Enemy extends GameObject {
    private int health = 50;
    private int shootCooldown = 1000; // Enemy shoots every 1 second
    private long lastShotTime = 0; // Tracks the last time the enemy shot
    private float speedX; // Horizontal speed of the enemy

    public Enemy(float x, float y, float speedX) {
        super(x, y);
        this.speedX = speedX; // Assign unique speed for this enemy
    }

    @Override
    public void update(PApplet app) {
        // Horizontal back-and-forth movement
        x += speedX;

        // Reverse direction when reaching screen edges
        if (x < 0 || x > app.width) {
            speedX *= -1; // Reverse direction
        }

        // Shooting logic
        if (app.millis() - lastShotTime >= shootCooldown) {
            shoot(app);
            lastShotTime = app.millis();
        }
    }

    @Override
    public void display(PApplet app) {
        app.pushMatrix();

        // Translate to enemy position
        app.translate(x, y);

        // Rotate 180 degrees (upside-down)
        app.rotate(PApplet.PI);

        // Draw the triangle
        app.fill(255, 0, 0); // Red color
        app.triangle(-10, 10, 10, 10, 0, -20); // Triangle shape

        app.popMatrix();
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
        // Create an enemy projectile starting from the tip of the triangle
        EnemyProjectile projectile = new EnemyProjectile(x, y + 20); // Adjusted for downward-facing triangle
        EnemyManager.addEnemyProjectile(projectile);
    }
}
