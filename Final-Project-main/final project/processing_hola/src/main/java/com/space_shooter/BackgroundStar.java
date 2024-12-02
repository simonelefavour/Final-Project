/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The background star class
 * deals with managing the stars in the background, including their movement, rotation, and fading effects.
 */

package com.space_shooter;

import processing.core.PApplet;

public class BackgroundStar {
    private float x, y; // star position
    private float size; // size
    private float brightness; // brightness of the star
    private float fadeSpeed; // fade in and out speed
    private float rotation; // rota tion angle of the star
    private PApplet app;

    // consdtructor
    public BackgroundStar(float x, float y, PApplet app) {
        this.x = x;
        this.y = y;
        this.size = app.random(2, 4); // random size
        this.brightness = app.random(100, 255); // brightness
        this.fadeSpeed = app.random(0.002f, 0.005f); // random fade speed 0.002 and 0.005
        this.rotation = app.random(PApplet.TWO_PI); // random initial rotation angle
        this.app = app;
    }

    // update brightness and rotation
    public void update() {
        // twinkling effect using sine wave
        brightness = 150 + 100 * PApplet.sin(app.millis() * fadeSpeed);

        // rotation effect
        rotation += 0.01;
    }

    // display
    public void display() {
        app.pushMatrix(); // save the current transformation matrix
        app.translate(x, y); // move the origin to the position of the star
        app.rotate(rotation); // applying rotation to the star

        app.fill(255, brightness); // set color of the star with dynamic brightness
        app.noStroke(); // no outline for the star

        // draw an 8-point star shape
        app.beginShape();
        for (int i = 0; i < 8; i++) {
            float angle = PApplet.TWO_PI / 8 * i; // angle between each point of the star
            float r = (i % 2 == 0) ? size : size / 2; // outer and inner radius
            float px = PApplet.cos(angle) * r;
            float py = PApplet.sin(angle) * r;
            app.vertex(px, py); // vertex at calculated position
        }
        app.endShape(PApplet.CLOSE); // closing shape

        app.popMatrix();
    }
} // end background star
