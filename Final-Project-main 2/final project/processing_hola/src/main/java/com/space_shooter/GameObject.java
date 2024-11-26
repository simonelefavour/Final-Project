package com.space_shooter;

import processing.core.PApplet;

public abstract class GameObject {
    protected float x, y; // position of object

    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
    } // end gameObject

    public abstract void update(PApplet app); // updates object

    public abstract void display(PApplet app); // sisplays object

    public float getX() {
        return x;
    } // end float getX

    public float getY() {
        return y;
    } // end float getY
} // end GameObject
