package de.lennartmeinhardt.math.util;

/**
 * This class provides methods for mathematical tasks. 
 * 
 * @author Lennart Meinhardt
 */
public class MathUtil {
	
	/**
	 * Computes the inverse cosine of the point (x, y) as radiant.
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return radiant inverse cosine of (x, y)
	 */
	public static double arccosRad(double x, double y) {
		// Get regular arccos
		double arccos = Math.acos(x / Math.sqrt(x*x + y*y));
		// Make it work for angles > pi
		if(y < 0)
			arccos = 2 * Math.PI - arccos;
		return arccos;
	}
	
	/**
	 * Given a degree double value this method returns a mod 360 equivalent value between -180 and 180.
	 * 
	 * @param deg input degree
	 * @return mod 360 equivalent value between -180 and 180
	 */
	public static double toDegDomain(double deg) {
		// Make deg be <= 180
		while(deg > 180)
			deg -= 360;
		// Make deg be >= -180
		while(deg < -180)
			deg += 360;
		// Return corrected value
		return deg;
	}
	
	/**
	 * Converts a radiant value to a degree value.
	 * 
	 * @param rad radiant value
	 * @return degree value
	 */
	public static double radToDeg(double rad) {
		return rad * 180 / Math.PI;
	}
	
	/**
	 * Computes the inverse cosine of the point (x, y) as degree value.
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return degree inverse cosine or (x, y)
	 */
	public static double arccosDeg(double x, double y) {
		// Get radiant cosine, convert it to degrees and move it to the correct interval.
		return toDegDomain(radToDeg(arccosRad(x, y)));
	}
	
	/**
	 * Determines the greatest common divisor using the Euclidean algorithm.
	 * 
	 * @param a first number
	 * @param b second number
	 * @return greatest common divisors of a and b
	 */
	public static int gcdEuclid(int a, int b) {
		// If a or b are 0, return the other one
		if(a == 0)
			return b;
		if(b == 0)
			return a;
		// If a is greater, subtract b from a
		if(a > b)
			return gcdEuclid(a - b, b);
		// Else subtract a from b
		else
			return gcdEuclid(a, b - a);
	}
	
	/**
	 * Get the euclidean norm of the point (x, y).
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return euclidean norm of (x, y)
	 */
	public static double getVectorNorm2(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}
	
	
	// no instances
	private MathUtil() {}
}
