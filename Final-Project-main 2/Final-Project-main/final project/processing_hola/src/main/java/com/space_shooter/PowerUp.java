package com.space_shooter;

import processing.core.PApplet;

public class PowerUp extends GameObject {
    private String type; // e.g., "health", "rapid-fire", "score"
    private float speedY = 2;

    public PowerUp(float x, float y, String type) {
        super(x, y);
        this.type = type;
    }

    @Override
    public void update(PApplet app) {
        y += speedY;
    }

    @Override
    public void display(PApplet app) {
        app.fill(type.equals("health") ? app.color(0, 255, 0)
                : type.equals("rapid-fire") ? app.color(0, 0, 255) : app.color(255, 255, 0));
        app.rect(x - 10, y - 10, 20, 20); // Square
    }

    public String getType() {
        return type;
    }

    public boolean isOutOfBounds(PApplet app) {
        return y > app.height;
    }
}
