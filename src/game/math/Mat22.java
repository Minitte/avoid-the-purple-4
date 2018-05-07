/**
 * 
 */
package game.math;

/**
 * @author Davis
 *
 */
public class Mat22 {
	public float[][] values;
	
	/**
	 * Rotation matrix (ccw)
	 * @param r
	 */
	public Mat22(float r) {
		values = new float[2][2];
		
		float cos = (float) Math.cos(r);
		float sin = (float) Math.sin(r);
		
		values[0][0] = cos;
		values[1][0] = -sin;
		values[0][1] = sin;
		values[1][1] = cos;
	}
}
