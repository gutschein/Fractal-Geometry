package de.lennartmeinhardt.math.fractalgeom.calculating;

import java.util.function.IntConsumer;

import de.lennartmeinhardt.math.DiscreteDoubleMapTransformer2D;
import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.FlatArrayDiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.OperableDiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.ifs.ProbabilityIteratedFunctionSystem;

/**
 * A {@link DiscreteDeterministicMeasureAttractorCalculator2D} calculates an approximation for a measure attractor. 
 * 
 * @author Lennart Meinhardt
 */
public class DiscreteDeterministicMeasureAttractorCalculator2D {

	// the IFS whose set attractor will be approximated
	private final ProbabilityIteratedFunctionSystem<? extends DiscreteTransformation2D> ifs;
	private final DiscreteDoubleMapTransformer2D mapTransformer;

	
	/**
	 * Create a new {@link DiscreteDeterministicMeasureAttractorCalculator2D} with given IFS and bounds.
	 * 
	 * @param ifs the IFS whose measure attractor will be approximated
	 * @param bounds measure set attractor's bounds
	 */
	public DiscreteDeterministicMeasureAttractorCalculator2D(ProbabilityIteratedFunctionSystem<? extends DiscreteTransformation2D> ifs, DiscreteRectangle bounds) {
		this.mapTransformer = new DiscreteDoubleMapTransformer2D(bounds);
		this.ifs = ifs;
	}
	
	
	/**
	 * Reset the calculation.
	 * 
	 * @param startMap the map to use as start.
	 */
	public void reset(OperableDiscreteDoubleMap2D<?> startMap) {
		mapTransformer.reset(startMap);
	}
	
	/**
	 * Calculate a number of iterations and performs an operation on the current iteration number.
	 * 
	 * @param iterations the number of iterations to transform the map
	 * @param operation the operation to perform
	 */
	public void calculate(int iterations, IntConsumer operation) {
		for(int i = 0; i < iterations; i++) {
			operation.accept(i);
			mapTransformer.transform(ifs);
		}
	}
	
	/**
	 * Calculate a number of iterations and performs an operation on the current iteration number.
	 * 
	 * @param iterations the number of iterations to transform the map
	 */
	public void calculate(int iterations) {
		calculate(iterations, i -> {});
	}
	
	/**
	 * The calculated measure attractor approximation.
	 * 
	 * @return the approximation calculated so far
	 */
	public FlatArrayDiscreteDoubleMap2D getMeasureMap() {
		return mapTransformer.get();
	}
}
