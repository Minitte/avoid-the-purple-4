/**
 * 
 */
package game.stage;

import java.util.List;
import java.util.Random;

import game.entities.Entity;
import game.math.Vec2;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Davis
 *
 */
public abstract class Stage {

	public static final float ROUND_SPEEDUP = 0.2f;
	
	public int maxRound = 5;
	public int round;
	public float time;
	
	protected List<Entity> ents;
	
	/**
	 * @param ents
	 */
	public Stage(List<Entity> ents) {
		super();
		this.ents = ents;
	}

	/**
	 * Updates the stage like spawning things
	 * @param delta
	 */
	public abstract void update(float delta);
	
	public abstract void drawBackground(GraphicsContext gc, float delta);
	
	/**
	 * Gets a random spot long the side
	 * @param s
	 * @return
	 */
	protected Vec2 getRandomSpot(Side s, Random rand) {
		float x = s.minX + rand.nextFloat() * (s.maxX - s.minX);
		float y = s.minY + rand.nextFloat() * (s.maxY - s.minY);
		return new Vec2(x, y);
	}
	
}
