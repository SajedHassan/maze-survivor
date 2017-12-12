package objects;

/**
 * Represents any game object that requires being updated every frame.
 * 
 * @author H
 *
 */
public abstract class GameObject {

	protected double x;

	protected double y;

	protected double angle;

	public GameObject () {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
	}

	public GameObject (double x, double y) {
		this.x = x;
		this.y = y;
		this.angle = 0;
	}

	public GameObject (double x, double y, double angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	/**
	 * Updates the game object.
	 */
	public abstract void update();

}
