package Logic;

public abstract class Obstacle extends Entity implements IHitteable{
	
	protected int lives;
	
	public abstract int getLife();
	public abstract void setLife(int life);
	
}
