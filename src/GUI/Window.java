package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GameListeners.EndGameListener;
import Logic.Data;
import Logic.Score;

public class Window extends JFrame {

	private static final long serialVersionUID = -725658883325246365L;

	private JPanel contentPane;
	private GamePanel gamePane;
	private EndGameListener endGame;
	private InitPanel panelPrincipal;
	private ScorePanel scorePanel;
	private Score score;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		initScore();
		setTitle("Lo k ase l avurrimiento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.add(getPanelPrincipal());

		this.revalidate();
		this.repaint();
	}

	public void initScore() {
		score = new Score(new File(Data.SCORE_FILE));

	}

	public ScorePanel getScorePanel() {
		if (scorePanel == null) {
			scorePanel = new ScorePanel(this);
		}

		return scorePanel;
	}

	public GamePanel getGamePane() {
		if (gamePane == null) {
			gamePane = new GamePanel(score, this);
		}

		return gamePane;
	}

	public InitPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			panelPrincipal = new InitPanel(this);
		}

		return panelPrincipal;
	}

	public void returnInitPanel() {
		this.removeKeyListener(endGame);
		contentPane.removeAll();

		contentPane.add(panelPrincipal);

		contentPane.revalidate();
		contentPane.repaint();
	}

	public void swapScores() {
		this.removeKeyListener(endGame);
		contentPane.removeAll();

		getScorePanel().cargaScores(score);
		contentPane.add(scorePanel);

		contentPane.revalidate();
		contentPane.repaint();
	}

	public void resetGamePanel() {
		GamePanel tempPanel = new GamePanel(score, this);
		// MainShipListener tempListener = new
		// MainShipListener(tempPanel.getMainShip());
		EndGameListener tempEnd = new EndGameListener(this);

		contentPane.removeAll();

		contentPane.add(tempPanel);
		// this.addKeyListener(tempListener);
		this.addKeyListener(tempEnd);

		gamePane = tempPanel;
		// shipMovement = tempListener;
		endGame = tempEnd;

		contentPane.revalidate();
		contentPane.repaint();
	}

}
