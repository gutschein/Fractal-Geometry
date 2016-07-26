package de.lennartmeinhardt.math.ifs;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * An {@link IteratedFunctionSystem} that can create random choices.
 * 
 * @author Lennart Meinhardt
 *
 * @param <T> the type of transformations
 */
public interface ChoiceGeneratorIteratedFunctionSystem <T> extends IteratedFunctionSystem<T> {
	
	/**
	 * Get a random chioce.
	 * 
	 * @return random choice
	 */
	int getRandomChoice();
	
	
	/**
	 * Get a random transformation of this IFS.
	 * 
	 * @return a random transformation
	 */
	default T getRandomTransformation() {
		return getTransformation(getRandomChoice());
	}
	
	/**
	 * Get a {@link Stream} of random transformations.
	 * 
	 * @param size the stream's size
	 * @return stream of random transformations
	 */
	default Stream<T> createRandomTransformationStream(int size) {
		return getRandomChoiceStream(size).mapToObj(this::getTransformation);
	}
	
	/**
	 * Get an {@link IntStream} of random choices.
	 * 
	 * @param size the stream's size
	 * @return stream of random choices
	 */
	default IntStream getRandomChoiceStream(int size) {
		return IntStream.range(0, size).map(i -> getRandomChoice());
	}
}
