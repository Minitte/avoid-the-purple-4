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
public class BallEntity extends Entity {

	public float radius;

	/**
	 * @param position
	 * @param angle
	 * @param team
	 * @param radius
	 */
	public BallEntity(Vec2 position, float angle, int team, float radius) {
		super(position, angle, team);
		this.radius = radius;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entities.Entity#update(float)
	 */
	@Override
	public void update(float delta) {
		position.x += velocity.x * delta;
		position.y += velocity.y * delta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entities.Entity#draw(javafx.scene.canvas.GraphicsContext, float)
	 */
	@Override
	public void draw(GraphicsContext gc, float delta) {
		
		gc.fillOval(position.x - (radius * 0.5f), position.y - (radius * 0.5f), radius * 2f, radius * 2f);

	}

}
