package com.space_shooter;

import processing.core.PApplet;
import java.util.ArrayList;

public class PowerUpManager {
    private ArrayList<PowerUp> powerUps = new ArrayList<>();
    private int spawnInterval = 5000; // Spawn every 5 seconds
    private long lastSpawnTime = 0;

    public PowerUpManager(PApplet app) {
        // Spawn initial power-ups
        spawnRandomPowerUp(app);
    }

    public void updateAndDisplay(PApplet app, Player player, ScoreManager scoreManager) {
        if (app.millis() - lastSpawnTime >= spawnInterval) {
            spawnRandomPowerUp(app);
            lastSpawnTime = app.millis();
        }

        for (PowerUp powerUp : powerUps) {
            powerUp.update(app);
            powerUp.display(app);

            // Check for collisions with the player
            if (PApplet.dist(player.getX(), player.getY(), powerUp.getX(), powerUp.getY()) < 20) {
                applyPowerUpEffect(powerUp, player, scoreManager);
                powerUps.remove(powerUp);
                break;
            }

            // Remove power-ups that move out of bounds
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
                // Decrease shooting interval temporarily
                break;
            case "score":
                scoreManager.increaseScore(50);
                break;
        }
    }
}
