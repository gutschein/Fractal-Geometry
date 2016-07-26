package de.lennartmeinhardt.util;

/**
 * Helper for determining calculation durations.
 * 
 * @author Lennart Meinhardt
 */
public class TimingHelper {

	/**
	 * Get current system time.
	 * 
	 * @return current system time
	 */
	public static long t() {
		return System.currentTimeMillis();
	}
	
	/**
	 * Get distance between current and given time.
	 * 
	 * @param t the past time
	 * @return the distance between current and given time
	 */
	public static long d(long t) {
		return t() - t;
	}
	
	/**
	 * Used when starting a calculation. Sets a timestamp and writes to console.
	 * 
	 * @return current system time
	 */
	public static long startCalculation() {
		System.out.println("Starting calculation ...");
		return t();
	}
	
	/**
	 * Used when stopping a calculation. Writes duration to console.
	 * 
	 * @param t the past time
	 */
	public static void stopCalculation(long t) {
		long seconds = d(t) / 1000;
		System.out.println("Stopped calculation after " + seconds + " seconds.");
	}
	
	
	// No instances
	private TimingHelper() {
	}
}
