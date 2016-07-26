package de.lennartmeinhardt.math.ifs;

import java.util.List;

import de.lennartmeinhardt.math.util.RangeRandomIntGenerator;

/**
 * A {@link ProbabilityIteratedFunctionSystem} that assigns equal probabilities to the transformations and creates random choices with equal probabilities.
 * 
 * @author Lennart Meinhardt
 *
 * @param <T> the type of transformations
 */
public class EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem <T> extends EqualProbabilitiesIteratedFunctionSystem<T> implements ChoiceGeneratorIteratedFunctionSystem<T> {

	// the choice generator
	private final RangeRandomIntGenerator randomGenerator;
	
	
	/**
	 * Create a new {@link EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem} with given IFS and random number generator.
	 * 
	 * @param ifs the IFS that has transformation
	 * @param randomGenerator the random number generator
	 */
	public EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem(IteratedFunctionSystem<T> ifs, RangeRandomIntGenerator randomGenerator) {
		super(ifs);
		this.randomGenerator = randomGenerator;
	}
	
	/**
	 * Create a new {@link EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem} with given transformations list and random generator.
	 * 
	 * @param transformations the transformations list
	 * @param randomGenerator the random generator
	 */
	public EqualProbabilitiesChoiceGeneratorIteratedFunctionSystem(List<T> transformations, RangeRandomIntGenerator randomGenerator) {
		this(new ListIteratedFunctionSystem<>(transformations), randomGenerator);
	}
	
	
	@Override public int getRandomChoice() {
		return randomGenerator.nextInt(size());
	}
}
