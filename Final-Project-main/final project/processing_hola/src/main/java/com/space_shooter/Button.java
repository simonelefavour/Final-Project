package com.space_shooter;

import processing.core.PApplet;

public class Button {
    private float x, y, width, height; // position and size of button
    private String label; // text displayed on the button
    private int normalColor, hoverColor, textColor; // colors
    private boolean isHovered = false; // hovering
    private Runnable action; // button clicked

    // Constructor with default white text
    public Button(float x, float y, float width, float height, String label, int normalColor, int hoverColor,
            PApplet app) {
        this(x, y, width, height, label, normalColor, hoverColor, app.color(255), app); // Default text color: white
    }

    // Constructor with customizable text color
    public Button(float x, float y, float width, float height, String label, int normalColor, int hoverColor,
            int textColor,
            PApplet app) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.normalColor = normalColor; // Default button color
        this.hoverColor = hoverColor; // Color when hovered
        this.textColor = textColor; // Text color
    }

    // Action when button is clicked
    public void setAction(Runnable action) {
        this.action = action;
    }

    // Displaying button
    public void display(PApplet app) {
        // Mouse over button
        isHovered = app.mouseX > x && app.mouseX < x + width && app.mouseY > y && app.mouseY < y + height;

        // Fill if hovered
        app.fill(isHovered ? hoverColor : normalColor);
        app.rect(x, y, width, height); // Draw button

        // Set text color
        app.fill(textColor);
        app.textAlign(PApplet.CENTER, PApplet.CENTER); // Center text
        app.textSize(24); // Text size
        app.text(label, x + width / 2, y + height / 2);
    }

    // Button click handler
    public void checkClick(PApplet app) {
        if (isHovered && app.mousePressed && action != null) {
            action.run(); // Run the action
        }
    }
}
