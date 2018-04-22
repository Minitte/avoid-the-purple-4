/**
 * 
 */
package game.entities;

import game.collision.CollisionBody;
import game.math.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Davis
 *
 */
public class BallEntity extends Entity{

	public float radius;
	
	private boolean collided;

	/**
	 * @param position
	 * @param angle
	 * @param team
	 * @param radius
	 */
	public BallEntity(Vec2 position, float angle, int team, float radius) {
		super(position, angle, team);
		this.radius = radius;
		collidable = true;
		body = new CollisionBody(radius, position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entities.Entity#update(float)
	 */
	@Override
	public void update(float delta) {
		
		Vec2 scaledVel = new Vec2(velocity).multiply(delta);
		
		position.add(scaledVel);
		
		body.translateVertices(scaledVel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see game.entities.Entity#draw(javafx.scene.canvas.GraphicsContext, float)
	 */
	@Override
	public void draw(GraphicsContext gc, float delta) {
		gc.setFill(Color.PURPLE);
		gc.fillOval(position.x - radius, position.y - radius, radius * 2f, radius * 2f);
	}

	/* (non-Javadoc)
	 * @see game.entities.Entity#handleCollision(game.entities.Entity)
	 */
	@Override
	public void handleCollision(Entity other) {
		if (other.id > 0) {
			dead = true;
		}
	}

}
