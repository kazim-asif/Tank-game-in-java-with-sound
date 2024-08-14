package game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Player1Listener implements KeyListener {
	private Gameplay gameplay;

	private ImageIcon player1;
	private int player1X = 0;
	private int player1Y = 550;
	private boolean player1right = false;
	private boolean player1left = false;
	private boolean player1down = false;
	private boolean player1up = true;
	private int player1score = 0;
	private int player1lives = 5;
	private boolean player1Shoot = false;
	private String bulletShootDir1 = "";

	public Player1Listener(Gameplay gameplay) {
		this.gameplay = gameplay;
	}

	public Gameplay getGamepFlay() {
		return gameplay;
	}

	public void setGameplay(Gameplay gameplay) {
		this.gameplay = gameplay;
	}

	public ImageIcon getPlayer1() {
		return player1;
	}

	public void setPlayer1(ImageIcon player1) {
		this.player1 = player1;
	}

	public int getPlayer1X() {
		return player1X;
	}

	public void setPlayer1X(int player1x) {
		player1X = player1x;
	}

	public int getPlayer1Y() {
		return player1Y;
	}

	public void setPlayer1Y(int player1y) {
		player1Y = player1y;
	}

	public boolean isPlayer1right() {
		return player1right;
	}

	public void setPlayer1right(boolean player1right) {
		this.player1right = player1right;
	}

	public boolean isPlayer1left() {
		return player1left;
	}

	public void setPlayer1left(boolean player1left) {
		this.player1left = player1left;
	}

	public boolean isPlayer1down() {
		return player1down;
	}

	public void setPlayer1down(boolean player1down) {
		this.player1down = player1down;
	}

	public boolean isPlayer1up() {
		return player1up;
	}

	public void setPlayer1up(boolean player1up) {
		this.player1up = player1up;
	}

	public int getPlayer1score() {
		return player1score;
	}

	public void setPlayer1score(int player1score) {
		this.player1score = player1score;
	}

	public int getPlayer1lives() {
		return player1lives;
	}

	public void setPlayer1lives(int player1lives) {
		this.player1lives = player1lives;
	}

	public boolean isPlayer1Shoot() {
		return player1Shoot;
	}

	public void setPlayer1Shoot(boolean player1Shoot) {
		this.player1Shoot = player1Shoot;
	}

	public String getBulletShootDir1() {
		return bulletShootDir1;
	}

	public void setBulletShootDir1(String bulletShootDir1) {
		this.bulletShootDir1 = bulletShootDir1;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER
				&& (player1lives == 0 || gameplay.getPlayer2Listener().getPlayer2lives() == 0)) {
			gameplay.br = new brick();
			player1X = 0;
			player1Y = 550;
			player1right = false;
			player1left = false;
			player1down = false;
			player1up = true;

			gameplay.getPlayer2Listener().setPlayer2X(600);
			gameplay.getPlayer2Listener().setPlayer2Y(550);
			gameplay.getPlayer2Listener().setPlayer2right(false);
			gameplay.getPlayer2Listener().setPlayer2left(false);
			gameplay.getPlayer2Listener().setPlayer2down(false);
			gameplay.getPlayer2Listener().setPlayer2up(true);

			player1score = 0;
			player1lives = 5;
			gameplay.getPlayer2Listener().setPlayer2score(0);
			gameplay.getPlayer2Listener().setPlayer2lives(5);
			gameplay.setPlay(true);
			gameplay.repaint();
		}

		if (e.getKeyCode() == KeyEvent.VK_U) {
			if (!player1Shoot) {
				if (player1up) {
					gameplay.setPlayer1Bullet(new Player1Bullet(player1X + 20, player1Y));
				} else if (player1down) {
					gameplay.setPlayer1Bullet(new Player1Bullet(player1X + 20, player1Y + 40));
				} else if (player1right) {
					gameplay.setPlayer1Bullet(new Player1Bullet(player1X + 40, player1Y + 20));
				} else if (player1left) {
					gameplay.setPlayer1Bullet(new Player1Bullet(player1X, player1Y + 20));
				}

				player1Shoot = true;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {

			boolean b = false;
			for (int i = 0; i < gameplay.br.solidBricksXPos.length; i++) {

				if (new Rectangle(gameplay.br.solidBricksXPos[i], gameplay.br.solidBricksYPos[i], 40, 40)
						.intersects(new Rectangle(player1X, (player1Y - 10), 50, 50))) {
					b = true;
					break;
				}
				b = false;
			}

			if (b == false) {
				for (int i = 0; i < Gameplay.posBrickX.size(); i++) {

					if (new Rectangle(Gameplay.posBrickX.get(i), Gameplay.posBrickY.get(i), 40, 40)
							.intersects(new Rectangle(player1X, (player1Y - 10), 50, 50))) {
						b = true;
						break;
					}
					b = false;
				}
			}

			player1right = false;
			player1left = false;
			player1down = false;
			player1up = true;

			if (!b) {

				if (!(player1Y < 10))
					player1Y -= 10;
			} else {
				if (!(player1Y < 10))
					player1Y += 10;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {

			boolean b = false;
			for (int i = 0; i < gameplay.br.solidBricksXPos.length; i++) {

				if (new Rectangle(gameplay.br.solidBricksXPos[i], gameplay.br.solidBricksYPos[i], 40, 40)
						.intersects(new Rectangle((player1X - 10), player1Y, 50, 50))) {
					b = true;
					break;
				}
				b = false;
			}

			if (b == false) {
				for (int i = 0; i < Gameplay.posBrickX.size(); i++) {

					if (new Rectangle(Gameplay.posBrickX.get(i), Gameplay.posBrickY.get(i), 40, 40)
							.intersects(new Rectangle((player1X - 10), player1Y, 50, 50))) {
						b = true;
						break;
					}
					b = false;
				}
			}

			player1right = false;
			player1left = true;
			player1down = false;
			player1up = false;

			if (!b) {
				if (!(player1X < 10))
					player1X -= 10;
			} else {
				if (!(player1X < 10))
					player1X += 10;
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_S) {

			boolean b = false;
			for (int i = 0; i < gameplay.br.solidBricksXPos.length; i++) {

				if (new Rectangle(gameplay.br.solidBricksXPos[i], gameplay.br.solidBricksYPos[i], 40, 40)
						.intersects(new Rectangle(player1X, (player1Y + 10), 50, 50))) {
					b = true;
					break;
				}
				b = false;
			}

			if (b == false) {
				for (int i = 0; i < Gameplay.posBrickX.size(); i++) {

					if (new Rectangle(Gameplay.posBrickX.get(i), Gameplay.posBrickY.get(i), 40, 40)
							.intersects(new Rectangle(player1X, (player1Y + 10), 50, 50))) {
						b = true;
						break;
					}
					b = false;
				}
			}
			player1right = false;
			player1left = false;
			player1down = true;
			player1up = false;

			if (!b) {
				if (!(player1Y > 540))
					player1Y += 10;
			} else {
				if (!(player1Y > 540))
					player1Y -= 10;
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_D) {

			boolean b = false;
			for (int i = 0; i < gameplay.br.solidBricksXPos.length; i++) {

				if (new Rectangle(gameplay.br.solidBricksXPos[i], gameplay.br.solidBricksYPos[i], 40, 40)
						.intersects(new Rectangle((player1X + 10), player1Y, 50, 50))) {
					b = true;
					break;
				}
				b = false;
			}

			if (b == false) {
				for (int i = 0; i < Gameplay.posBrickX.size(); i++) {

					if (new Rectangle(Gameplay.posBrickX.get(i), Gameplay.posBrickY.get(i), 40, 40)
							.intersects(new Rectangle((player1X + 10), player1Y, 50, 50))) {
						b = true;
						break;
					}
					b = false;
				}
			}
			
			player1right = true;
			player1left = false;
			player1down = false;
			player1up = false;

			if (!b) {
				if (!(player1X > 590))
					player1X += 10;
			} else {
				if (!(player1X > 590))
					player1X -= 10;
			}

		}

	}

	public void keyReleased(KeyEvent e) {
	}
}