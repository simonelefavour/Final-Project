/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The main class has the variables, set ups, methods, and initializing for the game to run. 
 */

package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {
    // Variables for the player, enemy, stars
    private Player rocket;
    private EnemyManager enemyManager;
    private PowerUpManager powerUpManager;
    private GameScreenManager screenManager;
    private ScoreManager scoreManager;
    private ProjectileManager projectileManager;
    private int lastShotTime = 0;
    private int shotInterval = 200;
    private boolean isPaused = false; // Pause state

    // Method to run
    public static void main(String[] args) {
        PApplet.main("com.space_shooter.Main");
    }

    // Set up for game screen
    public void settings() {
        fullScreen();
    }

    // Initialize game
    public void setup() {
        rocket = new Player(width / 2, height - 50);
        projectileManager = new ProjectileManager();

        // Initialize stars for the title screen
        ArrayList<Star> stars = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            stars.add(new Star(random(width), random(height), this));
        }

        enemyManager = new EnemyManager(this);
        powerUpManager = new PowerUpManager(this);
        screenManager = new GameScreenManager(this, stars); // Pass stars to GameScreenManager
        scoreManager = new ScoreManager();
    }

    // Draw method for screen transitions and updates
    public void draw() {
        if (isPaused) {
            displayPauseScreen();
            return;
        }

        background(0); // Black background

        // Switch with different screens
        switch (screenManager.getScreenState()) {
            case 0:
                screenManager.displayTitleScreen();
                break;
            case 1:
                screenManager.displayCountdown();
                break;
            case 2:
                displayGameScreen();
                break;
            case 3:
                screenManager.displayMainMenu();
                break;
        }
    }

    // Game screen, player, enemy, handle shooting and collision
    private void displayGameScreen() {
        rocket.update(this); // Update player movement
        rocket.display(this); // Display player
        enemyManager.updateAndDisplay(this, projectileManager, scoreManager, rocket); // Enemies
        powerUpManager.updateAndDisplay(this, rocket, scoreManager); // Power-ups

        // Handle player shooting
        if (mousePressed && millis() - lastShotTime >= shotInterval) {
            projectileManager.addProjectile(rocket.getX(), rocket.getY());
            lastShotTime = millis();
        }

        // Update and display projectiles
        projectileManager.updateAndDisplay(this, enemyManager, scoreManager);

        // Display score and health
        scoreManager.displayScore(this, rocket.getHealth());

        // Check for win or game over
        if (enemyManager.allEnemiesDefeated()) {
            displayGameOverScreen("You Win!");
        } else if (rocket.getHealth() <= 0) {
            displayGameOverScreen("Game Over!");
        }
    }

    // Pause screen
    private void displayPauseScreen() {
        fill(255);
        textSize(40);
        textAlign(CENTER, CENTER);
        text("Game Paused\nPress SPACE to Resume", width / 2, height / 2);
    }

    // Game over screen with restart and exit buttons
    private void displayGameOverScreen(String message) {
        fill(0, 0, 0, 150);
        rect(0, 0, width, height); // Overlay
        fill(255);
        textSize(48);
        textAlign(CENTER, CENTER);
        text(message, width / 2, height / 2 - 50);

        Button restartButton = new Button(
                width / 2 - 100, height / 2 + 50, 200, 50, "Restart", color(0, 128, 0), color(0, 100, 0), this);
        Button exitButton = new Button(
                width / 2 - 100, height / 2 + 120, 200, 50, "Exit", color(128, 0, 0), color(100, 0, 0), this);

        restartButton.setAction(() -> screenManager.setScreenState(3));
        exitButton.setAction(() -> screenManager.setScreenState(0));

        restartButton.display(this);
        exitButton.display(this);

        if (mousePressed) {
            restartButton.checkClick(this);
            exitButton.checkClick(this);
        }
    }

    // Key handling for pausing the game and moving to main menu
    public void keyPressed() {
        if (key == ' ') {
            isPaused = !isPaused; // Toggle pause
        }

        if (key == ENTER && screenManager.getScreenState() == 0) {
            screenManager.setScreenState(3); // Go to main menu
        }
    }
}
