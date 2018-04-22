/**
 * 
 */
package game.stage.spawner;

import java.util.List;
import java.util.Random;

import game.entities.Entity;
import game.math.Vec2;
import game.stage.Side;

/**
 * @author Davis
 *
 */
public abstract class Spawner {

	protected float spawnDelay;
	protected float time;
	protected int round;

	/**
	 * @param spawnDelay
	 */
	public Spawner(float spawnDelay) {
		super();
		this.spawnDelay = spawnDelay;
	}

	/**
	 * spawns entities when delay has passed
	 * @param delta
	 * @param ents
	 */
	public void update(float delta, List<Entity> ents) {
		time += delta;
		
		if (time > spawnDelay) {
			time -= spawnDelay;
			spawn(ents);
		}
	}

	/**
	 * Spawns entity
	 * @param ents
	 */
	public abstract void spawn(List<Entity> ents);

	/**
	 * Increase the difficulty
	 */
	protected abstract void increaseDifficulty();
	
	/**
	 * Increases round counter for difficulty tracking
	 */
	public void roundUp() {
		round++;
		increaseDifficulty();
	}
	
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
