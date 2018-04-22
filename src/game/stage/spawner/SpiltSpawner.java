/**
 * 
 */
package game.stage.spawner;

import java.util.List;
import java.util.Random;

import game.entities.Entity;
import game.entities.SplittingBallEntity;
import game.math.Vec2;
import game.stage.Side;

/**
 * @author Davis
 *
 */
public class SpiltSpawner extends Spawner {

	/**
	 * 
	 */
	public SpiltSpawner() {
		super(1.2f);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see game.stage.spawner.Spawner#spawn(java.util.List)
	 */
	@Override
	public void spawn(List<Entity> ents) {
		Random rand = new Random();
		Entity e = null;
		Vec2 dir = null;
		Vec2 pos = null;
		
		switch(rand.nextInt(4))
		{
		case 0: // top
			pos = getRandomSpot(Side.TOP, rand);
			
			dir = getRandomSpot(Side.BOTTOM, rand);
			dir.minus(pos);
			dir.normalize();
			
			break;
			
		case 1: // right
			pos = getRandomSpot(Side.RIGHT, rand);
			
			dir = getRandomSpot(Side.LEFT, rand);
			dir.minus(pos);
			dir.normalize();
			break;
			
		case 2: // bottom
			pos = getRandomSpot(Side.BOTTOM, rand);
			
			dir = getRandomSpot(Side.TOP, rand);
			dir.minus(pos);
			dir.normalize();
			break;
			
		case 3: // left;
			pos = getRandomSpot(Side.LEFT, rand);
			
			dir = getRandomSpot(Side.RIGHT, rand);
			dir.minus(pos);
			dir.normalize();
			break;
		}
		
		e = new SplittingBallEntity(pos, 0, -1, 60f);
		
		dir.multiply(30f);
		
		e.velocity = dir;
		
		ents.add(e);
	}

	/* (non-Javadoc)
	 * @see game.stage.spawner.Spawner#increaseDifficulty()
	 */
	@Override
	protected void increaseDifficulty() {
		// TODO Auto-generated method stub

	}

}
