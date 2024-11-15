package com.space_shooter ;

import processing.core.PApplet ;
import java.util.ArrayList ;

public class GameScreenManager 
{
    private int screenState = 0; // title, countdown, game
    private int countdownStartTime;
    private boolean showText = true;
    private int lastToggleTime = 0;
    private String countdownText = "";
    private ArrayList<Star> stars;
    private PApplet app;

    public GameScreenManager(PApplet app, ArrayList<Star> stars) {
        this.app = app;
        this.stars = stars;
    } // end GameScreenManager

    public void displayTitleScreen() {
        for (Star star : stars) {
            star.update(app);
            star.display(app);
        } // end for

        app.fill(255);
        app.textSize(32);
        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.text("Welcome to Space Shooter!", app.width / 2, app.height / 2 - 40);

        if (app.millis() - lastToggleTime > 500) {
            showText = !showText;
            lastToggleTime = app.millis();
        } // end if

        if (showText) {
            app.text("Press ENTER to Start", app.width / 2, app.height / 2 + 40);
        } // end if
    } // end diplayTitleScreen

public void startCountdown () 
    {
        countdownStartTime = app.millis () ;
        screenState = 1 ;
    } // end startCountdown

public void displayCountdown() 
    {
        int elapsed = app.millis () - countdownStartTime ; 
        app.fill (255, 255, 0) ;
        app.textSize (48) ;
        app.textAlign (PApplet.CENTER, PApplet.CENTER) ;

        if (elapsed < 1000) 
            {
                countdownText = "Starting in 3" ;
            } // end if
        else if (elapsed < 2000) 
            {
                countdownText = "Starting in 2" ;
            } 
        else if (elapsed < 3000) 
            {
                countdownText = "Starting in 1" ;
            } 
        else 
            {
                screenState = 2 ; // transition game screen
            }
        app.text (countdownText, app.width / 2, app.height / 2) ;
    } // end displayCountDown

public int getScreenState () 
    {
        return screenState ;
    } // end getScreenState

public void setScreenState (int state) 
    {
        screenState = state ;
    } // end setScreenState
} // end GameScreenManager class
