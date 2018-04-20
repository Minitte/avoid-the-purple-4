/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import game.entities.BallEntity;
import game.entities.Entity;
import game.math.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Davis
 *
 */
public class GameCore {
	
	public List<Entity> ents;
	
	public GameCore() {
		ents = new ArrayList<>();
		ents.add(new BallEntity(new Vec2(5f, 10f), 0f, -1, 30f));
	}
	
	/**
	 * Pre render updates
	 * @param delta
	 */
	public void update(float delta) {
		IntStream.range(0, ents.size()).parallel().forEach(i -> {
			ents.get(i).update(delta);
		});
	}
	
	/**
	 * Renders to screen
	 * @param delta
	 */
	public void render(GraphicsContext gc, float delta) {
		gc.setFill(Color.PURPLE);
		gc.setStroke(Color.YELLOW);
		
		for (int i = 0; i < ents.size(); i++) {
			ents.get(i).draw(gc, delta);
		}
	}
}
