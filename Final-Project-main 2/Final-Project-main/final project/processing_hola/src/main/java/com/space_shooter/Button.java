/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The button class
 * manages buttons, including their position, appearance, interaction, and associated actions.
 */

package com.space_shooter;

import processing.core.PApplet;

public class Button {
    private float x, y, width, height; // position and size of button
    private String label; // text displayed on the button
    private int normalColor, hoverColor; // color
    private boolean isHovered = false; // hovering
    private Runnable action; // button clicked

    // constructor
    public Button(float x, float y, float width, float height, String label, int normalColor, int hoverColor,
            PApplet app) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.normalColor = normalColor; // default button color
        this.hoverColor = hoverColor; // color when the button is hovered over
    }

    // action when button is clicked
    public void setAction(Runnable action) {
        this.action = action;
    }

    //
    public void setY(float y) {
        this.y = y;
    }

    // displaying button
    public void display(PApplet app) {
        // mouse over button
        isHovered = app.mouseX > x && app.mouseX < x + width && app.mouseY > y && app.mouseY < y + height;

        // fill if hovered
        app.fill(isHovered ? hoverColor : normalColor);
        app.rect(x, y, width, height); // button is rect

        app.fill(255); // white
        app.textAlign(PApplet.CENTER, PApplet.CENTER); // center
        app.textSize(24); // text size
        app.text(label, x + width / 2, y + height / 2);
    }

    // button, click, execute
    public void checkClick(PApplet app) {
        if (isHovered && app.mousePressed && action != null) {
            action.run(); // run
        }
    }
} // end button
