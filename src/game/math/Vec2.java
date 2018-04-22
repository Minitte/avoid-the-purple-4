/**
 * 
 */
package game.math;

/**
 * @author Davis
 *
 */
public class Vec2 {
	public static final Vec2 UP = new Vec2(0, -1f);
	public static final Vec2 DOWN = new Vec2(0, 1f);
	public static final Vec2 LEFT = new Vec2(-1f, 0);
	public static final Vec2 RIGHT = new Vec2(1f, 0);
	
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
	 * Noramlized/unit vector
	 * @param theta
	 */
	public Vec2(float theta) {
		x = (float)Math.cos(theta);
		y = (float)Math.sin(theta);
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
		x -= amt.x;
		y -= amt.y;
		
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
		
		if (!isZero(len)) {
			x /= len;
			y /= len;
		}
		
		return this;
	}
	
	/**
	 * Calulates the length
	 * @return
	 */
	public float getLength() {
		if (!isZero(x) && !isZero(y)) {
			return (float)Math.sqrt((x * x) + (y * y));
		}
		
		return 0f;
	}
	
	/**
	 * Squared of the length, faster then getting actual length
	 * @return
	 */
	public float getLengthSquared() {
		return (x * x) + (y * y);
	}
	
	/**
	 * Returns the angle of the vector tan-1(y / x)
	 * @return
	 */
	public float getAngle() {
		return (float)Math.atan(y / x);
	}
	
	private static boolean isZero(float f) {
		return Math.abs(f) < 0.001f;
	}

}
