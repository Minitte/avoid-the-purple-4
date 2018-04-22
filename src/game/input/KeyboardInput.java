/**
 * 
 */
package game.input;

import game.entities.Entity;
import game.math.Vec2;
import javafx.scene.input.KeyCode;

/**
 * @author Davis
 *
 */
public class KeyboardInput extends PlayerInput {

	public static final KeyCode[] KEYSET_WASD = {KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D};
	public static final KeyCode[] KEYSET_ARROW = {KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT};
	
	public KeyCode[] keyset;

	private boolean upPress, downPress, leftPress, rightPress;
	
	/**
	 * @param target
	 * @param keyset
	 */
	public KeyboardInput(Entity target, KeyCode[] keyset) {
		super(target);
		this.keyset = keyset;
	}

	@Override
	public boolean handleInput(TargetInputEvent in) {
		if (in.key == keyset[0]) {
			upPress = true;
		} else if (in.key == keyset[1]) {
			downPress = true;
		}

		if (in.key == keyset[2]) {
			leftPress = true;
		} else if (in.key == keyset[3]) {
			rightPress = true;
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see game.input.PlayerInput#handleInputReleased(game.input.TargetInputEvent)
	 */
	@Override
	public boolean handleInputReleased(TargetInputEvent in) {
		if (in.key == keyset[0]) {
			upPress = false;
		} else if (in.key == keyset[1]) {
			downPress = false;
		}

		if (in.key == keyset[2]) {
			leftPress = false;
		} else if (in.key == keyset[3]) {
			rightPress = false;
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see game.input.PlayerInput#update()
	 */
	@Override
	public void update() {
		Vec2 move = new Vec2();
		if (upPress) {
			move.add(Vec2.UP);
		}
		
		if (downPress) {
			move.add(Vec2.DOWN);
		}
		
		if (leftPress) {
			move.add(Vec2.LEFT);
		}
		
		if (rightPress) {
			move.add(Vec2.RIGHT);
		}
		
		move.normalize();
		move.multiply(target.speed);
		
		target.velocity = move;
	}



}
