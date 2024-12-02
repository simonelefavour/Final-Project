/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The game screen manager class
 * manages the various game screens, including the title screen, countdown, main menu, and game screen.
 */

package com.space_shooter;

import processing.core.PApplet;
import processing.core.PFont;
import java.util.ArrayList;

public class GameScreenManager {
    private int screenState = 0; // title, countdown, main
    private int countdownStartTime; // time when countdown starts
    private boolean showText = true; // blinking text effect
    private int lastToggleTime = 0;
    private String countdownText = ""; // text displayed during countdown
    private ArrayList<Star> stars; // list of stars for the title screen
    private ArrayList<BackgroundStar> backgroundStars; // list of stars for other screens
    private ArrayList<Button> levelButtons; // list of buttons for level selection
    private boolean isFlashing = false;
    private long flashStartTime = 0;
    private int flashDuration = 100;

    private Button howToPlayButton; // "How to Play" button
    private Button backButton; // Back button for instructions
    private Button goBackButton; // button to go back to the title screen

    private boolean isPowerUpGlow = false;
    private long powerUpGlowStartTime = 0;
    private int powerUpGlowDuration = 300; // Glow duration in milliseconds
    private int currentGlowColor; // Stores the current glow color

    private PFont byteBounceFont;
    private PApplet app;

    // references to the player, level, and enemy manager
    private Player rocket;
    private int level;
    private EnemyManager enemyManager;

    // constructor
    public GameScreenManager(PApplet app, ArrayList<Star> stars, Player rocket, int level, EnemyManager enemyManager) {
        this.app = app;
        this.stars = stars;
        this.rocket = rocket; // reference to the player's rocket
        this.level = level; // reference to the level
        this.enemyManager = enemyManager; // reference to the enemy manager
        byteBounceFont = app.createFont("ByteBounce.ttf", 32);

        // level buttons
        levelButtons = new ArrayList<>();
        levelButtons.add(new Button(app.width / 2 - 100, app.height / 2 - 50, 200, 50, "LEVEL 1",
                app.color(0, 128, 0), app.color(0, 100, 0), app));
        levelButtons.add(new Button(app.width / 2 - 100, app.height / 2 + 20, 200, 50, "LEVEL 2",
                app.color(0, 128, 0), app.color(0, 100, 0), app));
        levelButtons.add(new Button(app.width / 2 - 100, app.height / 2 + 90, 200, 50, "LEVEL 3",
                app.color(0, 128, 0), app.color(0, 100, 0), app));

        // level button
        levelButtons.get(0).setAction(() -> {
            Main.setLevel(1); // Set Level 1
            rocket.setLevel(Main.getLevel());
            Main.setEnemyManager(new EnemyManager(app, Main.getLevel(), this)); // Include screenManager
            startCountdown();
        });

        levelButtons.get(1).setAction(() -> {
            Main.setLevel(2); // Set Level 2
            rocket.setLevel(Main.getLevel());
            Main.setEnemyManager(new EnemyManager(app, Main.getLevel(), this)); // Include screenManager
            startCountdown();
        });

        levelButtons.get(2).setAction(() -> {
            Main.setLevel(3); // Set Level 3
            rocket.setLevel(Main.getLevel());
            Main.setEnemyManager(new EnemyManager(app, Main.getLevel(), this)); // Include screenManager
            startCountdown();
        });

        // stars on screen, not title
        backgroundStars = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            backgroundStars.add(new BackgroundStar(app.random(app.width), app.random(app.height), app));
        }

        // "How to Play" button
        howToPlayButton = new Button(app.width / 2 - 100, app.height / 2 + 160, 200, 50, "HOW TO PLAY",
                app.color(255, 255, 0), app.color(200, 200, 0), app.color(0), app);
        howToPlayButton.setAction(() -> screenState = 4); // Go to instructions page

        // "Go Back" button for the level main menu
        goBackButton = new Button(20, app.height - 70, 150, 50, "GO BACK",
                app.color(255), app.color(200), app.color(0), app); // Black text
        goBackButton.setAction(() -> screenState = 0); // Back to title screen

        // "Back" button for the instructions page
        backButton = new Button(20, app.height - 70, 150, 50, "BACK",
                app.color(255), app.color(200), app.color(0), app); // Black text
        backButton.setAction(() -> screenState = 3); // Back to main menu
    }

    // get current state
    public int getScreenState() {
        return screenState;
    }

    // set current state
    public void setScreenState(int state) {
        screenState = state;
    }

    // display
    public void displayTitleScreen() {
        // Reinitialize stars if empty
        if (stars.isEmpty()) {
            for (int i = 0; i < 150; i++) {
                stars.add(new Star(app.random(app.width), app.random(app.height), app));
            }
        }

        for (Star star : stars) {
            star.update(app);
            star.display(app);
        }

        // font and welcome
        app.textFont(byteBounceFont);
        app.fill(255);
        app.textSize(80);
        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.text("WELCOME TO SPACE SHOOTER!!", app.width / 2, app.height / 2 - 40);

        // blink
        if (app.millis() - lastToggleTime > 500) {
            showText = !showText;
            lastToggleTime = app.millis();
        }

        if (showText) {
            app.textSize(40);
            app.text("Press ENTER to Start", app.width / 2, app.height / 2 + 40);
        }
    }

    // countdown
    public void startCountdown() {
        countdownStartTime = app.millis();
        screenState = 1;
    }

    // background for countdown
    public void displayCountdown() {

        for (BackgroundStar star : backgroundStars) {
            star.update();
            star.display();
        }

        int elapsed = app.millis() - countdownStartTime;
        app.fill(255, 255, 0);
        app.textSize(60);
        app.textAlign(PApplet.CENTER, PApplet.CENTER);

        // update text
        if (elapsed < 1000) {
            countdownText = "STARTING IN 3";
        } else if (elapsed < 2000) {
            countdownText = "STARTING IN 2";
        } else if (elapsed < 3000) {
            countdownText = "STARTING IN 1";
        } else if (elapsed < 4000) {
            countdownText = "GO!";
        } else {
            screenState = 2; // game screen
        }
        app.text(countdownText, app.width / 2, app.height / 2);
    }

    // level selection
    public void displayMainMenu() {
        // stars
        for (BackgroundStar star : backgroundStars) {
            star.update();
            star.display();
        }

        // blink
        if (app.millis() - lastToggleTime > 500) {
            showText = !showText;
            lastToggleTime = app.millis();
        }

        if (showText) {
            app.fill(255);
            app.textSize(55);
            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.text("SELECT LEVEL", app.width / 2, app.height / 2 - 120);
        }

        // display levels and click
        for (Button button : levelButtons) {
            button.display(app);
            button.checkClick(app);
        }

        // display and click
        goBackButton.display(app);
        goBackButton.checkClick(app);

        // Display and check "How to Play" button
        howToPlayButton.display(app);
        howToPlayButton.checkClick(app);

        // Prevent lingering mouse clicks from affecting other buttons
        if (app.mousePressed) {
            app.mousePressed = false;
        }

    }

    public void displayInstructions() {
        // Stars in the background
        for (BackgroundStar star : backgroundStars) {
            star.update();
            star.display();
        }

        // Instructions text
        app.fill(255);
        app.textSize(30);
        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.text("HOW TO PLAY:", app.width / 2, app.height / 2 - 100);
        app.text("- Hold the left mouse button to shoot projectiles.", 50, 150);
        app.text("- Collect power-ups for bonuses:", 50, 200);
        app.text("  * Green: Restores health.", 100, 250);
        app.text("  * Blue: Activates rapid fire.", 100, 300);
        app.text("  * Yellow: Adds points to your score.", 100, 350);
        app.text("- Defeat all enemies to win!", 50, 400);
        app.text("- Avoid enemy projectiles to survive.", 50, 450);

        // "Back" button
        backButton.display(app);
        backButton.checkClick(app);

        // Prevent lingering mouse clicks from affecting other buttons
        if (app.mousePressed) {
            app.mousePressed = false;
        }
    }

    // main screen with stars
    public void displayGameScreen() {

        for (BackgroundStar star : backgroundStars) {
            star.update();
            star.display();
        }
    }

    // restart game - go back to main menu
    public void resetGame() {
        screenState = 3; // Directly set to the main menu
        countdownStartTime = 0; // Reset countdown timing
        countdownText = ""; // Clear any leftover countdown text

        // Reinitialize stars for the title screen
        stars = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            stars.add(new Star(app.random(app.width), app.random(app.height), app));
        }

        // Reset the player's rocket and level
        rocket.setLevel(1); // Default to level 1
        rocket.restoreHealth(100); // Reset player's health

        // Reset the enemy manager for a fresh start
        enemyManager = new EnemyManager(app, 1, this);

        // Ensure all other screen components (buttons, etc.) are ready
        lastToggleTime = 0; // Reset blinking text timing
        showText = true; // Ensure blinking text is shown
    }

    public void triggerFlash() {
        isFlashing = true;
        flashStartTime = app.millis();
    }

    public void displayFlash(float x, float y) {
        if (isFlashing) {
            app.noStroke();
            app.fill(255, 50, 50, 120); // Subtle red glow with reduced opacity
            app.ellipse(x, y, 100, 100); // Small circle around the player's position

            if (app.millis() - flashStartTime > flashDuration) {
                isFlashing = false; // End flash
            }
        }
    }

    // Trigger power-up glow
    public void triggerPowerUpGlow(int color) {
        isPowerUpGlow = true;
        powerUpGlowStartTime = app.millis();
        currentGlowColor = color;
    }

    // Display power-up glow
    public void displayPowerUpGlow(float x, float y) {
        if (isPowerUpGlow) {
            app.fill(currentGlowColor, 100); // Glow color with transparency
            app.noStroke();
            app.rect(x - 30, y - 30, 60, 60, 15); // Glow effect around player

            // End glow after duration
            if (app.millis() - powerUpGlowStartTime > powerUpGlowDuration) {
                isPowerUpGlow = false;
            }
        }
    }
} // end game screen manager
