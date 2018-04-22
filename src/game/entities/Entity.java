/**
 * 
 */
package game.entities;

import game.collision.CollisionBody;
import game.math.Vec2;
import javafx.scene.canvas.GraphicsContext;


/**
 * @author Davis
 *
 */
public abstract class Entity {
	public Vec2 position;
	public float angle;
	public Vec2 velocity;
	
	public CollisionBody body;
	public boolean collidable;

	public int id;
	public int team;
	public float speed;
	public int health;
	
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
		
		velocity = new Vec2();
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
	
	public abstract void handleCollision(Entity other);
}
