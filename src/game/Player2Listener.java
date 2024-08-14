package game;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Player2Listener implements KeyListener {
	private Gameplay gameplay;

	private ImageIcon player2;
	private int player2X = 600;
	private int player2Y = 550;
	private boolean player2right = false;
	private boolean player2left = false;
	private boolean player2down = false;
	private boolean player2up = true;
	private int player2score = 0;
	private int player2lives = 5;
	private boolean player2Shoot = false;
	private String bulletShootDir2 = "";

	 public Player2Listener(Gameplay gameplay) {
	        this.gameplay = gameplay;
	    }

	public Gameplay getGameplay() {
		return gameplay;
	}

	public void setGameplay(Gameplay gameplay) {
		this.gameplay = gameplay;
	}

	public ImageIcon getPlayer2() {
		return player2;
	}

	public void setPlayer2(ImageIcon player2) {
		this.player2 = player2;
	}

	public int getPlayer2X() {
		return player2X;
	}

	public void setPlayer2X(int player2x) {
		player2X = player2x;
	}

	public int getPlayer2Y() {
		return player2Y;
	}

	public void setPlayer2Y(int player2y) {
		player2Y = player2y;
	}

	public boolean isPlayer2right() {
		return player2right;
	}

	public void setPlayer2right(boolean player2right) {
		this.player2right = player2right;
	}

	public boolean isPlayer2left() {
		return player2left;
	}

	public void setPlayer2left(boolean player2left) {
		this.player2left = player2left;
	}

	public boolean isPlayer2down() {
		return player2down;
	}

	public void setPlayer2down(boolean player2down) {
		this.player2down = player2down;
	}

	public boolean isPlayer2up() {
		return player2up;
	}

	public void setPlayer2up(boolean player2up) {
		this.player2up = player2up;
	}

	public int getPlayer2score() {
		return player2score;
	}

	public void setPlayer2score(int player2score) {
		this.player2score = player2score;
	}

	public int getPlayer2lives() {
		return player2lives;
	}

	public void setPlayer2lives(int player2lives) {
		this.player2lives = player2lives;
	}

	public boolean isPlayer2Shoot() {
		return player2Shoot;
	}

	public void setPlayer2Shoot(boolean player2Shoot) {
		this.player2Shoot = player2Shoot;
	}

	public String getBulletShootDir2() {
		return bulletShootDir2;
	}

	public void setBulletShootDir2(String bulletShootDir2) {
		this.bulletShootDir2 = bulletShootDir2;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_M) {
			if (!player2Shoot) {
				if (player2up) {
					gameplay.setPlayer2Bullet(new Player2Bullet(player2X + 20, player2Y));
				} else if (player2down) {
					gameplay.setPlayer2Bullet(new Player2Bullet(player2X + 20, player2Y + 40));
				} else if (player2right) {
					gameplay.setPlayer2Bullet(new Player2Bullet(player2X + 40, player2Y + 20));
				} else if (player2left) {
					gameplay.setPlayer2Bullet(new Player2Bullet(player2X, player2Y + 20));
				}

				player2Shoot = true;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			
			boolean b = false;
			for (int i = 0; i < gameplay.br.solidBricksXPos.length; i++) {

				if (new Rectangle(gameplay.br.solidBricksXPos[i], gameplay.br.solidBricksYPos[i], 40, 40)
						.intersects(new Rectangle(player2X, (player2Y - 10), 50, 50))) {
					b = true;
					break;
				}
				b = false;
			}

			if (b == false) {
				for (int i = 0; i < Gameplay.posBrickX.size(); i++) {

					if (new Rectangle(Gameplay.posBrickX.get(i), Gameplay.posBrickY.get(i), 40, 40)
							.intersects(new Rectangle(player2X, (player2Y - 10), 50, 50))) {
						b = true;
						break;
					}
					b = false;
				}
			}

			player2right = false;
			player2left = false;
			player2down = false;
			player2up = true;
			if (!b) {
				if (!(player2Y < 10))
					player2Y -= 10;
			} else {
				if (!(player2Y < 10))
					player2Y += 0;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {


			boolean b = false;
			for (int i = 0; i < gameplay.br.solidBricksXPos.length; i++) {

				if (new Rectangle(gameplay.br.solidBricksXPos[i], gameplay.br.solidBricksYPos[i], 40, 40)
						.intersects(new Rectangle((player2X - 10), player2Y, 50, 50))) {
					b = true;
					break;
				}
				b = false;
			}

			if (b == false) {
				for (int i = 0; i < Gameplay.posBrickX.size(); i++) {

					if (new Rectangle(Gameplay.posBrickX.get(i), Gameplay.posBrickY.get(i), 40, 40)
							.intersects(new Rectangle((player2X - 10), player2Y, 50, 50))) {
						b = true;
						break;
					}
					b = false;
				}
			}


			player2right = false;
			player2left = true;
			player2down = false;
			player2up = false;
			if (!b) {
				if (!(player2X < 10))
					player2X -= 10;
			} else {
				if (!(player2X < 10))
					player2X += 10;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			boolean b = false;
			for (int i = 0; i < gameplay.br.solidBricksXPos.length; i++) {

				if (new Rectangle(gameplay.br.solidBricksXPos[i], gameplay.br.solidBricksYPos[i], 40, 40)
						.intersects(new Rectangle(player2X, (player2Y + 10), 50, 50))) {
					b = true;
					break;
				}
				b = false;
			}

			if (b == false) {
				for (int i = 0; i < Gameplay.posBrickX.size(); i++) {

					if (new Rectangle(Gameplay.posBrickX.get(i), Gameplay.posBrickY.get(i), 40, 40)
							.intersects(new Rectangle(player2X, (player2Y + 10), 50, 50))) {
						b = true;
						break;
					}
					b = false;
				}
			}


			player2right = false;
			player2left = false;
			player2down = true;
			player2up = false;
			if (!b) {
				if (!(player2Y > 540))
					player2Y += 10;
			} else {
				if (!(player2Y > 540))
					player2Y -= 10;
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			boolean b = false;
			for (int i = 0; i < gameplay.br.solidBricksXPos.length; i++) {

				if (new Rectangle(gameplay.br.solidBricksXPos[i], gameplay.br.solidBricksYPos[i], 40, 40)
						.intersects(new Rectangle((player2X + 10), player2Y, 50, 50))) {
					b = true;
					break;
				}
				b = false;
			}

			if (b == false) {
				for (int i = 0; i < Gameplay.posBrickX.size(); i++) {

					if (new Rectangle(Gameplay.posBrickX.get(i), Gameplay.posBrickY.get(i), 40, 40)
							.intersects(new Rectangle((player2X + 10), player2Y, 50, 50))) {
						b = true;
						break;
					}
					b = false;
				}
			}

			player2right = true;
			player2left = false;
			player2down = false;
			player2up = false;
			if (!b) {
				if (!(player2X > 590))
					player2X += 10;
			} else {
				if (!(player2X > 590))
					player2X -= 10;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
	}
}
