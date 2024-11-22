/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The projectile class deals with the projectiles being shot from the rocket.
 */

package com.space_shooter;

import processing.core.PApplet;

public class Projectile extends GameObject {

    private float speedY = -5; // speed y direction

    // constructor
    public Projectile(float x, float y) {
        super(x, y); // calls parent
    } // end projectile

    // update position
    @Override
    public void update(PApplet app) {
        y += speedY; // move up
    }

    // yellow circle
    @Override
    public void display(PApplet app) {
        app.fill(255, 255, 0); // yellow color
        app.ellipse(x, y, 10, 10); // circle
    } // end display

    // bounds
    public boolean isOutOfBounds() {
        return y < 0; // boundaries
    }

    // collision
    public boolean collidesWith(GameObject obj, PApplet app) {
        return PApplet.dist(x, y, obj.getX(), obj.getY()) < 20; // collision detection
    } // end collides with
} // end projectile class
