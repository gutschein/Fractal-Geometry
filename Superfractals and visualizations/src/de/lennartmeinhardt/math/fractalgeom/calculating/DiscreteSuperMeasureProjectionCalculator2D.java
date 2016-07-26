package de.lennartmeinhardt.math.fractalgeom.calculating;

import java.util.function.IntConsumer;

import de.lennartmeinhardt.math.DiscreteDoubleMapTransformer2D;
import de.lennartmeinhardt.math.TransformerChaosGamePlayer;
import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.FlatArrayDiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.OperableDiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.ifs.ChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.ProbabilityIteratedFunctionSystem;

/**
 * The {@link DiscreteSuperMeasureProjectionCalculator2D} computes an approximation for the super-measure projection.
 * 
 * @author Lennart Meinhardt
 */
public class DiscreteSuperMeasureProjectionCalculator2D {
	
	private final TransformerChaosGamePlayer<FlatArrayDiscreteDoubleMap2D, OperableDiscreteDoubleMap2D<?>, ? extends ProbabilityIteratedFunctionSystem<? extends DiscreteTransformation2D>> chaosGamePlayer;
	// the overlay that approximates 
	private final FlatArrayDiscreteDoubleMap2D overlayMap;
	private int iterationsCount;

	
	/**
	 * Create a new {@link DiscretePointwiseFrequencyCounter2D} with given superIFS and bounds.
	 * 
	 * @param superIfs the superIFS to use
	 * @param bounds the bounds to use for overlay map and transformed maps
	 */
	public DiscreteSuperMeasureProjectionCalculator2D(ChoiceGeneratorIteratedFunctionSystem<? extends ProbabilityIteratedFunctionSystem<? extends DiscreteTransformation2D>> superIfs, DiscreteRectangle bounds) {
		DiscreteDoubleMapTransformer2D mapTransformer = new DiscreteDoubleMapTransformer2D(bounds);
		this.chaosGamePlayer = new TransformerChaosGamePlayer<>(mapTransformer, superIfs);
		this.overlayMap = new FlatArrayDiscreteDoubleMap2D(bounds);
	}
	
	
	/**
	 * Reset the calculation.
	 * 
	 * @param startMap the start map to use for transforming
	 */
	public void reset(OperableDiscreteDoubleMap2D<?> startMap) {
		chaosGamePlayer.resetStartPoint(startMap);
		overlayMap.setToZero();
		iterationsCount = 0;
	}
	
	/**
	 * Calculate a number of iterations.
	 * In each iteration the last map is transformed and added to an overlay map.
	 * 
	 * @param iterations the iterations to run
	 */
	public void calculate(int iterations) {
		calculate(iterations, i -> {});
	}
	
	/**
	 * Calculate a number of iterations and run an operation on the iteration number.
	 * In each iteration the last map is transformed and added to an overlay map.
	 * 
	 * @param iterations the iterations to run
	 * @param operation the operation to perform
	 */
	public void calculate(int iterations, IntConsumer operation) {
		for(int i = 0; i < iterations; i++) {
			operation.accept(i);
			chaosGamePlayer.next();
			chaosGamePlayer.getLastPoint().forEach(overlayMap::addToValueAt);
			iterationsCount++;
		}
	}
	
	/**
	 * Get the overlay map calculated.
	 * 
	 * @return the overlay map
	 */
	public FlatArrayDiscreteDoubleMap2D getOverlayMeasureMap() {
		return overlayMap;
	}
	
	/**
	 * Get the total number of iterations run.
	 * 
	 * @return the number of iterations
	 */
	public int getIterationsCount() {
		return iterationsCount;
	}
}
