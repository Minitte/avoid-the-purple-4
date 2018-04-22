/**
 * 
 */
package game.entities;

import java.util.List;

import game.collision.CollisionBody;
import game.math.Mat22;
import game.math.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Davis
 *
 */
public class SplittingBallEntity extends Entity {

	static Color normalColour = new Color(204.0/255.0, 51.0/255.0, 255.0/255.0, 1.0);
	static Color blinkColour = new Color(213.0/255.0, 128.0/255.0, 255.0/255.0, 1.0);
	
	float radius;
	float time;
	float blinkTime;
	Color colour;
	boolean blinkStart;
	boolean altColour;
	int numSplit;
	float spiltStartTime;
	
	/**
	 * @param position
	 * @param angle
	 * @param team
	 */
	public SplittingBallEntity(Vec2 position, float angle, int team, float radius) {
		super(position, angle, team);
		this.radius = radius;
		collidable = true;
		body = new CollisionBody(radius, position);
		colour = normalColour;
		blinkTime = 1.5f;
		id = -2;
		spiltStartTime = 2.5f;
	}

	/* (non-Javadoc)
	 * @see game.entities.Entity#update(float, java.util.List)
	 */
	@Override
	public void update(float delta, List<Entity> ents) {
		time += delta;
		Vec2 scaledVel = new Vec2(velocity).multiply(delta);
		
		position.add(scaledVel);
		
		body.translateVertices(scaledVel);
		
		if (time > spiltStartTime && !blinkStart) {
			blinkStart = true;
			time = 0;
		}
		
		if (blinkStart) {
			
			// boom, spawn more
			if (blinkTime < 0f) {
				dead = true;
				
				if (numSplit < 2) {
					for (int i = 0; i < 2; i++) {
						Mat22 rot = new Mat22((float)Math.toRadians(30 - (60 * i)));
						Vec2 vel = new Vec2(velocity).multiply(rot);
						SplittingBallEntity e = new SplittingBallEntity(new Vec2(position), angle, team, radius * 0.5f);
						e.velocity = vel;
						e.numSplit = numSplit + 1;
						e.spiltStartTime -= 0.35f;
						ents.add(e);
					}
				}
			}
			
			// blink
			if (time > blinkTime) {
				blinkTime -= 0.2f;
				time = 0;
				colour = altColour ? blinkColour : normalColour;
				altColour = !altColour;
			}
		}

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
	 * @see game.entities.Entity#handleCollision(game.entities.Entity, java.util.List)
	 */
	@Override
	public void handleCollision(Entity other, List<Entity> ents) {
		if (other.id > 0) {
			dead = true;
		}

	}

}
