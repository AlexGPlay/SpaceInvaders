package Logic;

public class Ship extends Character{

	private Shot[] shoots;
	private int actualShoots;
	private int lives;
	private int movement;
	private int totalHits;

	public Ship(int y, int x) {
		setX(x);
		setY(y);
		shoots = new Shot[Data.SHOT_LIMIT];
		actualShoots = 0;
		lives = Data.MAINSHIP_LIFE;
	}

	@Override
	public int getX() {
		return super.x;
	}

	@Override
	public void setX(int x) {
		super.x = x;
	}

	@Override
	public int getY() {
		return super.y;
	}

	@Override
	public void setY(int y) {
		super.y = y;
	}

	public void moveShip(int movement) {
		Integer result = x + movement;

		if(Data.MAINSHIP_LEFTMOVE_CHECK.test(result) && Data.MAINSHIP_RIGHTMOVE_CHECK.test(result))
			this.movement = result;
		
		else
			this.movement = x + 0;
		
		move();
	}

	public void shoot() {
		if(actualShoots<Data.SHOT_LIMIT) {
			shoots[actualShoots] = new Shot(this.x + Data.MAINSHIP_WIDTH/2, this.y+Data.SHOT_SPACE, Data.SHOT_SPEED);
			actualShoots++;
		}
	}

	public void refreshShoots() {
		for(int i=0;i<actualShoots;i++)
			shoots[i].move();
	}

	public void rotateShoots() {
		for(int i=1;i<=actualShoots;i++) 
			shoots[i-1] = shoots[i];

		actualShoots--;
	}

	public Shot[] getShoots() {
		return shoots;
	}

	public int getActualShoots() {
		return actualShoots;
	}

	public void gotHit() {
		lives--;
	}

	public int getLives() {
		return lives;
	}

	public void checkShoots(IHitteable enemies) {
		int total = 0;
		
		for(int i=0;i<actualShoots;i++) {
			shoots[i].hit(enemies);
			total += shoots[i].getHits();
		}
		
		totalHits = total;
	}

	@Override
	public void move() {
		x = movement;
	}

	@Override
	public int getLife() {
		return this.lives;
	}

	@Override
	public void setLife(int life) {
		this.lives = life;
	}
	
	public int getHits() {
		return totalHits;
	}

}
