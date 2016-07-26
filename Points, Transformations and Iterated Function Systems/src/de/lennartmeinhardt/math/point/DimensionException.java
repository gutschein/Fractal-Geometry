package de.lennartmeinhardt.math.point;

/**
 * An {@link Exception} thrown if dimensions don't coincide when they should.
 * See {@link Dimensionable#requireSameDimension(Dimensionable)} and {@link Dimensionable#requireSameDimension(int)}.
 * 
 * @author Lennart Meinhardt
 */
@SuppressWarnings("serial")
public class DimensionException extends RuntimeException {
	
	/**
	 * Create a new {@link DimensionException}.
	 */
	public DimensionException() {
	}
	
	/**
	 * Create a new {@link DimensionException} with given message.
	 * 
	 * @param msg the message
	 */
	public DimensionException(String msg) {
		super(msg);
	}
	
	/**
	 * Create a new {@link DimensionException} with given expected and actual dimensions.
	 * 
	 * @param expected the expected dimension
	 * @param actual the actual dimension
	 */
	public DimensionException(int expected, int actual) {
		this("expected dimension " + expected + ", given dimension " + actual);
	}
}
