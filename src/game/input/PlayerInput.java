/**
 * 
 */
package game.input;

import game.entities.Entity;

/**
 * @author Davis
 *
 */
public abstract class PlayerInput {
	
	public Entity target;
	public int id;

	/**
	 * @param target
	 */
	public PlayerInput(Entity target) {
		super();
		this.target = target;
	}
	
	/**
	 * handles inputs
	 * @param in
	 * @return
	 */
	public abstract boolean handleInput(TargetInputEvent in);

	/**
	 * handles releases
	 * @param in
	 * @return
	 */
	public abstract boolean handleInputReleased(TargetInputEvent in);
	
	/**
	 * Do something based on input
	 */
	public abstract void update();
	
}
