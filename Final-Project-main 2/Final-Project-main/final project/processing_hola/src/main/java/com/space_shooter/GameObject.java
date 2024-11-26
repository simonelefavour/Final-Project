package com.space_shooter;

import processing.core.PApplet;

// Abstract class for game objects
public abstract class GameObject {
    protected float x, y; // Position of object

    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update(PApplet app); // Update state

    public abstract void display(PApplet app); // Display object

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
