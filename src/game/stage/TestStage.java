/**
 * 
 */
package game.stage;

import java.util.List;

import game.entities.Entity;
import game.stage.spawner.BallSpawner;
import game.stage.spawner.SpiltSpawner;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Davis
 *
 */
public class TestStage extends Stage {
	
	Color bkgdColour;
	Font bkgdFont;
	float roundUpPoint;
	int spawnertrack;
	
	/**
	 * @param ents
	 */
	public TestStage(List<Entity> ents) {
		super(ents);
		roundUpPoint = 10.0f;
		spawners.add(new BallSpawner());
		bkgdFont = new Font("Courier", 182f);
		bkgdColour = new Color(0.4, 0.4, 0.4, 0.5);
	}

	/* (non-Javadoc)
	 * @see game.stage.Stage#stageUpdate(float)
	 */
	@Override
	public void stageUpdate(float delta) {
		if (time > roundUpPoint) {
			roundUpPoint += roundUpPoint + 5f;
			round++;
			increaseDifficultyAll();
		}
		
		if (round == 1 && spawnertrack == 0) {
			spawnertrack = round;
			spawners.add(new SpiltSpawner());
		}
		
		if (round == 2 && spawnertrack == 1) {
			spawnertrack = round;
			increaseDifficultyAll();
		}
	}

	/* (non-Javadoc)
	 * @see game.stage.Stage#drawBackground(javafx.scene.canvas.GraphicsContext, float)
	 */
	@Override
	public void drawBackground(GraphicsContext gc, float delta) {
		gc.setFont(bkgdFont);
		gc.setFill(bkgdColour);
		gc.fillText("" + (round + 1), 640.0, 360.0);
		
	}
}
