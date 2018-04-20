/**
 * 
 */
package game.entities;

import game.math.Vec2;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Davis
 *
 */
public abstract class Entity {
	public Vec2 position;
	public float angle;
	public int team;
	public Vec2 velocity;
	public int id;

	/**
	 * @param position
	 * @param angle
	 * @param team
	 */
	public Entity(Vec2 position, float angle, int team) {
		super();
		this.position = position;
		this.angle = angle;
		this.team = team;
	}

	/**
	 * pre draw member updates
	 * 
	 * @param delta
	 */
	public abstract void update(float delta);

	/**
	 * draws the entity
	 * 
	 * @param gc
	 * @param delta
	 */
	public abstract void draw(GraphicsContext gc, float delta);
}
