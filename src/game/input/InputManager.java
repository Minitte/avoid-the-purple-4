/**
 * 
 */
package game.input;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author Davis
 *
 */
public class InputManager {
	
	public Queue<TargetInputEvent> inputs;
	public Queue<TargetInputEvent> releases;
	public List<PlayerInput> handlers;
	
	public InputManager() {
		inputs = new ArrayDeque<>();
		releases = new ArrayDeque<>();
		handlers = new ArrayList<>();
	}
	
	/**
	 * Processes inputs and releases
	 */
	public void update() {
		while (!inputs.isEmpty()) {
			TargetInputEvent e = inputs.poll();
			for (int i = 0; i < handlers.size(); i++) {
				PlayerInput in = handlers.get(i);
				if (in.id == e.target) {
					in.handleInput(e);
				}
			}
		}
		
		while (!releases.isEmpty()) {
			TargetInputEvent e = releases.poll();
			for (int i = 0; i < handlers.size(); i++) {
				PlayerInput in = handlers.get(i);
				if (in.id == e.target) {
					in.handleInputReleased(e);
				}
			}
		}
		
		for (int i = 0; i < handlers.size(); i++) {
			handlers.get(i).update();
		}
	}
}
