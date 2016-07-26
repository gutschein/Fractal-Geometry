package de.lennartmeinhardt.util.buffer;


/**
 * Base implementation of a {@link DoubleBuffer}.
 * 
 * @author Lennart Meinhardt
 *
 * @param <T> the type of objects in the buffer
 */
public class BaseDoubleBuffer <T> implements DoubleBuffer<T> {
	
	// the stored objects
	private final T object1;
	private final T object2;
	
	// the currently active object's index
	private int currentActiveIndex;
	
	
	/**
	 * Create a new {@link BaseDoubleBuffer} with given objects.
	 * 
	 * @param object1 the initially active object
	 * @param object2 the initially inactive object
	 */
	public BaseDoubleBuffer(T object1, T object2) {
		this.object1 = object1;
		this.object2 = object2;
		currentActiveIndex = 1;
	}
	
	@Override public void swap() {
		currentActiveIndex = 3 - currentActiveIndex;
	}
	
	@Override public T getActiveObject() {
		return getObject(currentActiveIndex);
	}
	
	@Override public T getInactiveObject() {
		return getObject(3 - currentActiveIndex);
	}
	
	/**
	 * Get the object with given index.
	 * 
	 * @param number the index
	 * @return the object with given
	 */
	private T getObject(int number) {
		if(number == 1)
			return object1;
		else if(number == 2)
			return object2;
		
		throw new Error("Object with wrong number selected: " + currentActiveIndex);
	}
}
