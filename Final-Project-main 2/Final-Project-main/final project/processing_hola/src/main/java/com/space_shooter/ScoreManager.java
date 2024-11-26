/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The score manager class helps deal with the player score.
 */

package com.space_shooter;

import processing.core.PApplet;

public class ScoreManager {
    private int score = 0; // initial score

    // increase score
    public void increaseScore(int amount) {
        score += amount;
    }

    // current score
    public int getScore() {
        return score;
    }

    // score and health
    public void displayScore(PApplet app, int playerHealth) {
        app.fill(255);
        app.textSize(40);
        app.text("SCORE: " + score, 70, 60); // top left
        app.text("HEALTH: " + playerHealth, app.width - 100, 60); // top right
    }
}
