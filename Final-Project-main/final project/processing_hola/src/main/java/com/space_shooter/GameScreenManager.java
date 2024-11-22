/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The game screen manager has all the screens including title, main menu, countdown, etc. It displays the transitions and make sure everything runs smoothly 
 */

package com.space_shooter;

import processing.core.PApplet;
import processing.core.PFont;

import java.util.ArrayList;

public class GameScreenManager {
    private int screenState = 0; // title, countdown, main
    private int countdownStartTime;
    private boolean showText = true;
    private int lastToggleTime = 0;
    private String countdownText = "";
    private ArrayList<Star> stars;
    private ArrayList<BackgroundStar> backgroundStars; // for twinkling stars
    private PApplet app;
    private ArrayList<Button> levelButtons; // level
    private Button goBackButton; // go back

    private PFont byteBounceFont; // font

    // constructor
    public GameScreenManager(PApplet app, ArrayList<Star> stars) {
        this.app = app;
        this.stars = stars;

        byteBounceFont = app.createFont("ByteBounce.ttf", 32); // title

        // level buttons
        levelButtons = new ArrayList<>();
        levelButtons.add(new Button(
                app.width / 2 - 100, app.height / 2, 200, 50, "LEVEL 1",
                app.color(0, 128, 0), app.color(0, 100, 0), app));
        levelButtons.add(new Button(
                app.width / 2 - 100, app.height / 2 + 70, 200, 50, "LEVEL 2",
                app.color(0, 128, 0), app.color(0, 100, 0), app));
        levelButtons.add(new Button(
                app.width / 2 - 100, app.height / 2 + 140, 200, 50, "LEVEL 3",
                app.color(0, 128, 0), app.color(0, 100, 0), app));

        // actions for level buttons
        levelButtons.get(0).setAction(this::startCountdown);
        levelButtons.get(1).setAction(this::startCountdown);
        levelButtons.get(2).setAction(this::startCountdown);

        // background stars
        backgroundStars = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            backgroundStars.add(new BackgroundStar(app.random(app.width), app.random(app.height), app));
        }

        // go back button
        goBackButton = new Button(
                20, app.height - 70, 150, 50, "GO BACK",
                app.color(255), app.color(200), app);
        goBackButton.setAction(() -> screenState = 0); // title screen
    }

    public int getScreenState() {
        return screenState;
    }

    public void setScreenState(int state) {
        screenState = state;
    }

    // blinking text
    public void displayTitleScreen() {
        for (Star star : stars) {
            star.update(app); // update stars
            star.display(app); // display stars
        }

        app.textFont(byteBounceFont);
        app.fill(255);
        app.textSize(80); // increase font
        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.text("WELCOME TO SPACE SHOOTER!!", app.width / 2, app.height / 2 - 40);

        if (app.millis() - lastToggleTime > 500) {
            showText = !showText;
            lastToggleTime = app.millis();
        }

        if (showText) {
            app.textSize(40); // press enter
            app.text("Press ENTER to Start", app.width / 2, app.height / 2 + 40);
        }
    }

    // countdown
    public void startCountdown() {
        countdownStartTime = app.millis();
        screenState = 1; // transition to countdown screen
    }

    // display the countdown screen
    public void displayCountdown() {
        for (BackgroundStar star : backgroundStars) {
            star.update();
            star.display();
        }

        int elapsed = app.millis() - countdownStartTime;
        app.fill(255, 255, 0); // yellow color for countdown
        app.textSize(60);
        app.textAlign(PApplet.CENTER, PApplet.CENTER);

        if (elapsed < 1000) {
            countdownText = "STARTING IN 3";
        } else if (elapsed < 2000) {
            countdownText = "STARTING IN 2";
        } else if (elapsed < 3000) {
            countdownText = "STARTING IN 1";
        } else if (elapsed < 4000) {
            countdownText = "GO!";
        } else {
            screenState = 2; // transition to game screen after countdown
        }
        app.text(countdownText, app.width / 2, app.height / 2);
    }

    // display the main menu with buttons
    public void displayMainMenu() {
        for (BackgroundStar star : backgroundStars) {
            star.update();
            star.display();
        }

        if (app.millis() - lastToggleTime > 500) {
            showText = !showText;
            lastToggleTime = app.millis();
        }

        if (showText) {
            app.fill(255);
            app.textSize(55);
            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.text("SELECT LEVEL", app.width / 2, app.height / 2 - 150);
        }

        // display level selection buttons
        float buttonHeight = 70;
        float buttonSpacing = 20;
        float totalButtonHeight = buttonHeight * 3 + buttonSpacing * 2;
        float buttonYStart = (app.height / 2) - (totalButtonHeight / 2) + 50;

        levelButtons.get(0).setY(buttonYStart);
        levelButtons.get(1).setY(buttonYStart + buttonHeight + buttonSpacing);
        levelButtons.get(2).setY(buttonYStart + (buttonHeight + buttonSpacing) * 2);

        for (Button button : levelButtons) {
            button.display(app); // display button
            button.checkClick(app); // check if button is clicked
        }

        goBackButton.display(app); // display "go back" button
        goBackButton.checkClick(app); // check if button is clicked
    }

    public void displayGameScreen() {
        for (BackgroundStar star : backgroundStars) {
            star.update();
            star.display();
        }
    }
}
