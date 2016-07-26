package de.lennartmeinhardt.math;

import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.set.DiscreteConsumer2D;
import de.lennartmeinhardt.math.discrete2d.set.IntBackedDiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.ifs.IteratedFunctionSystem;
import de.lennartmeinhardt.util.buffer.BaseDoubleBuffer;
import de.lennartmeinhardt.util.buffer.DoubleBuffer;

/**
 * A transformer acting on {@link IntBackedDiscreteSet2D}s.
 * 
 * @author Lennart Meinhardt
 */
public class DiscreteSetTransformer2D
extends DoubleBufferTransformer<IntBackedDiscreteSet2D, OperableDiscreteSet2D, IteratedFunctionSystem<? extends DiscreteTransformation2D>> {
	
	/**
	 * Create a new {@link IntBackedDiscreteSet2D} with given buffer.
	 * 
	 * @param buffer the buffer to use
	 */
	public DiscreteSetTransformer2D(DoubleBuffer<? extends IntBackedDiscreteSet2D> buffer) {
		super(buffer);
	}
	
	/**
	 * Create a new {@link IntBackedDiscreteSet2D} with given maps.
	 * 
	 * @param set1 the first set
	 * @param set2 the second set
	 */
	public DiscreteSetTransformer2D(IntBackedDiscreteSet2D set1, IntBackedDiscreteSet2D set2) {
		this(new BaseDoubleBuffer<>(set1, set2));
	}
	
	/**
	 * Creates a new {@link IntBackedDiscreteSet2D} with given bounds.
	 * 
	 * @param bounds the sets' bounds
	 */
	public DiscreteSetTransformer2D(DiscreteRectangle bounds) {
		this(new IntBackedDiscreteSet2D(bounds), new IntBackedDiscreteSet2D(bounds));
	}
	

	@Override protected void clear(IntBackedDiscreteSet2D object) {
		object.removeAll();
	}
	
	@Override protected void copySourceToDestination(OperableDiscreteSet2D source, IntBackedDiscreteSet2D destination) {
		source.forEachPoint(destination::addPointAt);
	}
	
	@Override protected void transformSourceToDestination(IntBackedDiscreteSet2D source, IntBackedDiscreteSet2D destination, IteratedFunctionSystem<? extends DiscreteTransformation2D> ifs) {
		DiscreteConsumer2D addToDest = destination::addPointAt;
		for(DiscreteTransformation2D transformation : ifs) {
			DiscreteConsumer2D transformSourceAddToDest = addToDest.preTransform(transformation);
			source.forEachPoint(transformSourceAddToDest);
		}
	}
}
