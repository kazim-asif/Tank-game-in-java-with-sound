package game;

import java.awt.event.*;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Gameplay extends JPanel implements ActionListener {
	brick br;

	private MusicPlayer backgroundMusicPlayer;
	private HealthPowerUp healthPowerUp;

	private boolean player1BulletFired = false;
	private boolean player2BulletFired = false;

	public static LinkedList<Integer> posBrickX;
	public static LinkedList<Integer> posBrickY;

	private Timer timer;
	private int delay = 8;

	private Player1Listener player1Listener;
	private Player2Listener player2Listener;

	private Player1Bullet player1Bullet = null;
	private Player2Bullet player2Bullet = null;

	private boolean play = true;

	public Player1Bullet getPlayer1Bullet() {
		return player1Bullet;
	}

	public void setPlayer1Bullet(Player1Bullet player1Bullet) {
		this.player1Bullet = player1Bullet;
	}

	public Player2Bullet getPlayer2Bullet() {
		return player2Bullet;
	}

	public void setPlayer2Bullet(Player2Bullet player2Bullet) {
		this.player2Bullet = player2Bullet;
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public Player1Listener getPlayer1Listener() {
		return player1Listener;
	}

	public void setPlayer1Listener(Player1Listener player1Listener) {
		this.player1Listener = player1Listener;
	}

	public Player2Listener getPlayer2Listener() {
		return player2Listener;
	}

	public void setPlayer2Listener(Player2Listener player2Listener) {
		this.player2Listener = player2Listener;
	}

	public Gameplay() {
		br = new brick();
		setFocusable(true);
		// addKeyListener(this);
		player1Listener = new Player1Listener(this);
		player2Listener = new Player2Listener(this);
		addKeyListener(player1Listener);
		addKeyListener(player2Listener);
		addKeyListener(player1Listener);
		addKeyListener(player2Listener);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

		MusicPlayer musicPlayer = new MusicPlayer(null);
		musicPlayer.playBackgroundMusic();

		posBrickX = convertIntArrayToLinkedList(br.bricksXPos);
		posBrickY = convertIntArrayToLinkedList(br.bricksYPos);

		// Start the background music player thread
		backgroundMusicPlayer = new MusicPlayer("/Tank2D/src/background_music.mp3");

		backgroundMusicPlayer.start();

		// Create the health power-up instance and set its initial position
		healthPowerUp = new HealthPowerUp(300, 100);
	}

	public static LinkedList<Integer> convertIntArrayToLinkedList(int[] array) {
		LinkedList<Integer> linkedList = new LinkedList<>();

		for (int value : array) {
			linkedList.add(value);
		}

		return linkedList;
	}

	public void paint(Graphics g) {

		Color blackishGreyColor = new Color(32, 33, 35);

		// play background
		g.setColor(blackishGreyColor);
		g.fillRect(0, 0, 800, 650);

		// right side background
		g.setColor(Color.red);
		g.fillRect(660, 0, 300, 700);

		// draw solid bricks
		br.drawSolids(this, g);

		// draw Breakable bricks
		br.draw(this, g);

		try {

			if (play) {

				// Check if healthPowerUp is not null before drawing it
				if (healthPowerUp != null) {
					healthPowerUp.draw(g);
				}

				// draw player 1
				if (player1Listener.isPlayer1up())
//					player1Listener.setPlayer1(new ImageIcon("Recourses\\player1_tank_up.png"));
					player1Listener.setPlayer1(new ImageIcon(getClass().getResource("/player1_tank_up.png")));
				else if (player1Listener.isPlayer1down())
//					player1Listener.setPlayer1(new ImageIcon("Recourses\\player1_tank_down.png"));
					player1Listener.setPlayer1(new ImageIcon(getClass().getResource("/player1_tank_down.png")));
				else if (player1Listener.isPlayer1right())
//					player1Listener.setPlayer1(new ImageIcon("Recourses\\player1_tank_right.png"));
					player1Listener.setPlayer1(new ImageIcon(getClass().getResource("/player1_tank_right.png")));

				else if (player1Listener.isPlayer1left())
//					player1Listener.setPlayer1(new ImageIcon("Recourses\\player1_tank_left.png"));
					player1Listener.setPlayer1(new ImageIcon(getClass().getResource("/player1_tank_left.png")));

				player1Listener.getPlayer1().paintIcon(this, g, player1Listener.getPlayer1X(),
						player1Listener.getPlayer1Y());

//			 draw player 2
				if (player2Listener.isPlayer2up())
//					player2Listener.setPlayer2(new ImageIcon("Recourses\\player2_tank_up.png"));
					player2Listener.setPlayer2(new ImageIcon(getClass().getResource("/player2_tank_up.png")));

				else if (player2Listener.isPlayer2down())
//					player2Listener.setPlayer2(new ImageIcon("Recourses\\player2_tank_down.png"));
					player2Listener.setPlayer2(new ImageIcon(getClass().getResource("/player2_tank_down.png")));

				else if (player2Listener.isPlayer2right())
//					player2Listener.setPlayer2(new ImageIcon("Recourses\\player2_tank_right.png"));
					player2Listener.setPlayer2(new ImageIcon(getClass().getResource("/player2_tank_right.png")));

				else if (player2Listener.isPlayer2left())
//					player2Listener.setPlayer2(new ImageIcon("Recourses\\player2_tank_left.png"));
					player2Listener.setPlayer2(new ImageIcon(getClass().getResource("/player2_tank_left.png")));

				player2Listener.getPlayer2().paintIcon(this, g, player2Listener.getPlayer2X(),
						player2Listener.getPlayer2Y());

				if (player1Bullet != null && player1Listener.isPlayer1Shoot()) {

					if (!player1BulletFired) {
						MusicPlayer musicPlayer = new MusicPlayer(null);
						musicPlayer.playBulletFiringSound(); // Play bullet firing sound when Player 1 fires a bullet
						player1BulletFired = true; // Mark that the sound has been played
					}

					if (player1Listener.getBulletShootDir1().equals("")) {
						if (player1Listener.isPlayer1up()) {
							player1Listener.setBulletShootDir1("up");
						} else if (player1Listener.isPlayer1down()) {
							player1Listener.setBulletShootDir1("down");
						} else if (player1Listener.isPlayer1right()) {
							player1Listener.setBulletShootDir1("right");
						} else if (player1Listener.isPlayer1left()) {
							player1Listener.setBulletShootDir1("left");
						}
					} else {
						player1Bullet.move(player1Listener.getBulletShootDir1());
						player1Bullet.draw(g);
						// If the bullet has not been fired, reset the player1BulletFired flag
						player1BulletFired = false;
					}
//
					if (new Rectangle(player1Bullet.getX(), player1Bullet.getY(), 10, 10).intersects(
							new Rectangle(player2Listener.getPlayer2X(), player2Listener.getPlayer2Y(), 50, 50))) {
						player1Listener.setPlayer1score(player1Listener.getPlayer1score() + 10);
						player2Listener.setPlayer2lives(player2Listener.getPlayer2lives() - 1);
						player1Bullet = null;
						player1Listener.setPlayer1Shoot(false);
						player1Listener.setBulletShootDir1("");
					}
//
					if (br.checkCollision(player1Bullet.getX(), player1Bullet.getY())
							|| br.checkSolidCollision(player1Bullet.getX(), player1Bullet.getY())) {

						// Play the collision sound when the bullet collides with the wall
						MusicPlayer.playCollisionSound();

						player1Bullet = null;
						player1Listener.setPlayer1Shoot(false);
						player1Listener.setBulletShootDir1("");
					}
//
					if (player1Bullet.getY() < 1 || player1Bullet.getY() > 580 || player1Bullet.getX() < 1
							|| player1Bullet.getX() > 630) {
						player1Bullet = null;
						player1Listener.setPlayer1Shoot(false);
						player1Listener.setBulletShootDir1("");

					}
				} else {
					// If the bullet has not been fired, reset the player1BulletFired flag
					player1BulletFired = false;
				}

				if (player2Bullet != null && player2Listener.isPlayer2Shoot()) {

					// Check if the bullet sound for player 2 has not been played yet
					if (!player2BulletFired) {
						MusicPlayer musicPlayer2 = new MusicPlayer(null);
						musicPlayer2.playBulletFiringSound(); // Play bullet firing sound when Player 2 fires a bullet
						player2BulletFired = true; // Mark that the sound has been played
					}

					if (player2Listener.getBulletShootDir2().equals("")) {
						if (player2Listener.isPlayer2up()) {
							player2Listener.setBulletShootDir2("up");
						} else if (player2Listener.isPlayer2down()) {
							player2Listener.setBulletShootDir2("down");
						} else if (player2Listener.isPlayer2right()) {
							player2Listener.setBulletShootDir2("right");
						} else if (player2Listener.isPlayer2left()) {
							player2Listener.setBulletShootDir2("left");
						}
					} else {
						player2Bullet.move(player2Listener.getBulletShootDir2());
						player2Bullet.draw(g);
						// If the bullet has not been fired, reset the player2BulletFired flag
						player2BulletFired = false;
					}

					if (new Rectangle(player2Bullet.getX(), player2Bullet.getY(), 10, 10).intersects(
							new Rectangle(player1Listener.getPlayer1X(), player1Listener.getPlayer1Y(), 50, 50))) {
						player2Listener.setPlayer2score(player2Listener.getPlayer2score() + 10);
						player1Listener.setPlayer1lives(player1Listener.getPlayer1lives() - 1);
						player2Bullet = null;
						player2Listener.setPlayer2Shoot(false);
						player2Listener.setBulletShootDir2("");
					}

					if (br.checkCollision(player2Bullet.getX(), player2Bullet.getY())
							|| br.checkSolidCollision(player2Bullet.getX(), player2Bullet.getY())) {

						// Play the collision sound when the bullet collides with the wall
						MusicPlayer.playCollisionSound();

						player2Bullet = null;
						player2Listener.setPlayer2Shoot(false);
						player2Listener.setBulletShootDir2("");

					}

					if (player2Bullet.getY() < 1 || player2Bullet.getY() > 580 || player2Bullet.getX() < 1
							|| player2Bullet.getX() > 630) {
						player2Bullet = null;
						player2Listener.setPlayer2Shoot(false);
						player2Listener.setBulletShootDir2("");
					}
				}

				else {
					// If the bullet has not been fired, reset the player2BulletFired flag
					player2BulletFired = false;
				}
			}

		} catch (Exception e) {
			System.out.println(">>>");
		}

		// the scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 20));
		g.drawString("Scores", 700, 30);
		g.drawString("Player 1:  " + player1Listener.getPlayer1score(), 670, 60);
		g.drawString("Player 2:  " + player2Listener.getPlayer2score(), 670, 90);

		g.drawString("Lives", 700, 150);
		g.drawString("Player 1:  " + player1Listener.getPlayer1lives(), 670, 180);
		g.drawString("Player 2:  " + player2Listener.getPlayer2lives(), 670, 230);

		// Draw Player 1 Life Bar
		int player1LifeX = 670; // X coordinate of the life bar for Player 1
		int player1LifeY = 190; // Y coordinate of the life bar for Player 1 (adjusted)
		int lifeBarWidth = 100; // Width of the life bar
		int lifeBarHeight = 20; // Height of the life bar
		int player1Lives = player1Listener.getPlayer1lives(); // Get the number of lives for Player 1

		g.setColor(Color.yellow); // Set the color of the life bar
		g.fillRect(player1LifeX, player1LifeY, lifeBarWidth, lifeBarHeight); // Draw the full life bar background

		g.setColor(Color.green); // Set the color of the remaining life
		int remainingLifeWidth = (int) ((double) lifeBarWidth * player1Lives / 5);
		g.fillRect(player1LifeX, player1LifeY, remainingLifeWidth, lifeBarHeight); // Draw the remaining life

		// Draw Player 2 Life Bar
		int player2LifeX = 670; // X coordinate of the life bar for Player 2
		int player2LifeY = 240; // Y coordinate of the life bar for Player 2 (adjusted)
		int player2Lives = player2Listener.getPlayer2lives(); // Get the number of lives for Player 2

		g.setColor(Color.yellow); // Set the color of the life bar
		g.fillRect(player2LifeX, player2LifeY, lifeBarWidth, lifeBarHeight); // Draw the full life bar background

		g.setColor(Color.green); // Set the color of the remaining life
		int remainingLifeWidth2 = (int) ((double) lifeBarWidth * player2Lives / 5);
		g.fillRect(player2LifeX, player2LifeY, remainingLifeWidth2, lifeBarHeight); // Draw the remaining life

		if (player1Listener.getPlayer1lives() == 0) {
			g.setColor(Color.yellow);
			g.setFont(new Font("serif", Font.BOLD, 60));
			g.drawString("Game Over", 200, 300);
			g.drawString("Player 2 Won", 180, 380);
			play = false;
			g.setColor(Color.yellow);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("(Enter to Restart)", 230, 430);
			healthPowerUp = new HealthPowerUp(300, 100);

		} else if (player2Listener.getPlayer2lives() == 0) {
			g.setColor(Color.yellow);
			g.setFont(new Font("serif", Font.BOLD, 60));
			g.drawString("Game Over", 200, 300);
			g.drawString("Player 1 Won", 180, 380);
			g.setColor(Color.yellow);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("(Enter to Restart)", 230, 430);
			healthPowerUp = new HealthPowerUp(300, 100);

			play = false;
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();

		// Check for collisions between player 1 and the health power-up
		if (play && healthPowerUp != null) {
			Rectangle player1Bounds = new Rectangle(player1Listener.getPlayer1X(), player1Listener.getPlayer1Y(), 50,
					50);
			Rectangle powerUpBounds = new Rectangle(healthPowerUp.getX(), healthPowerUp.getY(),
					HealthPowerUp.getPowerUpWidth(), HealthPowerUp.getPowerUpHeight());

			// Use the intersects method to check for overlap of bounding boxes for player 1
			if (player1Bounds.intersects(powerUpBounds)) {
				if (HealthPowerUp.applyEffect(player1Listener)) {
					// Power-up effect applied successfully, remove the power-up from the game
					healthPowerUp = null;
				}
			}
			resetHealthPowerUp();
		}

		// Check for collisions between player 2 and the health power-up
		if (play && healthPowerUp != null) {
			Rectangle player2Bounds = new Rectangle(player2Listener.getPlayer2X(), player2Listener.getPlayer2Y(), 50,
					50);
			Rectangle powerUpBounds = new Rectangle(healthPowerUp.getX(), healthPowerUp.getY(),
					HealthPowerUp.getPowerUpWidth(), HealthPowerUp.getPowerUpHeight());

			// Use the intersects method to check for overlap of bounding boxes for player 2
			if (player2Bounds.intersects(powerUpBounds)) {
				if (HealthPowerUp.applyEffect1(player2Listener)) {
					// Power-up effect applied successfully, remove the power-up from the game
					healthPowerUp = null;
				}
			}
			resetHealthPowerUp();

		}

		repaint();
	}

	private void resetHealthPowerUp() {
		// Set the health power-up to a new instance with its initial position
		healthPowerUp = new HealthPowerUp(300, 100);
	}
}
