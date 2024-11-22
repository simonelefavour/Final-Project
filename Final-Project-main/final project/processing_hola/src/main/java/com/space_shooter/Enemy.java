/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The enemy class deals with the enemy and it's behavior. 
 */

package com.space_shooter;

import processing.core.PApplet;

public class Enemy extends GameObject {
    private int health = 50; // enemy health 

    // constructor 
    public Enemy(float x, float y) {
        super(x, y); //
    }

    // position and behavior 
    @Override
    public void update(PApplet app) {
        
    }

    // red squARE
    @Override
    public void display(PApplet app) {
        app.fill(255, 0, 0); // Rred 
        app.rectMode(PApplet.CENTER);
        app.rect(x, y, 30, 30); 
    }

    //  enemy's health
    public int getHealth() {
        return health;
    }

    // decrease enemy health when it takes damage
    public void takeDamage() {
        if (health > 0)
            health -= 10; // Decrease health by 10
    }
}
