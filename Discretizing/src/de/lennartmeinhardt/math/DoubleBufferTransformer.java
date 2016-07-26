package de.lennartmeinhardt.math;

import de.lennartmeinhardt.util.buffer.DoubleBuffer;

/**
 * A {@link Transformer} that transforms a {@link DoubleBuffer}. Used for sets and maps.
 * The active object is transformed to the inactive one, then the buffer is swapped.
 * 
 * @author Lennart Meinhardt
 *
 * @param <P> the type of object being transformed
 * @param <Q> the type of objects to reset
 * @param <T> the type of transformations that can be used
 */
public abstract class DoubleBufferTransformer <P, Q, T> implements Transformer<P, Q, T> {

	// the buffer
	private final DoubleBuffer<? extends P> buffer;
	
	
	/**
	 * Create a new {@link DoubleBufferTransformer} with given buffer.
	 * 
	 * @param buffer the buffer to use
	 */
	public DoubleBufferTransformer(DoubleBuffer<? extends P> buffer) {
		this.buffer = buffer;
	}
	
	
	@Override public P get() {
		return buffer.getActiveObject();
	}
	
	@Override public void transform(T transformation) {
		clear(buffer.getInactiveObject());
		transformSourceToDestination(buffer.getActiveObject(), buffer.getInactiveObject(), transformation);
		buffer.swap();
	}
	
	@Override public void reset(Q startPoint) {
		clear(buffer.getInactiveObject());
		copySourceToDestination(startPoint, buffer.getInactiveObject());
		buffer.swap();
	}
	
	/**
	 * Transform one object to the other one with given transformation.
	 * 
	 * @param source the source object
	 * @param destination the destination object
	 * @param transformation the transformation to use
	 */
	protected abstract void transformSourceToDestination(P source, P destination, T transformation);
	
	/**
	 * Clear an object. Called on the inactive object before transforming.
	 * 
	 * @param object the object to clear
	 */
	protected abstract void clear(P object);
	
	/**
	 * Copy the source object to the destination object.
	 * 
	 * @param source the source object
	 * @param destination the destination object
	 */
	protected abstract void copySourceToDestination(Q source, P destination);
}
