/**
 * 
 */
package game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Davis
 *
 */
public class GameCore {
	
	/**
	 * Pre render updates
	 * @param delta
	 */
	public void update(float delta) {
		
	}
	
	/**
	 * Renders to screen
	 * @param delta
	 */
	public void render(GraphicsContext gc, float delta) {
		gc.setFill(Color.PURPLE);
		gc.setStroke(Color.YELLOW);
		
		gc.fillRect(10, 10, 40, 40);
	}
}
