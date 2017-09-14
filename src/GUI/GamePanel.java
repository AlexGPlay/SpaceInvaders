package GUI;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import GameListeners.TimerListener;
import Logic.Data;
import Logic.EnemyShip;
import Logic.GameController;
import Logic.Obstacle;
import Logic.Score;
import Logic.Ship;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 5376897475837769163L;

	GameController controller;
	Ship mainShip;
	Timer timer;
	public boolean finPlayer;
	public boolean finEnemies;
	Score score;
	int pressed;
	boolean paused;
	Window parent;

	public GamePanel(Score score, Window w) {
		parent = w;
		controller = new GameController(score);
		mainShip = controller.getMainShip();
		this.score = score;

		setBackground(Color.BLACK);
		setSize(new Dimension(Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT));
		setLayout(null);

		getTimer().start();
		finPlayer = false;
		finEnemies = false;

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "Left");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "Right");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(' '), "Fire");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Pause");

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released D"), "Stop");
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released A"), "Stop");

		this.getActionMap().put("Fire", new FireAction());
		this.getActionMap().put("Right", new MoveAction(Data.MAINSHIP_MOVEMENT));
		this.getActionMap().put("Left", new MoveAction(-Data.MAINSHIP_MOVEMENT));
		this.getActionMap().put("Pause", new PauseGame());

		this.getActionMap().put("Stop", new StopMove());

		pressed = 0;
		paused = false;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Pintar nave principal
		g.setColor(Data.MAINSHIP_COLOR);
		// g.fillRect(mainShip.x, mainShip.y, Data.MAINSHIP_WIDTH,
		// Data.MAINSHIP_HEIGTH);
		Image ship = scaleImage(Data.MAINSHIP_WIDTH, Data.MAINSHIP_HEIGTH, Data.MAINSHIP_FILE);
		g.drawImage(ship, mainShip.x, mainShip.y, null);

		// Pintar disparos
		g.setColor(Data.SHOT_COLOR);
		for (int i = 0; i < mainShip.getActualShoots(); i++)
			g.fillRect(mainShip.getShoots()[i].x, mainShip.getShoots()[i].y, Data.SHOT_WIDTH, Data.SHOT_LENGTH);

		// Pintar enemigos
		try {
			Image image = scaleImage(Data.ENEMYSHIP_DIAMETER, Data.ENEMYSHIP_DIAMETER, Data.ENEMYSHIP_FILE);

			g.setColor(Data.ENEMYSHIP_COLOR);
			for (int i = 0; i < controller.getEnemies().length; i++)
				if (!controller.getEnemies()[i].isDead())
					g.drawImage(image, controller.getEnemies()[i].x, controller.getEnemies()[i].y, null);
			// g.fillOval(controller.getEnemies()[i].x,
			// controller.getEnemies()[i].y, Data.ENEMYSHIP_DIAMETER,
			// Data.ENEMYSHIP_DIAMETER);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Pintar disparos enemigos
		g.setColor(Data.ENEMYSHIP_SHOT);
		for (EnemyShip temp : controller.getEnemies())
			for (int i = 0; i < temp.getActualShoots(); i++)
				g.fillRect(temp.getShoots()[i].x, temp.getShoots()[i].y, Data.SHOT_WIDTH, Data.SHOT_LENGTH);

		// Pintar barreras
		Image imageB = scaleImage(Data.BARRIER_WIDTH, Data.BARRIER_HEIGTH, Data.OBJECT_FILE);
		g.setColor(Data.BARRIER_COLOR);
		for (Obstacle temp : controller.getBarriers())
			if (temp.getLife() > 0)
				g.drawImage(imageB, temp.x, temp.y, null);
		// g.fillRect(temp.x, temp.y, Data.BARRIER_WIDTH, Data.BARRIER_HEIGTH);

		// Pintar datos juego
		g.setColor(Data.FONT_COLOR);
		g.setFont(Data.FONT);
		g.drawString("Vida: " + mainShip.getLives(), 20, Data.WINDOW_HEIGHT - 30);
		g.drawString("Nivel: " + controller.getLevel(), 80, Data.WINDOW_HEIGHT - 30);
		g.drawString("Puntos: " + controller.getScore(), 150, Data.WINDOW_HEIGHT - 30);

		// Pintar wasted

		if (finPlayer) {
			g.setColor(Data.FONT_COLOR);
			g.setFont(Data.FONT_WASTED);
			g.drawString("WASTED", Data.WINDOW_HEIGHT / 2 - 40 * 3, Data.WINDOW_HEIGHT / 2);
			g.setFont(Data.FONT_AGAIN);
			g.drawString("Pulsa espacio para jugar", Data.WINDOW_HEIGHT / 2 - 40 * 3, Data.WINDOW_HEIGHT / 2 + 30);

			altaPuntuacion();
		}

		// Pausa
		if (paused) {
			g.setColor(Data.FONT_COLOR);
			g.setFont(Data.FONT_WASTED);
			g.drawString("PAUSA", Data.WINDOW_HEIGHT / 2 - 40 * 3, Data.WINDOW_HEIGHT / 2);
			g.setFont(Data.FONT_AGAIN);
			g.drawString("Pulsa espacio para jugar", Data.WINDOW_HEIGHT / 2 - 40 * 3, Data.WINDOW_HEIGHT / 2 + 30);
			g.drawString("O escape para salir", Data.WINDOW_HEIGHT / 2 - 40 * 3, Data.WINDOW_HEIGHT / 2 + 60);
		}

	}

	public void altaPuntuacion() {
		int i = controller.checkHighScore();

		if (i != -1) {
			String nombre = JOptionPane.showInputDialog("Introduce tu nombre: ");
			controller.addScore(nombre, i);
		}

	}

	public void refreshGui() {
		if (!controller.playerAlive()) {
			timer.stop();
			finPlayer = true;
		}

		controller.refreshShip();
		this.repaint();
	}

	public Ship getMainShip() {
		return mainShip;
	}

	public Timer getTimer() {

		if (timer == null) {
			timer = new Timer(10, new TimerListener(this));
		}

		return timer;
	}

	public void pauseGame() {

		if (!paused) {
			timer.stop();
			this.repaint();
			paused = !paused;
		}

		else if (paused) {
			parent.returnInitPanel();
		}

	}

	public BufferedImage scaleImage(int WIDTH, int HEIGHT, String filename) {
		BufferedImage bi = null;
		try {
			ImageIcon ii = new ImageIcon(filename);// path to image
			bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(
					new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bi;
	}

	private void unPause() {
		timer.start();
		this.repaint();
		paused = !paused;
	}

	private class FireAction extends AbstractAction {

		private static final long serialVersionUID = -552584246660586823L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!paused) {
				mainShip.shoot();
			}
			if (paused) {
				unPause();
			}
		}

	}

	private class MoveAction extends AbstractAction {

		private static final long serialVersionUID = 5242226669059338951L;
		int movement;

		public MoveAction(int mov) {
			movement = mov;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!paused) {
				controller.setMovement(movement);
				pressed++;
			}
		}

	}

	private class StopMove extends AbstractAction {

		private static final long serialVersionUID = -7966580223430789315L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!paused) {
				if (pressed > 0)
					pressed--;

				if (pressed == 0)
					controller.setMovement(0);
			}
		}

	}

	private class PauseGame extends AbstractAction {
		private static final long serialVersionUID = 5083645117895335534L;

		@Override
		public void actionPerformed(ActionEvent e) {
			pauseGame();
		}

	}

}
