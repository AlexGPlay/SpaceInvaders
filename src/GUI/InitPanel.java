package GUI;

import javax.swing.JPanel;

import Logic.Data;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InitPanel extends JPanel {

	private static final long serialVersionUID = 213335578636993962L;

	private JPanel pnTitulo;
	private JLabel lblTitulo;
	private JPanel pnAcciones;
	private JPanel pnCreditos;
	private JLabel lblCreditos;
	private JLabel lblJugar;
	private JLabel lblPuntuaciones;
	private JLabel lblSalir;
	private Window parent;

	/**
	 * Create the panel.
	 */
	public InitPanel(Window w) {
		parent = w;

		setBackground(Color.BLACK);
		this.setSize(Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT);
		setLayout(new BorderLayout(0, 0));
		add(getPnTitulo(), BorderLayout.NORTH);
		add(getPnAcciones(), BorderLayout.CENTER);
		add(getPnCreditos(), BorderLayout.SOUTH);
	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.BLACK);
			pnTitulo.add(getLblTitulo());
		}
		return pnTitulo;
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Space Invaders");
			lblTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblTitulo.setFont(Data.FONT_WASTED);
			lblTitulo.setForeground(Data.FONT_COLOR);
		}
		return lblTitulo;
	}

	private JPanel getPnAcciones() {
		if (pnAcciones == null) {
			pnAcciones = new JPanel();
			pnAcciones.setBorder(new EmptyBorder(10, 0, 10, 0));
			pnAcciones.setBackground(Color.BLACK);
			GridBagLayout gbl_pnAcciones = new GridBagLayout();
			gbl_pnAcciones.columnWidths = new int[] { 600, 0 };
			gbl_pnAcciones.rowHeights = new int[] { 152, 152, 152, 0 };
			gbl_pnAcciones.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
			gbl_pnAcciones.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			pnAcciones.setLayout(gbl_pnAcciones);
			GridBagConstraints gbc_lblJugar = new GridBagConstraints();
			gbc_lblJugar.anchor = GridBagConstraints.SOUTH;
			gbc_lblJugar.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblJugar.insets = new Insets(0, 0, 5, 0);
			gbc_lblJugar.gridx = 0;
			gbc_lblJugar.gridy = 0;
			pnAcciones.add(getLblJugar(), gbc_lblJugar);
			GridBagConstraints gbc_lblPuntuaciones = new GridBagConstraints();
			gbc_lblPuntuaciones.fill = GridBagConstraints.BOTH;
			gbc_lblPuntuaciones.insets = new Insets(0, 0, 5, 0);
			gbc_lblPuntuaciones.gridx = 0;
			gbc_lblPuntuaciones.gridy = 1;
			pnAcciones.add(getLblPuntuaciones(), gbc_lblPuntuaciones);
			GridBagConstraints gbc_lblSalir = new GridBagConstraints();
			gbc_lblSalir.anchor = GridBagConstraints.NORTH;
			gbc_lblSalir.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblSalir.gridx = 0;
			gbc_lblSalir.gridy = 2;
			pnAcciones.add(getLblSalir(), gbc_lblSalir);
		}
		return pnAcciones;
	}

	private JPanel getPnCreditos() {
		if (pnCreditos == null) {
			pnCreditos = new JPanel();
			pnCreditos.setBackground(Color.BLACK);
			pnCreditos.add(getLblCreditos());
		}
		return pnCreditos;
	}

	private JLabel getLblCreditos() {
		if (lblCreditos == null) {
			lblCreditos = new JLabel("Poias en binagre edition");
			lblCreditos.setFont(Data.FONT_CREDITS);
			lblCreditos.setForeground(Data.FONT_COLOR);
		}
		return lblCreditos;
	}

	private JLabel getLblJugar() {
		if (lblJugar == null) {
			lblJugar = new JLabel("Jugar");
			lblJugar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					parent.resetGamePanel();
				}
			});
			lblJugar.setHorizontalAlignment(SwingConstants.CENTER);
			lblJugar.setFont(Data.FONT_INIT);
			lblJugar.setForeground(Data.FONT_COLOR);
		}
		return lblJugar;
	}

	private JLabel getLblPuntuaciones() {
		if (lblPuntuaciones == null) {
			lblPuntuaciones = new JLabel("Puntuaciones");
			lblPuntuaciones.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					parent.swapScores();
				}
			});
			lblPuntuaciones.setHorizontalAlignment(SwingConstants.CENTER);
			lblPuntuaciones.setFont(Data.FONT_INIT);
			lblPuntuaciones.setForeground(Data.FONT_COLOR);
		}
		return lblPuntuaciones;
	}

	private JLabel getLblSalir() {
		if (lblSalir == null) {
			lblSalir = new JLabel("Salir");
			lblSalir.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.exit(0);
				}
			});
			lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
			lblSalir.setFont(Data.FONT_INIT);
			lblSalir.setForeground(Data.FONT_COLOR);
		}
		return lblSalir;
	}
}
