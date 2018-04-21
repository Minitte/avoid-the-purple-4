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
	 * Copy constructor
	 * @param other
	 */
	public Vec2(Vec2 other) {
		x = other.x;
		y = other.y;
	}
	
	/**
	 * Adds amt to this vector
	 * @param amt
	 */
	public Vec2 add(Vec2 amt) {
		x += amt.x;
		y += amt.y;
		
		return this;
	}
	
	/**
	 * Minus amt to this vector
	 * @param amt
	 */
	public Vec2 minus(Vec2 amt) {
		x += amt.x;
		y += amt.y;
		
		return this;
	}
	
	/**
	 * Multiply amt to this vector
	 * @param amt
	 */
	public Vec2 multiply(Vec2 amt) {
		x *= amt.x;
		y *= amt.y;
		
		return this;
	}
	
	/**
	 * Multiply amt to this vector (both x and y)
	 * @param amt
	 */
	public Vec2 multiply(float amt) {
		x *= amt;
		y *= amt;
		
		return this;
	}
	
	/**
	 * Multiplies with the matrix
	 * @param m
	 * @return
	 */
	public Vec2 multiply(Mat22 m) {
		x = (x * m.values[0][0]) + (y * m.values[0][1]);
		y = (x * m.values[1][0]) + (y * m.values[1][1]);
		
		return this;
	}
	
	/**
	 * Divide amt to this vector
	 * @param amt
	 */
	public Vec2 divide(Vec2 amt) {
		x /= amt.x;
		y /= amt.y;
		
		return this;
	}
	
	/**
	 * Dot product with amt
	 * @param amt
	 */
	public float dot(Vec2 amt) {
		return (x * amt.y) + (y * amt.x);
	}
	
	/**
	 * Normalizes the vector
	 */
	public Vec2 normalize() {
		float len = getLength();
		x /= len;
		y /= len;
		
		return this;
	}
	
	/**
	 * Calulates the length
	 * @return
	 */
	public float getLength() {
		return (float)Math.sqrt((x * x) + (y * y));
	}
	
	/**
	 * Squared of the length, faster then getting actual length
	 * @return
	 */
	public float getLengthSquared() {
		return (x * x) + (y * y);
	}

}
