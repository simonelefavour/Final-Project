package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {
    private Player rocket; // player object
    private Enemy enemy; // enemy object
    private ArrayList<Star> stars = new ArrayList<>(); // stars displayed
    private GameScreenManager screenManager; // manages game screens
    private ScoreManager scoreManager; // manages scoring
    private ProjectileManager projectileManager; // manages projectiles
    private int lastShotTime = 0; // timer for shooting interval
    private int shotInterval = 200; // time bt each shot in milliseconds

    public static void main(String[] args) {
        PApplet.main("com.space_shooter.Main");
    } // end main

    public void settings() {
        fullScreen();
    } // end settings

    public void setup() {
        rocket = new Player(width / 2, height - 50); // initialize player
        enemy = new Enemy(width / 2, 50); // initialize enemy

        // stars on title screen
        for (int i = 0; i < 150; i++) {
            stars.add(new Star(random(width), random(height), this));
        } // end for

        screenManager = new GameScreenManager(this, stars); // initialize screen manager
        scoreManager = new ScoreManager(); // initialize score manager
        projectileManager = new ProjectileManager(); // initialize projectile manager
    } // end setup

    public void draw() {
        background(0); // black background

        // managing the screens
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
        } // end switch
    } // end draw

    private void displayGameScreen() {
        rocket.update(this); // update player
        rocket.display(this); // display player
        enemy.update(this); // update enemy
        enemy.display(this); // display enemy

        // shooting with interval time
        if (mousePressed && millis() - lastShotTime >= shotInterval) {
            projectileManager.addProjectile(rocket.getX(), rocket.getY());
            lastShotTime = millis();
        } // end if

        // updating projectiles and managing collision
        projectileManager.updateAndDisplay(this, enemy, scoreManager);

        // score and health
        scoreManager.displayScore(this, rocket.getHealth());

        // win/lose
        if (enemy.getHealth() <= 0) {
            displayEndMessage("You Win!");
        } // end if
        else if (rocket.getHealth() <= 0) {
            displayEndMessage("Game Over!");
        } // end else if
    } // end displayGameScreen

    private void displayEndMessage(String message) {
        fill(255);
        textSize(48);
        textAlign(CENTER, CENTER);
        text(message, width / 2, height / 2);
        noLoop();
    } // end displayEndMessage

    public void keyPressed() {
        // countdown when enter pressed
        if (key == ENTER && screenManager.getScreenState() == 0) {
            screenManager.startCountdown();
        } // end if
    } // wnd keyPressed
} // end class main
