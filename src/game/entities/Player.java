/**
 * 
 */
package game.entities;

import java.util.List;

import game.collision.CollisionBody;
import game.math.Vec2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * @author Davis
 *
 */
public class Player extends Entity implements Comparable<Player>{

	public static final Font NAME_FONT = new Font("Courier", 18);
	
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
		
		id = 1;
		speed = 150f;
		health = 30;
		collidable = true;
	}

	/* (non-Javadoc)
	 * @see game.entities.Entity#update(float)
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
		gc.setFill(colour);
		gc.fillOval(position.x - radius, position.y - radius, radius * 2f, radius * 2f);
		
		// name
		gc.setFont(NAME_FONT);
		gc.setFill(Color.BLACK);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText(name, position.x, position.y - (radius * 3));
	}

	/* (non-Javadoc)
	 * @see game.entities.Entity#handleCollision(game.entities.Entity, java.util.List)
	 */
	@Override
	public void handleCollision(Entity other, List<Entity> ents) {
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
