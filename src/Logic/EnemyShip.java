package Logic;

public class EnemyShip extends Character{

	private Shot[] shoots;
	private int actualShoots;
	private boolean dead;
	private boolean direction;
	private int speed;
	private int shootspeed;
	
	public EnemyShip(int x, int y, int speed, int shootspeed) {
		super.x = x;
		super.y = y;
		
		shoots = new Shot[Data.SHOT_LIMIT];
		actualShoots = 0;
		dead = false;
		direction = false;
		this.speed = speed;
		this.shootspeed = shootspeed;
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
	
	public void move() {
		if(direction)
			x += speed;
		
		else
			x -= speed;
	}
	
	public void changeDir() {
		direction = !direction;
	}
	
	public void moveShoots() {
		for(int i=0;i<actualShoots;i++)
			shoots[i].move();
	}
	
	public void shoot() {
		if(actualShoots<Data.SHOT_LIMIT) {
			shoots[actualShoots] = new Shot(this.x + Data.MAINSHIP_WIDTH/2, this.y+Data.ENEMYSHIP_DIAMETER+Data.SHOT_SPACE, shootspeed);
			actualShoots++;
		}
	}

	public void refreshShoots() {
		for(int i=0;i<actualShoots;i++)
			shoots[i].move();
	}

	public void rotateShoots() {
		for(int i=1;i<actualShoots;i++) 
			shoots[i-1] = shoots[i];
		
		if(actualShoots>0)
			actualShoots--;
	}

	public Shot[] getShoots() {
		return shoots;
	}

	public int getActualShoots() {
		return actualShoots;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void checkShoots(IHitteable mainShip) {
		for(int i=0;i<actualShoots;i++)
			shoots[i].hit(mainShip);
		
	}

	@Override
	public void gotHit() {
		this.setLife(this.getLife()-1);
		
		if(getLife()<=0)
			dead = true;

	}

	@Override
	public int getLife() {
		return this.lives;
	}

	@Override
	public void setLife(int life) {
		this.lives = life;
	}


}
