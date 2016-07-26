package de.lennartmeinhardt.math.point;

/**
 * This object has a dimension.
 * 
 * @author Lennart Meinhardt
 */
public interface Dimensionable {

	/**
	 * Get the dimension.
	 * 
	 * @return the dimension
	 */
	int getDimension();
	
	
	/**
	 * Check if the given object is of the same dimension as this one.
	 * 
	 * @param d the object to check
	 * @return the dimension if the dimensions coincide
	 * @throws DimensionException if the dimensions do not coincide
	 */
	default int requireSameDimension(Dimensionable d) throws DimensionException {
		return requireSameDimension(d.getDimension());
	}
	
	/**
	 * Check if this dimension equals the given one.
	 * 
	 * @param dimension the dimension to check
	 * @return the dimension if the dimensions coincide
	 * @throws DimensionException if the dimensions do not coincide
	 */
	default int requireSameDimension(int dimension) throws DimensionException {
		if(getDimension() != dimension)
			throw new DimensionException(getDimension(), dimension);
		return dimension;
	}
}
