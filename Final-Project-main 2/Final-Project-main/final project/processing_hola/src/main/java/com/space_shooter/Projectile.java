/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The projectile class 
 * deals with the projectiles being shot from the rocket.
 */

package com.space_shooter;

import processing.core.PApplet;

public class Projectile extends GameObject {
    // moves up
    private float speedY = -5;

    // constructor
    public Projectile(float x, float y) {
        super(x, y);
    }

    // projectile position
    @Override
    public void update(PApplet app) {
        y += speedY; // projectile upwards
    }

    // display projectile
    @Override
    public void display(PApplet app) {
        app.fill(255, 255, 0); // yellow
        app.ellipse(x, y, 10, 10); // circles
    }

    // boundaries
    public boolean isOutOfBounds(PApplet app) {
        return y < 0 || y > app.height;
    }
} // end projectile class
