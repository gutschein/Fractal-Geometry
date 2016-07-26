package de.lennartmeinhardt.math;

import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.DiscreteDoubleConsumer2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.FlatArrayDiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.OperableDiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.ifs.ProbabilityIteratedFunctionSystem;
import de.lennartmeinhardt.util.buffer.BaseDoubleBuffer;
import de.lennartmeinhardt.util.buffer.DoubleBuffer;

/**
 * A transformer acting on {@link FlatArrayDiscreteDoubleMap2D}s.
 * 
 * @author Lennart Meinhardt
 */
public class DiscreteDoubleMapTransformer2D
extends DoubleBufferTransformer<FlatArrayDiscreteDoubleMap2D, OperableDiscreteDoubleMap2D<?>, ProbabilityIteratedFunctionSystem<? extends DiscreteTransformation2D>> {
	
	/**
	 * Create a new {@link DiscreteDoubleMapTransformer2D} with given buffer.
	 * 
	 * @param buffer the buffer to use
	 */
	public DiscreteDoubleMapTransformer2D(DoubleBuffer<FlatArrayDiscreteDoubleMap2D> buffer) {
		super(buffer);
	}
	
	/**
	 * Create a new {@link DiscreteDoubleMapTransformer2D} with given maps.
	 * 
	 * @param map1 the first map
	 * @param map2 the second map
	 */
	public DiscreteDoubleMapTransformer2D(FlatArrayDiscreteDoubleMap2D map1, FlatArrayDiscreteDoubleMap2D map2) {
		this(new BaseDoubleBuffer<>(map1, map2));
	}
	
	/**
	 * Creates a new {@link DiscreteDoubleMapTransformer2D} with given bounds.
	 * 
	 * @param bounds the maps' bounds
	 */
	public DiscreteDoubleMapTransformer2D(DiscreteRectangle bounds) {
		this(new FlatArrayDiscreteDoubleMap2D(bounds), new FlatArrayDiscreteDoubleMap2D(bounds));
	}
	

	@Override protected void clear(FlatArrayDiscreteDoubleMap2D object) {
		object.setToZero();
	}
	
	@Override protected void copySourceToDestination(OperableDiscreteDoubleMap2D<?> source, FlatArrayDiscreteDoubleMap2D destination) {
		source.forEach(destination::setValueAt);
	}
	
	@Override protected void transformSourceToDestination(FlatArrayDiscreteDoubleMap2D source, FlatArrayDiscreteDoubleMap2D destination, ProbabilityIteratedFunctionSystem<? extends DiscreteTransformation2D> ifs) {
		DiscreteDoubleConsumer2D addToDest = destination::addToValueAt;
		for(int i = 0; i < ifs.size(); i++) {
			double scale = ifs.getProbability(i);
			DiscreteTransformation2D transformation = ifs.getTransformation(i);
			DiscreteDoubleConsumer2D fullOp = addToDest.preTransformPoint(transformation).preTransformValue(d -> d * scale).whenValueSatisfies(d -> d != 0);
			source.forEach(fullOp);
		}
	}
}
