/**
 * 
 */
package game.stage.spawner;

import java.util.List;
import java.util.Random;

import game.entities.BallEntity;
import game.entities.Entity;
import game.math.Vec2;
import game.stage.Side;

/**
 * @author Davis
 *
 */
public class BallSpawner extends Spawner {
	
	public static final float ROUND_SPEEDUP = 0.2f;
	
	public BallSpawner() {
		super(1.0f);
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
		
		e = new BallEntity(pos, 0f, -1, 10f + (15 * rand.nextFloat()));
		
		dir.multiply((60f + (130f * rand.nextFloat())) * (1f + ((float)round * ROUND_SPEEDUP)));
		
		e.velocity = dir;
		
		ents.add(e);
	}

	/* (non-Javadoc)
	 * @see game.stage.spawner.Spawner#increaseDifficulty()
	 */
	@Override
	protected void increaseDifficulty() {
		spawnDelay -= 0.1f;
	}


}
