/**
 * 
 */
package game.stage;

import java.util.List;
import java.util.Random;

import game.entities.BallEntity;
import game.entities.Entity;
import game.math.Vec2;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Davis
 *
 */
public class TestStage extends Stage {
	
	public float circleSpawnDelay = 0.25f;
	
	public float timeConsume;
	
	/**
	 * @param ents
	 */
	public TestStage(List<Entity> ents) {
		super(ents);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see game.stage.Stage#update(float)
	 */
	@Override
	public void update(float delta) {
		time += delta;
		timeConsume += delta;

		while (timeConsume > circleSpawnDelay) {
			timeConsume -= circleSpawnDelay;
			circleEdgeSpawn();
		}
	}
	
	private void circleEdgeSpawn() {
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
		
		dir.multiply((50f + (130f * rand.nextFloat())) * (1f + ((float)round * ROUND_SPEEDUP)));
		
		e.velocity = dir;
		
		ents.add(e);
	}

	private void ballWaveSpawn() {
		
	}

	/* (non-Javadoc)
	 * @see game.stage.Stage#drawBackground(javafx.scene.canvas.GraphicsContext, float)
	 */
	@Override
	public void drawBackground(GraphicsContext gc, float delta) {
		// TODO Auto-generated method stub
		
	}
}
