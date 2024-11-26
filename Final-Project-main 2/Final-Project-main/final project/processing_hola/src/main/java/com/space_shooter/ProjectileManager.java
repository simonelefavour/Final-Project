/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The projectile manager class
 * handles managing all projectiles, including adding, updating, displaying, and removing them.
 */

package com.space_shooter;

import processing.core.PApplet;
import java.util.Iterator; // credit to chat.gpt because it kept freezing when projectile left screen 
import java.util.LinkedList;

public class ProjectileManager {
    private LinkedList<Projectile> projectiles = new LinkedList<>(); // active projectiles

    // new projectile
    public void addProjectile(float x, float y) {
        projectiles.add(new Projectile(x, y));
    }

    // getter
    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }

    // removing projectile from list
    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    // update, display and remove if out of screen
    public void updateAndDisplay(PApplet app, EnemyManager enemyManager, ScoreManager scoreManager) {

        // credit to chat.gpt because it kept freezing when projectile left screen
        Iterator<Projectile> iterator = projectiles.iterator(); // iterator

        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.update(app);
            projectile.display(app);

            // removal
            if (projectile.isOutOfBounds(app)) {
                iterator.remove(); // removes using iterator
            }
        }
    }
} // end projectile manager class
