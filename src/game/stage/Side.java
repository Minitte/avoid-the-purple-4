/**
 * 
 */
package game.stage;

/**
 * @author Davis
 *
 */
public enum Side {

	TOP(	0f, 		1280f, 		-50f+100f, 	-50f+100f), //
	BOTTOM(	0f, 		1280f, 		770f-100f, 	770f-100f), //
	RIGHT(	1330f-100f, 1330f-100f, 0f, 		720f), //
	LEFT(	-50f+100f, 	-50f+100f, 	0f, 		720f);

	public final float minX, maxX;
	public final float minY, maxY;

	/**
	 * @param minX
	 * @param maxX
	 * @param minY
	 * @param maxY
	 */
	private Side(float minX, float maxX, float minY, float maxY) {
		this.minX = minX;
		this.maxX = maxX;
		this.minY = minY;
		this.maxY = maxY;
	}

}
