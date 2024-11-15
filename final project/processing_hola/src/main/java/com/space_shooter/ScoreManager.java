package com.space_shooter;

import processing.core.PApplet;

public class ScoreManager {
    private int score = 0;

    public void increaseScore(int amount) {
        score += amount;
    }

    public int getScore() {
        return score;
    }

    public void displayScore(PApplet app, int playerHealth) {
        app.fill(255);
        app.textSize(16);
        app.text("Score: " + score, 50, 30);
        app.text("Health: " + playerHealth, app.width - 100, 30);
    }
}
