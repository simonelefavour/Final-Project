/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The rocket class
 * handles the player rocket, including its movement and display on the screen.
 */

package com.space_shooter;

import processing.core.PApplet;

public class Rocket extends GameObject {
    private float size = 20; // rocket size

    // condtructor
    public Rocket(float x, float y) {
        super(x, y);
    }

    // update rocket position
    @Override
    public void update(PApplet app) {
        x = app.mouseX; // x
        y = app.mouseY; // y
    }

    // display
    public void display(PApplet app) {
        app.fill(255); // white
        app.triangle(x - 10, y + 20, x + 10, y + 20, x, y - 20); // triangle
    }
} // end rocket