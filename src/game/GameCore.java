/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import game.entities.Entity;
import game.math.Vec2;
import game.stage.Stage;
import game.stage.TestStage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Davis
 *
 */
public class GameCore {
	
	private List<Entity> ents;
	private float cleanTime;
	private Stage stg;
	
	public GameCore() {
		ents = new ArrayList<>();
		
		stg = new TestStage(ents);
	}
	
	/**
	 * Pre render updates
	 * @param delta
	 */
	public void update(float delta) {
		cleanTime += delta;
		stg.update(delta);
		
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
		
		// remove outside
		if (cleanTime > 3f) {
			cleanTime = 0f;
			Iterator<Entity> it = ents.iterator();
			while (it.hasNext()) {
				Entity e = it.next();
				
				// remove outside of box
				if (e.position.x < -100 || e.position.x > 1380 || e.position.y < -100 || e.position.y > 820) {
					it.remove();
				}
			}
		}
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
		
		for (int i = 0; i < ents.size(); i++) {
			gc.setStroke(Color.AQUA);
			Vec2 dest = new Vec2(ents.get(i).position).add(ents.get(i).velocity);
			gc.strokeLine(ents.get(i).position.x, ents.get(i).position.y, dest.x, dest.y);
		}
	}
}
