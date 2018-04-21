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
	
	private List<Entity> ents;
	
	public GameCore() {
		ents = new ArrayList<>();
		ents.add(new BallEntity(new Vec2(50f, 100f), 0f, -1, 30f));
	}
	
	/**
	 * Pre render updates
	 * @param delta
	 */
	public void update(float delta) {
		// collision Check
		IntStream.range(0, ents.size()).parallel().forEach(i -> {
			Entity a = ents.get(i);
			if (a.collidable) {
				for (int j = i; j < ents.size(); j++) {
					Entity b = ents.get(j);
					
					if (b.collidable && a.body.checkCollision(b.body)) {
						a.handleCollision(b);
						b.handleCollision(a);
					}
				}
			}
		});
		
		// update
		IntStream.range(0, ents.size()).parallel().forEach(i -> {
			ents.get(i).update(delta);
		});
	}
	
	/**
	 * Renders to screen
	 * @param delta
	 */
	public void render(GraphicsContext gc, float delta) {
		// draw objects
		for (int i = 0; i < ents.size(); i++) {
			ents.get(i).draw(gc, delta);
		}
		
		// draw bodies
		for (int i = 0; i < ents.size(); i++) {
			if (ents.get(i).body != null) {
				Vec2[] vert = ents.get(i).body.vertices;
				
				for (int j = 0; j < vert.length; j++) {
					gc.setFill(Color.RED);
					gc.fillRect(vert[j].x - 2f, vert[j].y - 2f, 4f, 4f);
				}
			}
		}
	}
}
