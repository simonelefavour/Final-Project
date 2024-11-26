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
    private Button goBackButton; // button to go back to the title screen
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
        levelButtons.add(new Button(app.width / 2 - 100, app.height / 2, 200, 50, "LEVEL 1", app.color(0, 128, 0),
                app.color(0, 100, 0), app));
        levelButtons.add(new Button(app.width / 2 - 100, app.height / 2 + 70, 200, 50, "LEVEL 2", app.color(0, 128, 0),
                app.color(0, 100, 0), app));
        levelButtons.add(new Button(app.width / 2 - 100, app.height / 2 + 140, 200, 50, "LEVEL 3", app.color(0, 128, 0),
                app.color(0, 100, 0), app));

        // level button
        levelButtons.get(0).setAction(() -> {
            Main.setLevel(1); // 1
            rocket.setLevel(Main.getLevel());
            Main.setEnemyManager(new EnemyManager(app, Main.getLevel()));
            startCountdown();
        });

        levelButtons.get(1).setAction(() -> {
            Main.setLevel(2); // 2
            rocket.setLevel(Main.getLevel());
            Main.setEnemyManager(new EnemyManager(app, Main.getLevel()));
            startCountdown();
        });

        levelButtons.get(2).setAction(() -> {
            Main.setLevel(3); // 3
            rocket.setLevel(Main.getLevel()); //
            Main.setEnemyManager(new EnemyManager(app, Main.getLevel()));
            startCountdown();
        });

        // stars on screen, not title
        backgroundStars = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            backgroundStars.add(new BackgroundStar(app.random(app.width), app.random(app.height), app));
        }

        // go back
        goBackButton = new Button(20, app.height - 70, 150, 50, "GO BACK", app.color(255), app.color(200), app);
        goBackButton.setAction(() -> screenState = 0);
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
            app.text("SELECT LEVEL", app.width / 2, app.height / 2 - 100);
        }

        // display levels and click
        for (Button button : levelButtons) {
            button.display(app);
            button.checkClick(app);
        }

        // display and click
        goBackButton.display(app);
        goBackButton.checkClick(app);
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
        screenState = 2; // Set to main menu screen
        stars = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            stars.add(new Star(app.random(app.width), app.random(app.height), app));
        }
    }
} // end game screen manager
