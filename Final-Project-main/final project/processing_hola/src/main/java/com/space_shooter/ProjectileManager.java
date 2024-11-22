/* Coder: Simone LeFavour
 * Date: Nov. 21, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The projectile manager class deals with the projectiles being shot from the rocket.
 */

package com.space_shooter;

import processing.core.PApplet;
import java.util.LinkedList;

public class ProjectileManager {
    private LinkedList<Projectile> projectiles = new LinkedList<>(); // linked list

    // new projectile
    public void addProjectile(float x, float y) {
        projectiles.add(new Projectile(x, y)); // create and add
    }

    // update and display projectiles
    public void updateAndDisplay(PApplet app, Enemy enemy, ScoreManager scoreManager) {
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile p = projectiles.get(i);
            p.update(app); // projectile position
            p.display(app); // display

            // removing projectile
            if (p.isOutOfBounds()) {
                projectiles.remove(i);
            } else if (p.collidesWith(enemy, app)) {
                scoreManager.increaseScore(10); // increase score
                enemy.takeDamage(); // decrease enemy health
                projectiles.remove(i); // remove projectile after collision
            }
        }
    }
}
