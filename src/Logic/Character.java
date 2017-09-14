package Logic;

public abstract class Character extends Entity implements IHitteable{
	
	protected Shot[] shoots;
	protected int actualShoots;
	protected int lives;
	
	public abstract void move();
	public abstract void shoot();
	public abstract int getLife();
	public abstract void setLife(int life);
	public abstract void checkShoots(IHitteable ship);
	public abstract int getActualShoots();
	public abstract void refreshShoots();
	public abstract void rotateShoots();
	
}
