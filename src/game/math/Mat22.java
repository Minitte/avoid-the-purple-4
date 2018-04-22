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
		
		values[0][0] = (float) Math.cos(r);
		values[1][0] = (float) Math.sin(r);
		values[0][1] = -values[1][0];
		values[1][1] = values[0][0];
	}
}
