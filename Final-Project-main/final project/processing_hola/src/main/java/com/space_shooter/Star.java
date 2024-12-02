/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The Star class
 * deals with managing the stars on the title screen, including their movement, rotation, and fading effects.
 */

package com.space_shooter;

import processing.core.PApplet;

public class Star {
    private float x, y; // star position
    private float speedX, speedY; // x, y speed direction
    private float size; // size
    private float brightness; // brightness
    private int starColor; // yellow, white
    private float fadeSpeed; // fade in, out
    private float rotationAngle; // rotation
    private float rotationSpeed; // speed of rotation

    public Star(float x, float y, PApplet app) {
        this.x = x;
        this.y = y;
        this.size = app.random(3, 8); // random size
        this.brightness = app.random(150, 255); // random initial brightness
        this.speedX = app.random(-0.5f, 0.5f); // random horizontal speed
        this.speedY = app.random(-0.5f, 0.5f); // random vertical

        // yellow or white
        this.starColor = (app.random(1) > 0.5) ? app.color(255, 255, 0) : app.color(255);

        // fade speed and rotation
        this.fadeSpeed = app.random(0.002f, 0.005f);
        this.rotationAngle = app.random(PApplet.TWO_PI); // random rotation angle
        this.rotationSpeed = (app.random(1) > 0.5) ? app.random(0.01f, 0.05f) : -app.random(0.01f, 0.05f);
    } // end star

    public void update(PApplet app) {

        // updating position
        x += speedX;
        y += speedY;

        // bouncing off edges
        if (x < 0 || x > app.width)
            speedX *= -1;
        if (y < 0 || y > app.height)
            speedY *= -1;

        // fading in out using sine wave
        brightness = 150 + 100 * PApplet.sin(app.millis() * fadeSpeed);

        // angle based on speed
        rotationAngle += rotationSpeed;
    } // end update

    public void display(PApplet app) {
        app.fill(starColor, brightness); // color and brightness level
        app.noStroke();

        // rotation and draw star shape
        app.pushMatrix();
        app.translate(x, y); // move origin to star positon
        app.rotate(rotationAngle); // rotation

        app.beginShape();
        for (int i = 0; i < 10; i++) {
            float angle = PApplet.TWO_PI / 10 * i; // angle between points
            float radius = (i % 2 == 0) ? size : size / 2; // outer, inner radius
            float xOuter = PApplet.cos(angle) * radius;
            float yOuter = PApplet.sin(angle) * radius;
            app.vertex(xOuter, yOuter);
        } // end for

        app.endShape(PApplet.CLOSE);
        app.popMatrix();
    } // end display
} // end star class