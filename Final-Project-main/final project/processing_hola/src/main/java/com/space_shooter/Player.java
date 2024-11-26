/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The player class deals with the player's rocket, score, and health. 
 */

package com.space_shooter;

import processing.core.PApplet;

public class Player extends GameObject {
    private int health = 100; // Player's health
    private final float size = 20; // Rocket size

    public Player(float x, float y) {
        super(x, y);
    }

    @Override
    public void update(PApplet app) {
        x = app.mouseX;
        y = app.mouseY;
    }

    @Override
    public void display(PApplet app) {
        app.fill(255); // White
        app.noStroke();
        app.beginShape();
        app.vertex(x, y - size); // Top
        app.vertex(x - size / 2, y + size); // Bottom left
        app.vertex(x + size / 2, y + size); // Bottom right
        app.endShape(PApplet.CLOSE);
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage() {
        if (health > 0) {
            health -= 10;
        }
    }

    public void restoreHealth(int amount) {
        health = Math.min(100, health + amount); // Restore health
    }
}
