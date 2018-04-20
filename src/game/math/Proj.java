/**
 * 
 */
package game.math;

/**
 * @author Davis
 *
 */
public class Proj {
	float min, max;

	/**
	 * @param min
	 * @param max
	 */
	public Proj(float min, float max) {
		super();
		this.min = min;
		this.max = max;
	}
	
	/**
	 * Checks for overlap between projections
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static boolean overlap(Proj p1, Proj p2) {
		if (p2.min < p1.max && p1.max < p2.max) {
			return true;
		}
		
		if (p2.min < p1.min && p1.min < p2.max) {
			return true;
		}
		
		return false;
	}
}
