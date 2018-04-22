/**
 * 
 */
package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import game.entities.Entity;
import game.entities.Player;
import game.input.InputManager;
import game.input.KeyboardInput;
import game.input.PlayerInput;
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
	public InputManager inMgner;
	public List<Player> players;
	
	public GameCore() {
		ents = new ArrayList<>();
		players = new ArrayList<>();
		inMgner = new InputManager();
		stg = new TestStage(ents);
		
		addPlayer("test", Color.RED, 1, new KeyboardInput(null, KeyboardInput.KEYSET_WASD));
	}
	
	public void addPlayer(String name, Color colour, int team, PlayerInput in) {
		Player p = new Player(new Vec2(640f, 360f), 0, team, name, colour);
		ents.add(p);
		players.add(p);
		
		in.target = p;
		inMgner.handlers.add(in);
	}
	
	/**
	 * Pre render updates
	 * @param delta
	 */
	public void update(float delta) {
		cleanTime += delta;
		stg.update(delta);
		inMgner.update();
		
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
		
		//sort player by score
		Collections.sort(players);
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
		
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			gc.setFill(p.colour);
			gc.fillText(String.format("%d %s %03d HP %09d PT", i+1, p.name, p.health, p.score), 10f, 20f + (i * 30f));
		}
		
		// draw bodies
//		for (int i = 0; i < ents.size(); i++) {
//			if (ents.get(i).body != null) {
//				Vec2[] vert = ents.get(i).body.vertices;
//				
//				for (int j = 0; j < vert.length; j++) {
//					gc.setFill(Color.RED);
//					gc.fillRect(vert[j].x - 2f, vert[j].y - 2f, 4f, 4f);
//				}
//			}
//		}
		
//		for (int i = 0; i < ents.size(); i++) {
//			gc.setStroke(Color.AQUA);
//			Vec2 dest = new Vec2(ents.get(i).position).add(ents.get(i).velocity);
//			gc.strokeLine(ents.get(i).position.x, ents.get(i).position.y, dest.x, dest.y);
//		}
	}
}
