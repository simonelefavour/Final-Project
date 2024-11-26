package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {
    private Player rocket;
    private EnemyManager enemyManager;
    private PowerUpManager powerUpManager;
    private GameScreenManager screenManager;
    private ScoreManager scoreManager;
    private ProjectileManager projectileManager;
    private int lastShotTime = 0;
    private int shotInterval = 200;
    private boolean isPaused = false;
    private int level = 1; // You can adjust this to change levels dynamically

    public static void main(String[] args) {
        PApplet.main("com.space_shooter.Main");
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        rocket = new Player(width / 2, height - 50);
        projectileManager = new ProjectileManager();

        ArrayList<Star> stars = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            stars.add(new Star(random(width), random(height), this));
        }

        // Initialize EnemyManager with PApplet instance and current level
        enemyManager = new EnemyManager(this, level);
        powerUpManager = new PowerUpManager(this);
        screenManager = new GameScreenManager(this, stars);
        scoreManager = new ScoreManager();
    }

    public void draw() {
        if (isPaused) {
            displayPauseScreen();
            return;
        }

        background(0);

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

    private void displayGameScreen() {
        rocket.update(this);
        rocket.display(this);
        enemyManager.updateAndDisplay(this, projectileManager, scoreManager, rocket);
        powerUpManager.updateAndDisplay(this, rocket, scoreManager);

        if (mousePressed && millis() - lastShotTime >= shotInterval) {
            projectileManager.addProjectile(rocket.getX(), rocket.getY());
            lastShotTime = millis();
        }

        projectileManager.updateAndDisplay(this, enemyManager, scoreManager);
        scoreManager.displayScore(this, rocket.getHealth());

        if (enemyManager.allEnemiesDefeated()) {
            displayGameOverScreen("You Win!");
        } else if (rocket.getHealth() <= 0) {
            displayGameOverScreen("Game Over!");
        }
    }

    private void displayPauseScreen() {
        fill(255);
        textSize(40);
        textAlign(CENTER, CENTER);
        text("Game Paused\nPress SPACE to Resume", width / 2, height / 2);
    }

    private void displayGameOverScreen(String message) {
        fill(0, 0, 0, 150);
        rect(0, 0, width, height);
        fill(255);
        textSize(48);
        textAlign(CENTER, CENTER);
        text(message, width / 2, height / 2 - 50);

        Button restartButton = new Button(
                width / 2 - 100, height / 2 + 50, 200, 50, "Restart", color(0, 128, 0), color(0, 100, 0), this);
        Button exitButton = new Button(
                width / 2 - 100, height / 2 + 120, 200, 50, "Exit", color(128, 0, 0), color(100, 0, 0), this);

        restartButton.setAction(() -> resetGame());
        exitButton.setAction(() -> {
            resetGame();
            screenManager.setScreenState(0);
        });

        restartButton.display(this);
        exitButton.display(this);

        if (mousePressed) {
            restartButton.checkClick(this);
            exitButton.checkClick(this);
        }
    }

    private void resetGame() {
        rocket = new Player(width / 2, height - 50);
        enemyManager = new EnemyManager(this, level); // Initialize again when resetting
        projectileManager = new ProjectileManager();
        powerUpManager = new PowerUpManager(this);
        scoreManager = new ScoreManager();
        screenManager.setScreenState(3);
    }

    public void keyPressed() {
        if (key == ' ') {
            isPaused = !isPaused;
        }

        if (key == ENTER && screenManager.getScreenState() == 0) {
            screenManager.setScreenState(3);
        }
    }
}
