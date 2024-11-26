/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The player class
 * deals with managing the player character, including movement, health, and level settings
 */

package com.space_shooter;

import processing.core.PApplet;

public class Player extends GameObject {
    private int health = 100; // health
    private final float size = 20; // size of rocket
    private int level = 1; // default level
    private boolean rapidFireActive = false; // rapid fire
    private long rapidFireStartTime = 0;
    private int rapidFireDuration = 5000; // 5 seconds

    // constructor
    public Player(float x, float y) {
        super(x, y);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // player position from mouse
    @Override
    public void update(PApplet app) {
        x = app.mouseX; // horizontal
        y = app.mouseY; // vertical

        // restriction for level 2/3 bottom half of screen
        if (level >= 2) {
            if (y < app.height / 2) {
                y = app.height / 2;
            }
        }

        // active fire active/deactivate
        if (rapidFireActive && app.millis() - rapidFireStartTime >= rapidFireDuration) {
            rapidFireActive = false;
        }
    }

    // activating
    public void activateRapidFire(long startTime) {
        rapidFireActive = true;
        rapidFireStartTime = startTime;
    }

    // checking
    public boolean isRapidFireActive() {
        return rapidFireActive;
    }

    // rocket
    @Override
    public void display(PApplet app) {
        app.fill(255);
        app.noStroke();
        app.beginShape();
        app.vertex(x, y - size);
        app.vertex(x - size / 2, y + size);
        app.vertex(x + size / 2, y + size);
        app.endShape(PApplet.CLOSE);
    }

    // getter
    public int getHealth() {
        return health;
    }

    // reduce health
    public void takeDamage() {
        if (health > 0) {
            health -= 10;
        }
    }

    // restoring
    public void restoreHealth(int amount) {
        health = Math.min(100, health + amount);
    }
} // end player
