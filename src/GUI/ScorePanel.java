package GUI;

import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Logic.Data;
import Logic.Score;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

public class ScorePanel extends JPanel {

	private static final long serialVersionUID = 3314869205445631279L;
	private JPanel pnTitulo;
	private JLabel lblPuntuaciones;
	private JPanel pnVolver;
	private JPanel pnPuntos;
	private JLabel lblVolver;
	private Window parent;

	public ScorePanel(Window w) {
		parent = w;

		setBackground(Color.BLACK);
		this.setSize(Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
		setLayout(new BorderLayout(0, 0));
		add(getPnTitulo(), BorderLayout.NORTH);
		add(getPnVolver(), BorderLayout.SOUTH);
		add(getPnPuntos(), BorderLayout.CENTER);

		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Out");
		this.getActionMap().put("Out", new OutGame());
	}

	public void cargaScores(Score sc) {
		pnPuntos.removeAll();

		for (int i = 9; i >= 0; i--) {
			JLabel temp = new JLabel(
					10 - i + ".\t" + sc.getScores()[i].getPlayer() + "\t\t" + sc.getScores()[i].getPuntos());
			temp.setFont(Data.FONT_SCORES);
			temp.setHorizontalAlignment(SwingConstants.CENTER);
			temp.setForeground(Data.FONT_COLOR);
			pnPuntos.add(temp);
		}

	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.BLACK);
			pnTitulo.add(getLblPuntuaciones());
		}
		return pnTitulo;
	}

	private JLabel getLblPuntuaciones() {
		if (lblPuntuaciones == null) {
			lblPuntuaciones = new JLabel("Puntuaciones");
			lblPuntuaciones.setForeground(Data.FONT_COLOR);
			lblPuntuaciones.setFont(Data.FONT_INIT);
		}
		return lblPuntuaciones;
	}

	private JPanel getPnVolver() {
		if (pnVolver == null) {
			pnVolver = new JPanel();
			pnVolver.setBackground(Color.BLACK);
			pnVolver.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnVolver.add(getLblVolver());
		}
		return pnVolver;
	}

	private JPanel getPnPuntos() {
		if (pnPuntos == null) {
			pnPuntos = new JPanel();
			pnPuntos.setBackground(Color.BLACK);
			pnPuntos.setLayout(new GridLayout(10, 1, 0, 0));
		}
		return pnPuntos;
	}

	private JLabel getLblVolver() {
		if (lblVolver == null) {
			lblVolver = new JLabel("Volver");
			lblVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					parent.returnInitPanel();
				}
			});
			lblVolver.setHorizontalAlignment(SwingConstants.LEFT);
			lblVolver.setForeground(Data.FONT_COLOR);
			lblVolver.setFont(Data.FONT);
		}
		return lblVolver;
	}

	private class OutGame extends AbstractAction {
		private static final long serialVersionUID = 5083645117895335534L;

		@Override
		public void actionPerformed(ActionEvent e) {
			parent.returnInitPanel();
		}

	}
}
