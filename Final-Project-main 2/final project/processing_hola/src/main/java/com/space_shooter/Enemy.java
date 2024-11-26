package com.space_shooter;

import processing.core.PApplet;

public class Enemy extends GameObject {
    private int health = 50; // enemy health

    public Enemy(float x, float y) {
        super(x, y);
    } // end enemy

    public void update(PApplet app) {
        // enemy movement
    } // end update

    public void display(PApplet app) {
        app.fill(255, 0, 0); // red
        app.rectMode(PApplet.CENTER);
        app.rect(x, y, 30, 30); // square as enemy for now
    } // end display

    public int getHealth() {
        return health;
    } // end getHealth

    public void takeDamage() {
        if (health > 0)
            health -= 10; // decrease health
    }
} // end enemy class
