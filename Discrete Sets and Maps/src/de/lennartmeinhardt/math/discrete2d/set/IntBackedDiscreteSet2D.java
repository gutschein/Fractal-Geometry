package de.lennartmeinhardt.math.discrete2d.set;

import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;

/**
 * A basic mutable {@link DiscreteSet2D}.
 * 
 * Saves the contained points as an int-array. Each row is made up of a sequence of integers.
 * The integers bismasks determine which points are contained. A bit set to 1 indicates that the point with a certain x coordinate is contained in this set.
 * Each int value stores contained state for 32 points.
 * Rows are stored in a single int-array. 
 * 
 * @author Lennart Meinhardt
 */
public class IntBackedDiscreteSet2D implements MutableDiscreteSet2D, RectangularBoundsDiscreteSet2D, Cloneable {

	// the coordinates of the bounds' bottom left anchor
	private final int left, bottom;
	// the bounds' dimensions
	private final int width, height;
	private final DiscreteRectangle bounds = new Bounds();
	
	private final int[] groupsRowWise;
	
	
	/**
	 * Create a new {@link IntBackedDiscreteSet2D} with given size.
	 * 
	 * @param size the set's size
	 */
	public IntBackedDiscreteSet2D(int size) {
		this(size, size);
	}
	
	/**
	 * Create a new {@link IntBackedDiscreteSet2D} with given dimensions.
	 * 
	 * @param width the set's width
	 * @param height the set's height
	 */
	public IntBackedDiscreteSet2D(int width, int height) {
		this(0, 0, width, height);
	}
	
	/**
	 * Create a new {@link IntBackedDiscreteSet2D} with given bounds.
	 * 
	 * @param left the left anchor
	 * @param bottom the bottom anchor
	 * @param width the set's width
	 * @param height the set's height
	 */
	public IntBackedDiscreteSet2D(int left, int bottom, int width, int height) {
		this.width = width;
		this.height = height;
		this.left = left;
		this.bottom = bottom;
		groupsRowWise = createGroupRowArray(width, height);
	}
	
	/**
	 * Create a new {@link IntBackedDiscreteSet2D} with given bounds.
	 * 
	 * @param bounds the bounds to use
	 */
	public IntBackedDiscreteSet2D(DiscreteRectangle bounds) {
		this(bounds.getLeft(), bounds.getBottom(), bounds.getWidth(), bounds.getHeight());
	}
	
	
	@Override public DiscreteRectangle getBounds() {
		return bounds;
	}
	
	@Override public boolean removeAll() {
		for(int i = 0; i < groupsRowWise.length; i++)
			groupsRowWise[i] = 0;
		return true;
	}
	
	// create the array of row-wise groups
	private static int[] createGroupRowArray(int width, int height) {
		int arrayLength = getGroupsRequiredForMultiRows(width, height);
		return new int[arrayLength];
	}
	
	// check if a given point is contained in the bounds
	private boolean isInRange(int x, int y) {
		return left <= x && x < left + width && bottom <= y && y < bottom + height;
	}
	
	@Override public boolean containsPointAt(int x, int y) {
		if(isInRange(x, y)) {
			int groupIndex = getGroupIndex(x, y);
			int groupOffset = getGroupOffset(x);
			int group = groupsRowWise[groupIndex];
			return (group & (1 << groupOffset)) != 0;
		} else
			return false;
	}
	
	@Override public boolean setContainsPointAt(int x, int y, boolean contained) {
		if(isInRange(x, y)) {
			int groupIndex = getGroupIndex(x, y);
			int groupOffset = getGroupOffset(x);
			int group = groupsRowWise[groupIndex];
			int singlePointContained = 1 << groupOffset;
			int groupNew;
			
			if(contained)
				groupNew = group | singlePointContained;
			else
				groupNew = group & (singlePointContained ^ -1);
			groupsRowWise[groupIndex] = groupNew;
			return true;
		} else
			return false;
	}
	
	// get the number of groups required for saving points of a single row
	private static int getGroupsRequiredForSingleRow(int width) {
		if(width <= 0)
			return 0;
		else
			return ((width - 1) / Integer.SIZE) + 1;
	}
	
	/**
	 * Get the number of groups 
	 * @param width
	 * @param height
	 * @return
	 */
	private static int getGroupsRequiredForMultiRows(int width, int height) {
		return height * getGroupsRequiredForSingleRow(width);
	}
	
	// get the index of the group containing the given coordinates
	private int getGroupIndex(int x, int y) {
		x -= left;
		y -= bottom;
		int groupsPerRow = getGroupsRequiredForSingleRow(width);
		return x / 32 + y * groupsPerRow;
	}
	
	// get the offset of given x coordinate within a group
	private int getGroupOffset(int x) {
		x -= left;
		int groupOffsetRightToLeft = x % 32;
		return 31 - groupOffsetRightToLeft;
	}
	
	/**
	 * Get this set's raw data, i.e. the bitmasks that determine which points are contained.
	 * 
	 * @return the raw data as bitmask array
	 */
	public int[] getRawData() {
		return groupsRowWise;
	}
	
	@Override public IntBackedDiscreteSet2D clone() {
		IntBackedDiscreteSet2D copy = new IntBackedDiscreteSet2D(bounds);
		int[] raw = groupsRowWise;
		int[] copyRaw = copy.groupsRowWise;
		System.arraycopy(raw, 0, copyRaw, 0, raw.length);
		return copy;
	}
	
	@Override public void forEachPoint(DiscreteConsumer2D operation) {
		for(int x = left; x < left + width; x++)
			for(int y = bottom; y < bottom + height; y++)
				if(containsPointAt(x, y))
					operation.accept(x, y);
	}

	
	private class Bounds implements DiscreteRectangle {
		@Override public int getLeft() { return left; }
		@Override public int getBottom() { return bottom; }
		@Override public int getRight() { return left + width - 1; }
		@Override public int getTop() { return bottom + height - 1; }
	}
}
