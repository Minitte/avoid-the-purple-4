/**
 * 
 */
package game.math;

/**
 * @author Davis
 *
 */
public class Vec2 {
	public float x, y;

	/**
	 * Default (0, 0)
	 */
	public Vec2() {
		super();
	}

	/**
	 * @param x
	 * @param y
	 */
	public Vec2(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Adds amt to this vector
	 * @param amt
	 */
	public void add(Vec2 amt) {
		x += amt.x;
		y += amt.y;
	}
	
	/**
	 * Minus amt to this vector
	 * @param amt
	 */
	public void minus(Vec2 amt) {
		x += amt.x;
		y += amt.y;
	}
	
	/**
	 * Multiply amt to this vector
	 * @param amt
	 */
	public void multiply(Vec2 amt) {
		x *= amt.x;
		y *= amt.y;
	}
	
	/**
	 * Divide amt to this vector
	 * @param amt
	 */
	public void divide(Vec2 amt) {
		x /= amt.x;
		y /= amt.y;
	}
	
	/**
	 * Dot product with amt
	 * @param amt
	 */
	public float dot(Vec2 amt) {
		return (x * amt.y) + (y * amt.x);
	}

}
