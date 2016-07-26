package de.lennartmeinhardt.math.point;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * A point has a dimension and coordinates.
 * 
 * @author Lennart Meinhardt
 */
public interface Point extends Dimensionable {
	
	/**
	 * Get the coordinate of given index.
	 * 
	 * @param index the index
	 * @return the coordinate of given index
	 */
	double getCoordinate(int index);
	
	
	/**
	 * Get this point's coordinates as stream.
	 * 
	 * @return the coordinates as stream
	 */
	default DoubleStream getCoordinatesStream() {
		return IntStream.range(0, getDimension()).mapToDouble(this::getCoordinate);
	}
	
	/**
	 * Convert this point to an array.
	 * 
	 * @return array of this point's coordinates
	 */
	default double[] toArray() {
		return getCoordinatesStream().toArray();
	}
}
