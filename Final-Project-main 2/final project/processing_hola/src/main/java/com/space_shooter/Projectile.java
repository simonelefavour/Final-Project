package com.space_shooter;

import processing.core.PApplet;

public class Projectile extends GameObject {

    private float speedY = -5; // speed y direction

    public Projectile(float x, float y) {
        super(x, y);
    }

    public void update(PApplet app) {
        y += speedY; // shoots upward
    } // end update

    public void display(PApplet app) {
        app.fill(255, 255, 0); // yellow
        app.ellipse(x, y, 10, 10); // draw circle
    } // end display

    public boolean isOutOfBounds() {
        return y < 0; // check projectile off screen
    } // end isOutOfBounds

    public boolean collidesWith(GameObject obj, PApplet app) {
        return PApplet.dist(x, y, obj.getX(), obj.getY()) < 20; // collision
    } // end collidesWith
} // end projectile class
