package de.lennartmeinhardt.math.util;

import java.util.Random;

/**
 * A {@link RangeRandomIntGenerator} using {@link Random#nextInt(int)} (pseudo-random number generator).
 * 
 * @author Lennart Meinhardt
 */
public class BaseRandomRangeIntGenerator implements RangeRandomIntGenerator {
	
	// instance that may be used instead of creating new ones
	public static final RangeRandomIntGenerator INSTANCE = new BaseRandomRangeIntGenerator();
	
	
	// the random number generator
	private final Random random = new Random();
	
	
	@Override public int nextInt(int maxExcl) {
		return random.nextInt(maxExcl);
	}
}
