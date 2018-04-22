/**
 * 
 */
package game.stage;

import java.util.ArrayList;
import java.util.List;

import game.entities.Entity;
import game.stage.spawner.Spawner;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Davis
 *
 */
public abstract class Stage {
	
	public int maxRound = 5;
	public int round;
	public float time;
	
	protected List<Entity> ents;
	protected List<Spawner> spawners;
	
	/**
	 * @param ents
	 */
	public Stage(List<Entity> ents) {
		super();
		this.ents = ents;
		spawners = new ArrayList<>();
	}

	/**
	 * Updates the stage like spawning things
	 * @param delta
	 */
	public void update(float delta) {
		time += delta;
		stageUpdate(delta);
		
		// spawn stuff
		for (int i = 0; i < spawners.size(); i++) {
			spawners.get(i).update(delta, ents);
		}
	}
	
	/**
	 * Increase all spawner difficulty
	 */
	public void increaseDifficultyAll() {
		for (int i = 0; i < spawners.size(); i++) {
			spawners.get(i).roundUp();
		}
	}
	
	/**
	 * Updates the custom stuff for custom stages like adding custom spawner :)
	 * @param delta
	 */
	protected abstract void stageUpdate(float delta);
	
	/**
	 * Draws the background for the stage if any
	 * @param gc
	 * @param delta
	 */
	public abstract void drawBackground(GraphicsContext gc, float delta);
	
}
