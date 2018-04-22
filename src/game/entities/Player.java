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
public class Player extends Entity implements Comparable<Player>{

	public float radius = 6f;
	public Color colour;
	public String name;
	public int score;
	
	/**
	 * @param position
	 * @param angle
	 * @param name
	 * @param team
	 */
	public Player(Vec2 position, float angle, int team, String name, Color colour) {
		super(position, angle, team);
		this.name = name;
		this.colour = colour;
		body = new CollisionBody(radius, position);
		
		speed = 150f;
		health = 30;
		collidable = true;
	}

	/* (non-Javadoc)
	 * @see game.entities.Entity#update(float)
	 */
	@Override
	public void update(float delta) {
		Vec2 scaledVel = new Vec2(velocity).multiply(delta);
		
		position.add(scaledVel);
		
		body.translateVertices(scaledVel);
	}

	/* (non-Javadoc)
	 * @see game.entities.Entity#draw(javafx.scene.canvas.GraphicsContext, float)
	 */
	@Override
	public void draw(GraphicsContext gc, float delta) {
		gc.setFill(colour);
		gc.fillOval(position.x - radius, position.y - radius, radius * 2f, radius * 2f);
	}

	/* (non-Javadoc)
	 * @see game.entities.Entity#handleCollision(game.entities.Entity)
	 */
	@Override
	public void handleCollision(Entity other) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Player arg0) {
		return arg0.score - score;
	}
}
