package com.space_shooter;

import processing.core.PApplet;

public class EnemyProjectile extends GameObject {
    private float speed = 5; // Speed of the projectile
    private float angle; // Direction of the projectile (optional, for angled shots)

    // Constructor for standard downward movement
    public EnemyProjectile(float x, float y) {
        super(x, y); // Pass position to the parent class
        this.angle = PApplet.HALF_PI; // Default angle (downward)
    }

    // Constructor for angled projectiles (if needed)
    public EnemyProjectile(float x, float y, float angle) {
        super(x, y);
        this.angle = angle; // Assign angle for projectile direction
    }

    @Override
    public void update(PApplet app) {
        // Move in the direction of the angle
        x += PApplet.cos(angle) * speed;
        y += PApplet.sin(angle) * speed;
    }

    @Override
    public void display(PApplet app) {
        app.fill(255, 0, 0); // Red color for the projectile
        app.ellipse(x, y, 10, 10); // Circular shape
    }

    public boolean isOutOfBounds(PApplet app) {
        // Check if the projectile leaves the screen
        return x < 0 || x > app.width || y < 0 || y > app.height;
    }
}
