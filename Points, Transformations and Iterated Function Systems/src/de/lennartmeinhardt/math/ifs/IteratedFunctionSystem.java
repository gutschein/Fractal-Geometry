package de.lennartmeinhardt.math.ifs;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * An {@link IteratedFunctionSystem} contains transformations.
 * 
 * @author Lennart Meinhardt
 *
 * @param <T> the type of transformations
 */
public interface IteratedFunctionSystem <T> extends Iterable<T> {
	
	/**
	 * Get the transformation of given index.
	 * 
	 * @param index the index
	 * @return the transformation at specified position
	 */
	T getTransformation(int index);
	
	/**
	 * Get the number of transformations.
	 * 
	 * @return the number of transformations
	 */
	int size();
	
	
	/**
	 * Get a {@link ProbabilityIteratedFunctionSystem} with equal probabilities.
	 * 
	 * @return {@link ProbabilityIteratedFunctionSystem} with equal probabilities
	 */
	default ProbabilityIteratedFunctionSystem<T> withEqualProbabilities() {
		return new EqualProbabilitiesIteratedFunctionSystem<>(this);
	}
	
	/**
	 * Create a {@link Stream} that contains the transformations of this IFS.
	 * 
	 * @return a stream of this IFS's transformations
	 */
	default Stream<T> stream() {
		return IntStream.range(0, size()).mapToObj(this::getTransformation);
	}
}
