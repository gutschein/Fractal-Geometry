package de.lennartmeinhardt.argb;

import java.awt.Color;

/**
 * Contains methods for manipulating integer colors with channels alpha, red, green, blue.
 * Preset colors' rgb values may be obtained using {@link Color#getRGB()}, for example.
 * 
 * @author Lennart Meinhardt
 */
public class ArgbColors {
	
	// Channel offsets
	private static final int OFFSET_ALPHA = 24;
	private static final int OFFSET_RED = 16;
	private static final int OFFSET_GREEN = 8;
	private static final int OFFSET_BLUE = 0;
	// single channel bitmask
	private static final int CHANNEL_MASK = 0xff;
	
	/**
	 * Get the argb color from integer components in [0, 255] with transparency.
	 * 
	 * @param alpha alpha component
	 * @param red red component
	 * @param green green component
	 * @param blue blue component
	 * @return the argb color
	 */
	public static int argb(int alpha, int red, int green, int blue) {
		return (alpha << OFFSET_ALPHA)
				| (red << OFFSET_RED)
				| (green << OFFSET_GREEN)
				| (blue << OFFSET_BLUE);
	}
	
	/**
	 * Get the opaque argb color from integer components in [0, 255].
	 * 
	 * @param red red component
	 * @param green green component
	 * @param blue blue component
	 * @return the argb color
	 */
	public static int rgb(int red, int green, int blue) {
		return argb(CHANNEL_MASK, red, green, blue);
	}
	
	/**
	 * Get the argb color from relative components in [0, 1] with transparency.
	 * 
	 * @param alpha the relative alpha value
	 * @param red the relative red value
	 * @param green the relative green value
	 * @param blue the relative blue value
	 * @return the argb color
	 */
	public static int argb(double alpha, double red, double green, double blue) {
		int alphaAbs = channelRelativeToAbsolute(alpha);
		int redAbs = channelRelativeToAbsolute(red);
		int greenAbs = channelRelativeToAbsolute(green);
		int blueAbs = channelRelativeToAbsolute(blue);
		return argb(alphaAbs, redAbs, greenAbs, blueAbs);
	}
	
	/**
	 * Get the opaque argb color from relative components in [0, 1].
	 * 
	 * @param red the relative red value
	 * @param green the relative green value
	 * @param blue the relative blue value
	 * @return the argb color
	 */
	public static int rgb(double red, double green, double blue) {
		int redAbs = channelRelativeToAbsolute(red);
		int greenAbs = channelRelativeToAbsolute(green);
		int blueAbs = channelRelativeToAbsolute(blue);
		return rgb(redAbs, greenAbs, blueAbs);
	}
	
	/**
	 * Get the alpha value of the given argb color.
	 * 
	 * @param argb argb color
	 * @return the color's alpha value
	 */
	public static int getAlpha(int argb) {
		return getComponent(argb, OFFSET_ALPHA);
	}
	
	/**
	 * Get the red value of the given argb color.
	 * 
	 * @param argb argb color
	 * @return the color's red value
	 */
	public static int getRed(int argb) {
		return getComponent(argb, OFFSET_RED);
	}
	
	/**
	 * Get the green value of the given argb color.
	 * 
	 * @param argb argb color
	 * @return the color's green value
	 */
	public static int getGreen(int argb) {
		return getComponent(argb, OFFSET_GREEN);
	}
	
	/**
	 * Get the blue value of the given argb color.
	 * 
	 * @param argb argb color
	 * @return the color's blue value
	 */
	public static int getBlue(int argb) {
		return getComponent(argb, OFFSET_BLUE);
	}
	
	/**
	 * Get the color component with offset.
	 * 
	 * @param argb argb color
	 * @param offset component's offset
	 * @return the component value
	 */
	private static int getComponent(int argb, int offset) {
		return (argb >> offset) & CHANNEL_MASK;
	}

	/**
	 * Get the argb color with reset alpha value.
	 * 
	 * @param argb argb color
	 * @param alpha new alpha value
	 * @return the argb color with reset alpha
	 */
	public static int setAlpha(int argb, int alpha) {
		return argb(alpha, getRed(argb), getGreen(argb), getBlue(argb));
	}

	/**
	 * Get the argb color with reset red value.
	 * 
	 * @param argb argb color
	 * @param alpha new red value
	 * @return the argb color with reset red
	 */
	public static int setRed(int argb, int red) {
		return argb(getAlpha(argb), red, getGreen(argb), getBlue(argb));
	}

	/**
	 * Get the argb color with reset green value.
	 * 
	 * @param argb argb color
	 * @param alpha new green value
	 * @return the argb color with reset green
	 */
	public static int setGreen(int argb, int green) {
		return argb(getAlpha(argb), getRed(argb), green, getBlue(argb));
	}

	/**
	 * Get the argb color with reset blue value.
	 * 
	 * @param argb argb color
	 * @param alpha new blue value
	 * @return the argb color with reset blue
	 */
	public static int setBlue(int argb, int blue) {
		return argb(getAlpha(argb), getRed(argb), getGreen(argb), blue);
	}
	
	/**
	 * Interpolates two argb colors. A lower ratio makes the result closer to the first color.
	 * 
	 * @param argb1 the first color
	 * @param argb2 the second color
	 * @param ratio the position of the result between the colors
	 * @return the interpolated color between the input colors
	 */
	public static int interpolate(int argb1, int argb2, double ratio) {
		int alpha = interpolateChannel(getAlpha(argb1), getAlpha(argb2), ratio);
		int red = interpolateChannel(getRed(argb1), getRed(argb2), ratio);
		int green = interpolateChannel(getGreen(argb1), getGreen(argb2), ratio);
		int blue = interpolateChannel(getBlue(argb1), getBlue(argb2), ratio);
		return argb(alpha, red, green, blue);
	}
	
	/**
	 * Interpolates two color channels. A lower ratio makes the result closer to the first channel.
	 * 
	 * @param channel1 the first color's channel
	 * @param channel2 the second color's channel
	 * @param ratio the position of the result between the channels
	 * @return the interpolated channel between the input channels
	 */
	public static int interpolateChannel(int channel1, int channel2, double ratio) {
		if(ratio <= 0)
			return channel1;
		if(ratio >= 1)
			return channel2;
		return (int) ((1 - ratio) * channel1 + ratio * channel2);
	}
	
	/**
	 * Get the absolute channel value of relative value in [0, 1].
	 * 
	 * @param relative the relative channel value
	 * @return the absolute channel value
	 */
	public static int channelRelativeToAbsolute(double relative) {
		return (int) Math.round(relative * CHANNEL_MASK);
	}
	
	/**
	 * Get {@link String} representation of the color's channels.
	 * 
	 * @param argb argb color
	 * @return the {@link String} containing the color's channel values
	 */
	public static String toString(int argb) {
		return String.format("[a=%d,r=%d,g=%d,b=%d]", getAlpha(argb), getRed(argb), getGreen(argb), getBlue(argb));
	}
	
	
	// no instances
	private ArgbColors() {
	}
}
