package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {

    private static int level = 1; // static level variable
    private static EnemyManager enemyManager; // static enemy manager
    private Player rocket; // the rocket
    private PowerUpManager powerUpManager; // power ups
    private GameScreenManager screenManager; // transitions
    private ScoreManager scoreManager; // score
    private ProjectileManager projectileManager; // projectiles shot
    private int lastShotTime = 0; // last fired shot
    private int shotInterval = 150; // interval b/t last fired shot
    private boolean isPaused = false; // game pause
    private int normalShotInterval = 200; // normal shooting interval
    private int rapidFireShotInterval = 100; // faster shooting interval for rapid fire
    private ArrayList<Star> stars;

    public static void main(String[] args) {
        PApplet.main("com.space_shooter.Main");
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        // initialize player rocket
        rocket = new Player(width / 2, height - 50);
        rocket.setLevel(level);

        // initialize projectile manager
        projectileManager = new ProjectileManager();

        // arraylist for title screen stars
        stars = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            stars.add(new Star(random(width), random(height), this));
        }

        // initialize powerups, screens, score, enemies
        enemyManager = new EnemyManager(this, level);
        powerUpManager = new PowerUpManager(this);
        screenManager = new GameScreenManager(this, stars, rocket, level, enemyManager);
        scoreManager = new ScoreManager();
    }

    public void draw() {

        // paused
        if (isPaused) {
            displayPauseScreen();
            return;
        }

        background(0);

        // transitions
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

        rocket.update(this); // player rocket position
        rocket.display(this); // // display player rocket

        int shotInterval = rocket.isRapidFireActive() ? rapidFireShotInterval : normalShotInterval;

        // Allow shooting with the adjusted interval
        if (mousePressed && millis() - lastShotTime >= shotInterval) {
            projectileManager.addProjectile(rocket.getX(), rocket.getY());
            lastShotTime = millis();
        }

        projectileManager.updateAndDisplay(this, enemyManager, scoreManager);

        // update and display power ups, enemies, projectiles
        screenManager.displayGameScreen(); // game screen elements
        enemyManager.updateAndDisplay(this, projectileManager, scoreManager, rocket);
        powerUpManager.updateAndDisplay(this, rocket, scoreManager);
        projectileManager.updateAndDisplay(this, enemyManager, scoreManager);
        scoreManager.displayScore(this, rocket.getHealth());

        // player shooting with a delay between shots
        if (mousePressed && millis() - lastShotTime >= shotInterval) {
            projectileManager.addProjectile(rocket.getX(), rocket.getY());
            lastShotTime = millis();
        }

        // enemy defeat or player dies
        if (enemyManager.allEnemiesDefeated()) {
            displayGameOverScreen("YOU WIN!");
        } else if (rocket.getHealth() <= 0) {
            displayGameOverScreen("GAME OVER!");
        }
    }

    private void displayPauseScreen() {
        // pause screen
        fill(255);
        textSize(40);
        textAlign(CENTER, CENTER);
        text("Game Paused\nPress SPACE to Resume", width / 2, height / 2 + 20);
    }

    private void displayGameOverScreen(String message) {
        // game over
        fill(0, 0, 0, 150);
        rect(0, 0, width, height);
        fill(255);
        textSize(48);
        textAlign(CENTER, PApplet.CENTER);
        text(message, width / 2, height / 2 - 50);

        // restart and exit buttons
        Button restartButton = new Button(
                width / 2 - 100, height / 2 + 50, 200, 50, "RESTART?", color(0, 128, 0), color(0, 100, 0), this);
        Button exitButton = new Button(
                width / 2 - 100, height / 2 + 120, 200, 50, "EXIT", color(128, 0, 0), color(100, 0, 0), this);

        restartButton.setAction(() -> {
            resetGame();
            screenManager.setScreenState(3); // Go back to main menu screen
        });
        exitButton.setAction(() -> {
            resetGame();
            screenManager.setScreenState(0); // Go back to title screen
        });

        restartButton.display(this);
        exitButton.display(this);

        if (mousePressed) {
            restartButton.checkClick(this);
            exitButton.checkClick(this);
        }
    }

    private void resetGame() {
        // resetting game, reinitialize everything
        rocket = new Player(width / 2, height - 50);
        rocket.setLevel(level);
        enemyManager = new EnemyManager(this, level);
        projectileManager = new ProjectileManager();
        powerUpManager = new PowerUpManager(this);
        stars = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            stars.add(new Star(random(width), random(height), this));
        }
        screenManager.resetGame();
    }

    public void keyPressed() {
        // space bar pressed
        if (key == ' ') {
            isPaused = !isPaused;
        }

        // enter pressed
        if (key == ENTER && screenManager.getScreenState() == 0) {
            screenManager.setScreenState(3);
        }
    }

    // getter and setter for gamescreen manager
    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Main.level = level;
    }

    public static EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public static void setEnemyManager(EnemyManager enemyManager) {
        Main.enemyManager = enemyManager;
    }
} // end main class
