/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The game object helps update and display. It's the abstract class.
 */

package com.space_shooter;

import processing.core.PApplet;

// abstract class
public abstract class GameObject {
    protected float x, y; // position of object

    // constructor
    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // update state
    public abstract void update(PApplet app);

    // display
    public abstract void display(PApplet app);

    // x position
    public float getX() {
        return x;
    }

    // y position
    public float getY() {
        return y;
    }
}
