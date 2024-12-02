/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The GameObject class
 * is an abstract base class for all game objects, defining shared properties and methods.
 */

package com.space_shooter;

import processing.core.PApplet;

public abstract class GameObject {
    protected float x, y; // position

    // constructor
    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update(PApplet app);

    // display
    public abstract void display(PApplet app);

    // getter x
    public float getX() {
        return x;
    }

    // getter y
    public float getY() {
        return y;
    }
} // end game object class
