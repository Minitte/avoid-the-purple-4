/**
 * 
 */
package game.entities;

import java.util.List;

import game.collision.CollisionBody;
import game.math.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Davis
 *
 */
public class MissileEntity extends Entity {

	/**
	 * @param position
	 * @param angle
	 * @param team
	 * @param length
	 * @param width
	 */
	public MissileEntity(Vec2 position, float angle, int team, float length, float width) {
		super(position, angle, team);
		
		float halfLen = length / 2f;
		float halfWid = width / 2f;
		
		Vec2[] vertice = {
			new Vec2(-halfLen, -halfWid),
			new Vec2(halfLen, -halfWid),
			new Vec2(halfLen, halfWid),
			new Vec2(-halfLen, halfWid)
		};
		
		body = new CollisionBody(vertice);
		rotateTo(angle);
		body.translateVertices(position);
	}

	/* (non-Javadoc)
	 * @see game.entities.Entity#update(float, java.util.List)
	 */
	@Override
	public void update(float delta, List<Entity> ents) {
		
		Vec2 scaledVel = new Vec2(velocity).multiply(delta);
		
		position.add(scaledVel);
		
		body.translateVertices(scaledVel);

	}

	/* (non-Javadoc)
	 * @see game.entities.Entity#draw(javafx.scene.canvas.GraphicsContext, float)
	 */
	@Override
	public void draw(GraphicsContext gc, float delta) {
		gc.setFill(Color.MEDIUMPURPLE);
		
		// convert xy array
		double[] arrX = {body.vertices[0].x, body.vertices[1].x, body.vertices[2].x, body.vertices[3].x};
		double[] arrY = {body.vertices[0].y, body.vertices[1].y, body.vertices[2].y, body.vertices[3].y};
		
		gc.fillPolygon(arrX, arrY, 4);
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
