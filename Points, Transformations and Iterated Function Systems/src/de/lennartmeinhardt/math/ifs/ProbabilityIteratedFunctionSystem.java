package de.lennartmeinhardt.math.ifs;

/**
 * An {@link IteratedFunctionSystem} that has probabilities assigned to the transformations.
 * 
 * @author Lennart Meinhardt
 *
 * @param <T> the type of transformations
 */
public interface ProbabilityIteratedFunctionSystem <T> extends IteratedFunctionSystem<T> {
	
	/**
	 * Get the probability for the transformation of given index.
	 * 
	 * @param index the index
	 * @return probability the transformation
	 */
	double getProbability(int index);
}
