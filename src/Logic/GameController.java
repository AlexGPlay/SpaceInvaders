package Logic;

public class GameController {

	private Ship mainShip;
	private EnemyShip[] enemies;
	private Obstacle[] barriers;
	private int score;
	private int level;
	private int movement;
	boolean direction = true;
	private Score scoreTable;
	
	public GameController(Score scoreT) {
		this.scoreTable = scoreT;
		mainShip = new Ship(Data.WINDOW_HEIGHT-Data.MAINSHIP_HEIGTH*4, Data.WINDOW_WIDTH/2);

		enemies = new EnemyShip[Data.ENEMYSHIP_NUMBER];
		for (int i=0;i<Data.ENEMYSHIP_NUMBER;i++) 
			enemies[i] = new EnemyShip(i*Data.ENEMYSHIP_SEPARATION+Data.ENEMYSHIP_SEPARATION,20,Data.ENEMYSHIP_MOVEMENT, Data.ENEMYSHIP_SHOTSPEED);
		
		
		barriers = new Obstacle[Data.BARRIER_NUMBER];
		for(int i=0;i<Data.BARRIER_NUMBER;i++)
			barriers[i] = new Barrier(i*Data.BARRIER_SEPARATION+50,Data.WINDOW_HEIGHT/2);
		
		score = 0;
		movement = 0;
		level = 1;
	}

	public Ship getMainShip() {
		return mainShip;
	}
	
	public boolean playerAlive() {
		return mainShip.getLives()>0;
	}
	
	public boolean enemiesAlive() {
		for(EnemyShip temp : enemies)
			if(!temp.isDead())
				return true;
		
		return false;
	}

	public void refreshShip() {
		mainShip.refreshShoots();
		mainShip.moveShip(movement);

		if(Data.SHOT_OUT.test(mainShip.getShoots()))
			mainShip.rotateShoots();
		
		int obstHits = 0;
		for(Obstacle obs : barriers) {
			mainShip.checkShoots(obs);
			obstHits += mainShip.getHits();
		}
		
		
		score += obstHits * Data.SCORE_BARRIER;
		
		int enemyHits = 0;
		
		for(EnemyShip temp : enemies) {
			mainShip.checkShoots(temp);
			enemyHits += mainShip.getHits();
			temp.move();
			temp.checkShoots(mainShip);
			temp.moveShoots();

			for(Obstacle obs : barriers) 
				temp.checkShoots(obs);
			
			if(Data.ENEMYSHOT_OUT.test(temp.getShoots()))
				temp.rotateShoots();
		}
		
		score += enemyHits * Data.SCORE_ENEMYSHIP;

		if(Data.ENEMYSHIP_DIRECTION.test(enemies)) {
			
			for(EnemyShip temp : enemies)
				temp.changeDir();
			
		}
		
		decideShoot();
		
		if(!enemiesAlive())
			nextLevel();
		
		//System.out.println(nEnemies());
	}
	
	public int nEnemies() {
		int temp = 0;
		
		for(EnemyShip x : enemies)
			if(!x.isDead())
				temp++;
		
		return temp;
	}
	
	public void nextLevel() {
		level++;
		
		if(level%10==0)
			mainShip.setLife(mainShip.getLives()+1);
		
		int nextEnemies = Data.ENEMYSHIP_NUMBER+level;
		
		if(nextEnemies > Data.ENEMYSHIP_MAXNUMBER)
			nextEnemies = Data.ENEMYSHIP_MAXNUMBER;
		
		int nextSpeed = Data.ENEMYSHIP_MOVEMENT+(level/2);
		
		if(nextSpeed > Data.ENEMYSHIP_MAXMOVEMENT)
			nextSpeed = Data.ENEMYSHIP_MAXMOVEMENT;
		
		int nextShot = Data.ENEMYSHIP_SHOTSPEED + level;
		
		if(nextShot > Data.ENEMYSHIP_MAXSHOOTSPEED)
			nextShot = Data.ENEMYSHIP_MAXSHOOTSPEED;
		
		enemies = new EnemyShip[nextEnemies];
		
		for(int i=0;i<nextEnemies;i++) {
			int resX = i*Data.ENEMYSHIP_SEPARATION+Data.ENEMYSHIP_SEPARATION;
			int resY = ((resX/Data.WINDOW_WIDTH)*Data.ENEMYSHIP_DIAMETER)+20;
			
			resX %= Data.WINDOW_WIDTH;
			
			if(resY > 40)
				resX += 100;
			
			if(resX == 600)
				resX = 100;
			
			enemies[i] = new EnemyShip(resX,resY,
					nextSpeed, nextShot);
			
		}
		
	}

	public void decideShoot() {
		for(EnemyShip temp : enemies)
			if(temp.x >= mainShip.x && temp.x+Data.ENEMYSHIP_DIAMETER<=mainShip.x+Data.MAINSHIP_WIDTH && !temp.isDead())
				temp.shoot();
	}

	public EnemyShip[] getEnemies() {
		return enemies;
	}
	
	public Obstacle[] getBarriers() {
		return barriers;
	}
	
	public int getScore() {
		return score;
	}

	public void setMovement(int movement) {
		this.movement = movement;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int checkHighScore() {
		return scoreTable.newScore(score);
	}
	
	public void addScore(String nombre, int i) {
		scoreTable.addScore(nombre, score, i);
	}
	
}
