/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The button class deals with all the buttons in the code. 
 */

package com.space_shooter;

import processing.core.PApplet;

public class Button {
    private float x, y, width, height;
    private String label;
    private int normalColor, hoverColor;
    private boolean isHovered = false;
    private Runnable action; // runnable

    public Button(float x, float y, float width, float height, String label, int normalColor, int hoverColor,
            PApplet app) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.normalColor = normalColor; // default
        this.hoverColor = hoverColor; // color when hovered
    }

    public void setAction(Runnable action) {
        this.action = action; // set action
    }

    public void setY(float y) {
        this.y = y; // button position
    }

    public void display(PApplet app) {
        // mouse hover
        isHovered = app.mouseX > x && app.mouseX < x + width && app.mouseY > y && app.mouseY < y + height;
        app.fill(isHovered ? hoverColor : normalColor);
        app.rect(x, y, width, height);

        app.fill(255); // white text
        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.textSize(24);
        app.text(label, x + width / 2, y + height / 2);
    }

    public void checkClick(PApplet app) {
        if (isHovered && app.mousePressed && action != null) {
            action.run(); // execution
        }
    }
}