package GameListeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import GUI.Window;

public class EndGameListener extends KeyAdapter{

	Window parent;

	public EndGameListener(Window w) {
		parent = w;
	}

	public void keyPressed(KeyEvent e) {

		if(parent.getGamePane().finEnemies || parent.getGamePane().finPlayer) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				parent.resetGamePanel();
			}
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				parent.returnInitPanel();
			}
		}
	}

}
