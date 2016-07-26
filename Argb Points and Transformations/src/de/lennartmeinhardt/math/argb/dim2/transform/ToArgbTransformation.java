package de.lennartmeinhardt.math.argb.dim2.transform;

import de.lennartmeinhardt.argb.ArgbHolder;

/**
 * Maps {@link ArgbHolder}s to argb values.
 * 
 * @author Lennart Meinhardt
 *
 * @param <P> the type of point that can be transformed
 */
@FunctionalInterface
public interface ToArgbTransformation <P> {

	/**
	 * Transforms an {@link ArgbHolder} to an argb color. 
	 * 
	 * @param argbPoint the point to map
	 * @return the transformed argb color
	 */
	int transformArgb(P argbPoint);
}
