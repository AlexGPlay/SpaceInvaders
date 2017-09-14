package Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Score implements Serializable{

	private static final long serialVersionUID = -4335181499033907767L;
	private ScorePair[] scores;
	File f;

	public Score() {
		scores = new ScorePair[10];
	}

	public Score(File f) {
		this();
		this.f = f;
		
		try {
			if(!f.exists()) {
				f.createNewFile();
				generateInitialScores();
			}

			else {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);

				Score temp = (Score)ois.readObject();
				this.scores = temp.getScores();

				fis.close();
				ois.close();

			} 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void generateInitialScores() {
		int base = 1000;
		String userBase = "Jimmy";
		
		for(int i=0;i<10;i++) {
			ScorePair temp = new ScorePair(userBase,base*(i+1));
			scores[i] = temp; 
		}
		
		saveAll();
	}
	
	public void saveAll() {
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream ous = new ObjectOutputStream(fos);
			ous.writeObject(this);
			
			ous.close();
			fos.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ScorePair[] getScores() {
		return scores;
	}

	public int newScore(int puntuacion) {

		int i=0;

		if(!(puntuacion>scores[0].getPuntos()))
			return -1;

			while(i<scores.length && puntuacion>scores[i].getPuntos())
				i++;

			for(int x=1;x<i;x++)
				scores[x-1] = scores[x];

			return i-1;
	}

	public void addScore(String player, int score, int pos) {
		scores[pos] = new ScorePair(player,score);
		saveAll();
	}

	public class ScorePair implements Serializable{

		private static final long serialVersionUID = -5023823838618225742L;
		private String player;
		private int puntuacion;

		public ScorePair(String player, int puntos) {
			this.player = player;
			this.puntuacion = puntos;
		}

		public String getPlayer() {
			return player;
		}

		public int getPuntos() {
			return puntuacion;
		}

	}

}
