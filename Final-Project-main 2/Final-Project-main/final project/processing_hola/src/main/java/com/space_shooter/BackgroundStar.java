package com.space_shooter;

import processing.core.PApplet;

public class BackgroundStar {
    private float x, y, size, brightness, fadeSpeed, rotation;
    private PApplet app;

    public BackgroundStar(float x, float y, PApplet app) {
        this.x = x;
        this.y = y;
        this.size = app.random(2, 4); // Slightly brighter
        this.brightness = app.random(100, 255); // Initial brightness
        this.fadeSpeed = app.random(0.002f, 0.005f); // Twinkling
        this.rotation = app.random(PApplet.TWO_PI); // Random rotation
        this.app = app;
    }

    public void update() {
        // Brightness twinkling effect
        brightness = 150 + 100 * PApplet.sin(app.millis() * fadeSpeed);

        // Continuous rotation for star effect
        rotation += 0.01;
    }

    public void display() {
        app.pushMatrix();
        app.translate(x, y); // Set position of the star
        app.rotate(rotation); // Apply rotation

        app.fill(255, brightness); // White color with dynamic brightness
        app.noStroke();

        // Draw 4-point star shape
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
