package com.space_shooter;

import processing.core.PApplet;

public class EnemyProjectile extends GameObject {
    private float speedY = 5;

    public EnemyProjectile(float x, float y) {
        super(x, y);
    }

    @Override
    public void update(PApplet app) {
        y += speedY; // Move down
    }

    @Override
    public void display(PApplet app) {
        app.fill(255, 0, 0); // Red
        app.ellipse(x, y, 10, 10); // Circle
    }

    public boolean isOutOfBounds(PApplet app) {
        return y > app.height;
    }
}
