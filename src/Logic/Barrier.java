package Logic;

public class Barrier extends Obstacle{
	
	boolean isDead;
	
	public Barrier(int x, int y) {
		this.x = x;
		this.y = y;
		this.lives = Data.BARRIER_LIFES;
		isDead = false;
	}
	
	@Override
	public void gotHit() {
		lives--;
		
		if(lives == 0)
			isDead = true;
	}

	@Override
	public int getLife() {
		return this.lives;
	}

	@Override
	public void setLife(int life) {
		this.lives = life;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

}
