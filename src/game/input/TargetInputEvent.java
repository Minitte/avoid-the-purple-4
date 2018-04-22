/**
 * 
 */
package game.input;

import javafx.scene.input.KeyCode;

/**
 * @author Davis
 *
 */
public class TargetInputEvent {
	public int target;
	public KeyCode key;

	/**
	 * @param target
	 * @param key
	 */
	public TargetInputEvent(int target, KeyCode key) {
		super();
		this.target = target;
		this.key = key;
	}

}
