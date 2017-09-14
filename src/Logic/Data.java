package Logic;

import java.awt.Color;
import java.awt.Font;
import java.util.function.Predicate;

public abstract class Data {
	
	// Window Data
	public static int WINDOW_WIDTH = 600;
	public static int WINDOW_HEIGHT = 600;
	
	// Main Ship data
	public static Color MAINSHIP_COLOR = Color.WHITE;
	public static int MAINSHIP_WIDTH = 50;
	public static int MAINSHIP_HEIGTH = 30;
	public static int MAINSHIP_MOVEMENT = 5;
	public static int MAINSHIP_LIFE = 2;
	
	// Main Ship predicates
	public static Predicate<Integer> MAINSHIP_RIGHTMOVE_CHECK = x -> x<Data.WINDOW_WIDTH-Data.MAINSHIP_WIDTH;
	public static Predicate<Integer> MAINSHIP_LEFTMOVE_CHECK = x -> x>0;
	
	// Shot data
	public static int SHOT_SPEED = -8;
	public static int SHOT_SPACE = -5;
	public static int SHOT_WIDTH = 5;
	public static int SHOT_LENGTH = 20;
	public static Color SHOT_COLOR = Color.YELLOW;
	public static int SHOT_LIMIT = 20;
	
	// Shot predicates
	public static Predicate<Shot[]> SHOT_OUT  = shoots -> {
		if(shoots[0] == null)
			return false;
		
		else {
			if(shoots[0].y<=0 || shoots[0].isDone())
				return true;
			
			return false;
		}
	};
	
	public static Predicate<Shot[]> ENEMYSHOT_OUT = shoots -> {
		if(shoots[0] == null)
			return false;
		
		else {
			if(shoots[0].y>=Data.WINDOW_HEIGHT || shoots[0].isDone())
				return true;
			
			return false;
		}
	};
	
	// Enemy ship data
	public static int ENEMYSHIP_NUMBER = 5;
	public static int ENEMYSHIP_MAXNUMBER = 16;
	public static int ENEMYSHIP_MAXMOVEMENT = 5;
	public static Color ENEMYSHIP_COLOR = Color.RED;
	public static int ENEMYSHIP_DIAMETER = 50;
	public static int ENEMYSHIP_MOVEMENT = 2;
	public static int ENEMYSHIP_SHOTSPEED = 2;
	public static int ENEMYSHIP_MAXSHOOTSPEED = 10;
	
	public static int ENEMYSHIP_SEPARATION = ENEMYSHIP_DIAMETER*2;
	public static Color ENEMYSHIP_SHOT = Color.RED;
	public static int ENEMYSHIP_LIFE=1;
	
	// Enemy predicate
	public static Predicate<EnemyShip[]> ENEMYSHIP_DIRECTION = enemies -> {
		
		if(enemies[0].x<=0 || enemies[0].x-Data.ENEMYSHIP_MOVEMENT-Data.ENEMYSHIP_DIAMETER/2<=0)
			return true;
		
		else if(enemies[Data.ENEMYSHIP_NUMBER-1].x>=Data.WINDOW_WIDTH || 
				enemies[Data.ENEMYSHIP_NUMBER-1].x + Data.ENEMYSHIP_MOVEMENT + Data.ENEMYSHIP_DIAMETER/2>=Data.WINDOW_WIDTH)
			return true;
			
		return false;
	};
	
	// Barrier data
	public static int BARRIER_NUMBER = 3;
	public static int BARRIER_LIFES = 3;
	public static int BARRIER_SEPARATION = Data.WINDOW_WIDTH/BARRIER_NUMBER;
	public static Color BARRIER_COLOR = Color.GREEN;
	public static int BARRIER_WIDTH = 100;
	public static int BARRIER_HEIGTH = 50;
	
	// Score data
	public static int SCORE_BARRIER = 5;
	public static int SCORE_ENEMYSHIP = 15;
	
	// Game data
	public static Color FONT_COLOR = Color.WHITE;
	public static Font FONT = new Font("Arial", Font.PLAIN, 12);
	public static Font FONT_WASTED = new Font("Arial", Font.BOLD, 60);
	public static Font FONT_AGAIN = new Font("Arial", Font.PLAIN, 24);
	public static Font FONT_CREDITS = new Font("Arial", Font.PLAIN, 12);
	public static Font FONT_SCORES = new Font("Arial", Font.PLAIN, 16);
	public static Font FONT_INIT = new Font("Arial", Font.PLAIN, 20);
	
	// Extern data
	public static String SCORE_FILE = "src/ExternData/scores.yp";
	public static String MAINSHIP_FILE = "src/ExternData/Ship.png";
	public static String ENEMYSHIP_FILE = "src/ExternData/Space_invader.png";
	public static String OBJECT_FILE = "src/ExternData/Barrier.png";
}
