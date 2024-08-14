package game;

import java.awt.*;

public class HealthPowerUp extends GameObject {
    private static final int POWER_UP_WIDTH = 20;
    private static final int POWER_UP_HEIGHT = 20;

    public HealthPowerUp(int x, int y) {
        super(x, y, POWER_UP_WIDTH, POWER_UP_HEIGHT);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    public static int getPowerUpWidth() {
        return POWER_UP_WIDTH;
    }

    public static int getPowerUpHeight() {
        return POWER_UP_HEIGHT;
    }

    // Add any additional behavior or effects for the health power-up here
    public static boolean applyEffect(Player1Listener player1Listener) {
        int currentLives = player1Listener.getPlayer1lives();
        int maxLives = 5; // Adjust this based on the maximum number of lives the player can have

        if (currentLives < maxLives) {
            int newLives = Math.min(currentLives + 1, maxLives);
            player1Listener.setPlayer1lives(newLives);
            return true; // Effect applied successfully
        }

        return false; // Effect not applied as player already has max lives
    }
    
 // Add any additional behavior or effects for the health power-up here
    public static boolean applyEffect1(Player2Listener player2Listener) {
        int currentLives = player2Listener.getPlayer2lives();
        int maxLives = 5; // Adjust this based on the maximum number of lives the player can have

        if (currentLives < maxLives) {
            int newLives = Math.min(currentLives + 1, maxLives);
            player2Listener.setPlayer2lives(newLives);
            return true; // Effect applied successfully
        }

        return false; // Effect not applied as player already has max lives
    }
}
