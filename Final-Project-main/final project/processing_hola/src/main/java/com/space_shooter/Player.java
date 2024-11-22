/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The player class deals with the player's rocket, score, and health. 
 */

package com.space_shooter;

import processing.core.PApplet;

public class Player extends GameObject {
    private int health = 100; // player's health
    private final float size = 20; // size of the rocket

    // constructor
    public Player(float x, float y) {
        super(x, y);
    }

    // update coordinates
    @Override
    public void update(PApplet app) {
        x = app.mouseX; // mouse x-coordinate
        y = app.mouseY; // mouse y-coordinate
    }

    // display rocket
    @Override
    public void display(PApplet app) {
        app.fill(255); // white
        app.noStroke();

        // draw a triangle for the rocket
        app.beginShape();
        app.vertex(x, y - size); // top
        app.vertex(x - size / 2, y + size); // bottom left
        app.vertex(x + size / 2, y + size); // bottom right
        app.endShape(PApplet.CLOSE);
    }

    // get the player's health
    public int getHealth() {
        return health;
    }

    // decrease player's health when taking damage
    public void takeDamage() {
        if (health > 0)
            health -= 10; // decrease health by 10
    }
}
