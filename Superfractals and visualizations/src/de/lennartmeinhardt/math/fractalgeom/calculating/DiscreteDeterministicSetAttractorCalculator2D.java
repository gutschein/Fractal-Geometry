package de.lennartmeinhardt.math.fractalgeom.calculating;

import de.lennartmeinhardt.math.DiscreteSetTransformer2D;
import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.set.IntBackedDiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.ifs.IteratedFunctionSystem;

/**
 * A {@link DiscreteDeterministicSetAttractorCalculator2D} calculates an approximation for a set attractor. 
 * 
 * @author Lennart Meinhardt
 */
public class DiscreteDeterministicSetAttractorCalculator2D {
	
	// the IFS whose set attractor will be approximated
	private final IteratedFunctionSystem<? extends DiscreteTransformation2D> ifs;
	private final DiscreteSetTransformer2D setTransformer;

	
	/**
	 * Create a new {@link DiscreteDeterministicSetAttractorCalculator2D} with given IFS and bounds.
	 * 
	 * @param ifs the IFS whose set attractor will be approximated
	 * @param bounds the set attractor's bounds
	 */
	public DiscreteDeterministicSetAttractorCalculator2D(IteratedFunctionSystem<? extends DiscreteTransformation2D> ifs, DiscreteRectangle bounds) {
		this.setTransformer = new DiscreteSetTransformer2D(bounds);
		this.ifs = ifs;
	}
	
	
	/**
	 * Reset the calculation.
	 * 
	 * @param startSet the set to use as start.
	 */
	public void reset(OperableDiscreteSet2D startSet) {
		setTransformer.reset(startSet);
	}
	
	/**
	 * Calculate a number of iterations.
	 * 
	 * @param iterations the number of iterations to transform the set
	 */
	public void calculate(int iterations) {
		for(int i = 0; i < iterations; i++)
			setTransformer.transform(ifs);
	}
	
	/**
	 * The calculated set attractor approximation.
	 * 
	 * @return the approximation calculated so far
	 */
	public IntBackedDiscreteSet2D getDiscreteSet() {
		return setTransformer.get();
	}
}
