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
    public void updateAndDisplay(PApplet app, Player player, ScoreManager scoreManager,
            GameScreenManager screenManager) {
        // Spawn a new power-up if enough time has passed
        if (app.millis() - lastSpawnTime >= spawnInterval) {
            spawnRandomPowerUp(app);
            lastSpawnTime = app.millis();
        }

        // Iterate through active power-ups
        for (int i = powerUps.size() - 1; i >= 0; i--) {
            PowerUp powerUp = powerUps.get(i);
            powerUp.update(app);
            powerUp.display(app);

            // Check for collisions with the player
            if (PApplet.dist(player.getX(), player.getY(), powerUp.getX(), powerUp.getY()) < 20) {
                applyPowerUpEffect(powerUp, player, scoreManager);

                // Trigger glow effect based on the type of power-up
                if (powerUp.getType().equals("health")) {
                    screenManager.triggerPowerUpGlow(app.color(0, 255, 0)); // Green for health
                } else if (powerUp.getType().equals("rapid-fire")) {
                    screenManager.triggerPowerUpGlow(app.color(0, 0, 255)); // Blue for rapid fire
                } else if (powerUp.getType().equals("score")) {
                    screenManager.triggerPowerUpGlow(app.color(255, 255, 0)); // Yellow for score
                }

                powerUps.remove(i); // Remove the collected power-up
                continue; // Skip to the next power-up
            }

            // Remove power-ups that move out of bounds
            if (powerUp.isOutOfBounds(app)) {
                powerUps.remove(i); // Remove the out-of-bounds power-up
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
                player.setHealth(player.getHealth() + 1); // Increase health by 1
                break;
            case "rapid-fire":
                player.activateRapidFire(app.millis()); // Activate rapid fire effect
                break;
            case "score":
                scoreManager.increaseScore(5);
                break;
        }
    }

} // end power up manager class
