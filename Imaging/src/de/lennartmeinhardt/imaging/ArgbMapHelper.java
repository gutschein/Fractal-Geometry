package de.lennartmeinhardt.imaging;

import de.lennartmeinhardt.argb.ArgbColors;
import de.lennartmeinhardt.math.discrete2d.doublemap.DiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.intmap.DiscreteIntMap2D;

/**
 * Contains helper methods for creating grayscale argb {@link DiscreteIntMap2D}s from relative maps.
 * {@link Double} values in [0, 1] can be mapped to {@link Integer}s in [0, 255], these are considered color channel values.
 * The color channel values are then being used as red, green, and blue values in the argb colors, yielding argb colors without hue and saturation.
 * 
 * @author Lennart Meinhardt
 */
public class ArgbMapHelper {

	/**
	 * Create a single channel {@link DiscreteIntMap2D} from a given map with values in [0, 1].
	 * Each value is mapped to the corresponding integer color channel value in [0, 255].
	 * 
	 * @param frequencyMap the relative map
	 * @return the channel map
	 */
	public static DiscreteIntMap2D relativeMapToChannelMap(DiscreteDoubleMap2D frequencyMap) {
		return frequencyMap.pushForwardToInt(ArgbColors::channelRelativeToAbsolute);
	}
	
	/**
	 * Create an argb color map from a single channel map, having grayscale values.
	 * 
	 * @param channelMap the single channel map
	 * @return grayscale argb color map
	 */
	public static DiscreteIntMap2D channelMapToArgb(DiscreteIntMap2D channelMap) {
		return channelMap.pushForward(ArgbMapHelper::argbFromChannelOpaque);
	}
	
	/**
	 * Get grayscale argb value from single channel value.
	 * 
	 * @param channel the channel value
	 * @return the argb color
	 */
	private static int argbFromChannelOpaque(int channel) {
		return ArgbColors.argb(0xff, channel, channel, channel);
	}
	
	/**
	 * Exchanges minimum and maximum values of a single channel map.
	 * Bright points become darker and dark points become brighter.
	 * 
	 * @param channelMap the single channel map
	 * @return channel map with interchanged minimum and maximum
	 */
	public static DiscreteIntMap2D swapChannelMinMax(DiscreteIntMap2D channelMap) {
		return channelMap.pushForward(i -> 255 - i);
	}
	
	/**
	 * Concatenation of the other methods.
	 * 
	 * @param relativeMap the map with {@link Double} values in [0, 1]
	 * @return the map with values being grayscale argb colors
	 */
	public static DiscreteIntMap2D relativeToGrayscaleArgbMap(DiscreteDoubleMap2D relativeMap) {
		DiscreteIntMap2D channelMap = ArgbMapHelper.relativeMapToChannelMap(relativeMap);
		channelMap = ArgbMapHelper.swapChannelMinMax(channelMap);
		DiscreteIntMap2D colorMap = ArgbMapHelper.channelMapToArgb(channelMap);
		return colorMap;
	}
	
	
	// no instances
	private ArgbMapHelper() {
	}
}
