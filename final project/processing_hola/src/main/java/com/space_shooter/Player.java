package com.space_shooter;

import processing.core.PApplet;

public class Player extends GameObject {
    private int health = 100; // player health
    private final float size = 20; // size the rocket

    public Player(float x, float y) {
        super(x, y);
    } // end player

    public void update(PApplet app) {
        x = app.mouseX; // mouse x-coordinate
        y = app.mouseY; // mouse y-coordinate
    } // end update

    public void display(PApplet app) {
        app.fill(255); // white
        app.noStroke();

        // triangle for rocket
        app.beginShape();
        app.vertex(x, y - size); // top
        app.vertex(x - size / 2, y + size); // bottom left
        app.vertex(x + size / 2, y + size); // bottom right
        app.endShape(PApplet.CLOSE);
    } // end display

    public int getHealth() {
        return health;
    } // end getHealth

    public void takeDamage() {
        if (health > 0)
            health -= 10; // decreasing health
    } // end takeDamage
} // end class player
