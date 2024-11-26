/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The projectile class deals with the projectiles being shot from the rocket.
 */

package com.space_shooter;

import processing.core.PApplet;

public class Projectile extends GameObject {
    private float speedY = -5;

    public Projectile(float x, float y) {
        super(x, y);
    }

    @Override
    public void update(PApplet app) {
        y += speedY; // Move up
    }

    @Override
    public void display(PApplet app) {
        app.fill(255, 255, 0); // Yellow
        app.ellipse(x, y, 10, 10); // Circle
    }

    public boolean isOutOfBounds(PApplet app) {
        return y < 0 || y > app.height;
    }
}
