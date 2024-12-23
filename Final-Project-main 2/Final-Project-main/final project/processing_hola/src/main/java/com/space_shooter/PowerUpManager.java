/* Coder: Simone LeFavour
 * Date: Nov. 26, 2024
 * Description: Final Project for Creative Computation III. Space Shooter game. The power up manager class
 * handles spawning, updating, and managing power-up items, including their interactions with the player.
 */

package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;

public class PowerUpManager {
    private ArrayList<PowerUp> powerUps = new ArrayList<>(); // active power ups
    private int spawnInterval = 7000; // every 7 seconds
    private long lastSpawnTime = 0;
    private PApplet app;

    public PowerUpManager(PApplet app) {
        this.app = app;
        spawnRandomPowerUp(app);
    }

    // update and display power ups
    public void updateAndDisplay(PApplet app, Player player, ScoreManager scoreManager) {
        if (app.millis() - lastSpawnTime >= spawnInterval) {
            spawnRandomPowerUp(app);
            lastSpawnTime = app.millis();
        }

        for (PowerUp powerUp : powerUps) {
            powerUp.update(app);
            powerUp.display(app);

            // check for collisions with the player
            if (PApplet.dist(player.getX(), player.getY(), powerUp.getX(), powerUp.getY()) < 20) {
                applyPowerUpEffect(powerUp, player, scoreManager);
                powerUps.remove(powerUp);
                break;
            }

            // remove power-ups that move out of bounds
            if (powerUp.isOutOfBounds(app)) {
                powerUps.remove(powerUp);
                break;
            }
        }
    }

    private void spawnRandomPowerUp(PApplet app) {
        String[] types = { "health", "rapid-fire", "score" };
        String type = types[(int) app.random(types.length)];
        powerUps.add(new PowerUp(app.random(50, app.width - 50), -10, type));
    }

    private void applyPowerUpEffect(PowerUp powerUp, Player player, ScoreManager scoreManager) {
        switch (powerUp.getType()) {
            case "health":
                player.restoreHealth(20);
                break;
            case "rapid-fire":
                player.activateRapidFire(app.millis()); // activate rapid fire effect
                break;
            case "score":
                scoreManager.increaseScore(50);
                break;
        }
    }
} // end power up manager class
