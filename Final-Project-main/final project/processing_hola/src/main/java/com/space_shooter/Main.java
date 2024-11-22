/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The main class has the variables, set ups, methods, and initalizing for the game to run. 
 */

package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {
    // variables for the player, enemy, stars
    private Player rocket;
    private Enemy enemy;
    private ArrayList<Star> stars = new ArrayList<>();
    private GameScreenManager screenManager;
    private ScoreManager scoreManager;
    private ProjectileManager projectileManager;
    private int lastShotTime = 0;
    private int shotInterval = 200;

    // method to run
    public static void main(String[] args) {
        PApplet.main("com.space_shooter.Main");
    }

    // set up for game screen
    public void settings() {
        fullScreen();
    }

    // initialize game
    public void setup() {
        // player and enemy
        rocket = new Player(width / 2, height - 50);
        enemy = new Enemy(width / 2, 50);

        // initialize background stars
        for (int i = 0; i < 150; i++) {
            stars.add(new Star(random(width), random(height), this));
        } // end for

        // instance for game manager
        screenManager = new GameScreenManager(this, stars);
        scoreManager = new ScoreManager();
        projectileManager = new ProjectileManager();
    } // end set up

    // draw method for screen transitions and updates
    public void draw() {
        background(0); // black background

        // switch with different screens
        switch (screenManager.getScreenState()) {
            case 0:
                screenManager.displayTitleScreen(); // title screen
                break;

            case 1:
                screenManager.displayCountdown(); // countdown screen
                break;

            case 2:
                displayGameScreen(); // game screen
                break;

            case 3:
                screenManager.displayMainMenu(); // main menu screen
                break;
        } // end switch
    } // end draw

    // game screen, player, enemy, handle shooting and collision
    private void displayGameScreen() {
        rocket.update(this); // update player movement
        rocket.display(this); // display player
        enemy.update(this); // enemy movement
        enemy.display(this); // enemy on the screen

        // shooting, mouse pressed
        if (mousePressed && millis() - lastShotTime >= shotInterval) {
            projectileManager.addProjectile(rocket.getX(), rocket.getY());
            lastShotTime = millis();
        } // end if

        // update/display projectile
        projectileManager.updateAndDisplay(this, enemy, scoreManager);
        scoreManager.displayScore(this, rocket.getHealth());

        // win or game over
        if (enemy.getHealth() <= 0) {
            displayEndMessage("You Win!"); // win message
        } else if (rocket.getHealth() <= 0) {
            displayEndMessage("Game Over!"); // game over message
        }
    } // end display game screen

    // end game message
    private void displayEndMessage(String message) {
        fill(255);
        textSize(48);
        textAlign(CENTER, CENTER);
        text(message, width / 2, height / 2); // center message
        noLoop(); // stop game loop
    }

    // press enter to change screens
    public void keyPressed() {
        if (key == ENTER) {
            // main menue
            if (screenManager.getScreenState() == 0) {
                screenManager.setScreenState(3);
            } // end if
        } // end if
    } // end key pressed
} // end main class
