package de.lennartmeinhardt.util.buffer;

/**
 * A double buffer holds two objects; an active and an inactive one. They can be swapped.
 * 
 * @author Lennart Meinhardt
 *
 * @param <T> the type of objects in the buffer
 */
public interface DoubleBuffer <T> {
	
	/**
	 * Swap the active and inactive objects.
	 */
	void swap();
	
	/**
	 * Get the currently active object.
	 * 
	 * @return the active object
	 */
	T getActiveObject();
	
	/**
	 * Get the currently inactive object.
	 * 
	 * @return inactive object
	 */
	T getInactiveObject();
}
