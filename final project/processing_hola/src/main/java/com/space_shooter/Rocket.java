package com.space_shooter;

import processing.core.PApplet;

public class Rocket {
    float x, y;

    public Rocket(PApplet app) {
        x = app.width / 2;
        y = app.height / 2;
    } // end rocket

    public void update(PApplet app) {
        // rocket rovement with mouse
        x = app.mouseX;
        y = app.mouseY;
    } // end update

    public void display(PApplet app) {
        app.fill(255); // white rocket
        app.triangle(x - 10, y + 20, x + 10, y + 20, x, y - 20); // rocket shape
    } // end display
} // end rocket class
