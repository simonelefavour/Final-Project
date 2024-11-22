/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The background class has the twinkling stars.
 */

package com.space_shooter;

import processing.core.PApplet;

public class BackgroundStar {
    private float x, y, size, brightness, fadeSpeed, rotation;
    private PApplet app;

    public BackgroundStar(float x, float y, PApplet app) {
        this.x = x;
        this.y = y;
        this.size = app.random(2, 4); // slightly brighter
        this.brightness = app.random(100, 255); // initial brightness
        this.fadeSpeed = app.random(0.002f, 0.005f); // twinkling
        this.rotation = app.random(PApplet.TWO_PI); // random rotation
        this.app = app;
    }

    public void update() {
        // brightness
        brightness = 150 + 100 * PApplet.sin(app.millis() * fadeSpeed);

        // rotation
        rotation += 0.01;
    }

    public void display() {
        app.pushMatrix();
        app.translate(x, y); // star position
        app.rotate(rotation); // rotation

        app.fill(255, brightness); // white
        app.noStroke();

        // 4 point star
        app.beginShape();
        for (int i = 0; i < 8; i++) {
            float angle = PApplet.TWO_PI / 8 * i;
            float r = (i % 2 == 0) ? size : size / 2;
            float px = PApplet.cos(angle) * r;
            float py = PApplet.sin(angle) * r;
            app.vertex(px, py);
        }
        app.endShape(PApplet.CLOSE);

        app.popMatrix();
    }
}