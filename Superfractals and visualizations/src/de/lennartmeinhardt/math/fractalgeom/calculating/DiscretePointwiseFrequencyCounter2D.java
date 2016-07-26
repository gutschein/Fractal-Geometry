package de.lennartmeinhardt.math.fractalgeom.calculating;

import java.util.function.IntConsumer;

import de.lennartmeinhardt.math.DiscreteSetTransformer2D;
import de.lennartmeinhardt.math.TransformerChaosGamePlayer;
import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.intmap.FlatArrayDiscreteIntMap2D;
import de.lennartmeinhardt.math.discrete2d.set.IntBackedDiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.ifs.ChoiceGeneratorIteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.IteratedFunctionSystem;

/**
 * The {@link DiscretePointwiseFrequencyCounter2D} computes an approximation for the point-wise frequency map.
 * 
 * @author Lennart Meinhardt
 */
public class DiscretePointwiseFrequencyCounter2D {
	
	private final TransformerChaosGamePlayer<IntBackedDiscreteSet2D, OperableDiscreteSet2D, ? extends IteratedFunctionSystem<? extends DiscreteTransformation2D>> chaosGamePlayer;
	// the point-wise frequency map approximation
	private final FlatArrayDiscreteIntMap2D frequencyMap;
	private int iterationsCount;


	/**
	 * Create a new {@link DiscretePointwiseFrequencyCounter2D} with given superIFS and bounds.
	 * 
	 * @param superIfs the superIFS to use
	 * @param bounds the bounds to use for frequency map and transformed sets
	 */
	public DiscretePointwiseFrequencyCounter2D(ChoiceGeneratorIteratedFunctionSystem<? extends IteratedFunctionSystem<? extends DiscreteTransformation2D>> superIfs, DiscreteRectangle bounds) {
		DiscreteSetTransformer2D setTransformer = new DiscreteSetTransformer2D(bounds);
		this.chaosGamePlayer = new TransformerChaosGamePlayer<>(setTransformer, superIfs);
		this.frequencyMap = new FlatArrayDiscreteIntMap2D(bounds);
	}
	
	
	/**
	 * Reset the calculation.
	 * 
	 * @param startSet the start set to use for transforming
	 */
	public void reset(OperableDiscreteSet2D startSet) {
		chaosGamePlayer.resetStartPoint(startSet);
		frequencyMap.setToZero();
		iterationsCount = 0;
	}
	
	/**
	 * Calculate a number of iterations.
	 * In each iteration the last set is transformed and for each point the frequency map value is increased.
	 * 
	 * @param iterations the iterations to run
	 */
	public void calculate(int iterations) {
		calculate(iterations, i -> {});
	}
	
	/**
	 * Calculate a number of iterations and run an operation on the current iteration number.
	 * In each iteration the last set is transformed and for each point the frequency map value is increased.
	 * 
	 * @param iterations the iterations to run
	 * @param operation the operation to perform
	 */
	public void calculate(int iterations, IntConsumer operation) {
		for(int i = 0; i < iterations; i++) {
			operation.accept(i);
			chaosGamePlayer.next();
			chaosGamePlayer.getLastPoint().forEachPoint(frequencyMap::incrementValueAt);
			iterationsCount++;
		}
	}
	
	/**
	 * Get the frequency map calculated.
	 * 
	 * @return the frequency map
	 */
	public FlatArrayDiscreteIntMap2D getFrequencyMap() {
		return frequencyMap;
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
