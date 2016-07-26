package de.lennartmeinhardt.math.ifs;

import java.util.Iterator;

/**
 * A {@link ProbabilityIteratedFunctionSystem} that assigns equal probabilities to the transformations.
 * 
 * @author Lennart Meinhardt
 *
 * @param <T> the type of transformations
 */
public class EqualProbabilitiesIteratedFunctionSystem <T> implements ProbabilityIteratedFunctionSystem<T> {
	
	// the IFS that has the transformations
	private IteratedFunctionSystem<T> baseIfs;


	/**
	 * Create a new {@link EqualProbabilitiesIteratedFunctionSystem} with given base IFS.
	 * 
	 * @param baseIfs the ifs that has the transformations
	 */
	public EqualProbabilitiesIteratedFunctionSystem(IteratedFunctionSystem<T> baseIfs) {
		this.baseIfs = baseIfs;
	}
	
	
	@Override public T getTransformation(int index) {
		return baseIfs.getTransformation(index);
	}
	
	@Override public final int size() {
		return baseIfs.size();
	}
	
	@Override public final double getProbability(int index) {
		return 1. / size();
	}
	
	@Override public Iterator<T> iterator() {
		return baseIfs.iterator();
	}
}
