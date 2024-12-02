/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The enemy projectile class
 * manages enemy fired projectiles, including their movement and interactions.
 */

package com.space_shooter;

import processing.core.PApplet;

public class EnemyProjectile extends GameObject {
    private float speed = 5; // speed
    private float angle; // direction

    // constructor
    public EnemyProjectile(float x, float y) {
        super(x, y); // passing to parent
        this.angle = PApplet.HALF_PI; // default down
    }

    // constructor for angled
    public EnemyProjectile(float x, float y, float angle) {
        super(x, y);
        this.angle = angle;
    }

    // position of projectile
    @Override
    public void update(PApplet app) {
        // move in direction
        x += PApplet.cos(angle) * speed;
        y += PApplet.sin(angle) * speed;
    }

    // displaying
    @Override
    public void display(PApplet app) {
        app.fill(255, 0, 0); // red
        app.ellipse(x, y, 10, 10); // circle
    }

    // boundaries off screen
    public boolean isOutOfBounds(PApplet app) {
        return x < 0 || x > app.width || y < 0 || y > app.height;
    }
} // end enemy projectile class
