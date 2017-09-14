package GameListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GamePanel;

public class TimerListener implements ActionListener{

	GamePanel gui;
	
	public TimerListener(GamePanel panel) {
		this.gui = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gui.refreshGui();
	}

}
