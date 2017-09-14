package Logic;

public class Shot extends Entity{

	private boolean done;
	private int hits;
	private int speed;

	public Shot(int x, int y, int speed) {
		super.x = x;
		super.y = y;
		done = false;
		this.speed = speed;
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
		this.y += speed;
	}

	public void hit(IHitteable hit) {
		hits = 0;
		Entity ship = (Entity)hit;

		if(ship instanceof EnemyShip) {
			if(x >= ship.x && x<= ship.x+Data.ENEMYSHIP_DIAMETER && y>=ship.y && y<=ship.y+Data.ENEMYSHIP_DIAMETER) {
				if(!((EnemyShip)ship).isDead()) {
					((EnemyShip)ship).gotHit();
					hits++;
					done = true;
				}
			}
		}

		else if(ship instanceof Ship){
			if(x >= ship.x && x <= ship.x+Data.MAINSHIP_WIDTH && y>=ship.y && y<=ship.y+Data.MAINSHIP_HEIGTH) {
				((Ship)ship).gotHit();
				hits++;
				done = true;
			}
		}

		else if(ship instanceof Barrier) {
			if(x >= ship.x && x <= ship.x+Data.BARRIER_WIDTH && y>=ship.y && y<=ship.y+Data.BARRIER_HEIGTH) {
				if(((Obstacle)ship).getLife()>0){
					((Obstacle)ship).gotHit();
					hits++;
					done = true;
				}
			}
		}

	}
	
	public int getHits() {
		return hits;
	}

	public boolean isDone() {
		return done;
	}

}
