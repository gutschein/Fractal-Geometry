package de.lennartmeinhardt.math.ifs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * A {@link List}-based {@link IteratedFunctionSystem}.
 * 
 * @author Lennart Meinhardt
 *
 * @param <T> the type of transformations
 */
public class ListIteratedFunctionSystem <T> implements IteratedFunctionSystem<T> {

	// the transformations of this IFS
	private final List<T> transformations;
	
	
	/**
	 * Create a new {@link ListIteratedFunctionSystem} with given transformations.
	 * 
	 * @param transformations the transformations to use
	 */
	@SafeVarargs
	public ListIteratedFunctionSystem(T... transformations) {
		this(Arrays.asList(transformations));
	}
	
	/**
	 * Create a new {@link ListIteratedFunctionSystem} with given transformations list.
	 * 
	 * @param transformations the transformations list to use
	 */
	public ListIteratedFunctionSystem(List<T> transformations) {
		this.transformations = transformations;
	}
	
	
	/**
	 * Get the transformations as {@link List} object.
	 * 
	 * @return the transformations as list
	 */
	public List<T> getTransformationList() {
		return transformations;
	}
	
	@Override public T getTransformation(int index) {
		return transformations.get(index);
	}
	
	@Override public int size() {
		return transformations.size();
	}
	
	@Override public Iterator<T> iterator() {
		return transformations.iterator();
	}
}
