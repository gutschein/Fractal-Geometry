package de.lennartmeinhardt.math.dim2.transform.square;

import de.lennartmeinhardt.math.dim2.transform.affine.AffineBuilder2D;
import de.lennartmeinhardt.math.dim2.transform.affine.AffineTransformation2D;
import de.lennartmeinhardt.math.dim2.transform.affine.ImmutableAffineTransformation2D;

public class BuildingBlocks {

	public static AffineTransformation2D IDENTITY = new ImmutableAffineTransformation2D(1, 0, 0, 0, 1, 0);
	public static AffineTransformation2D REFLECT_X = new ImmutableAffineTransformation2D(-1, 0, 1, 0, 1, 0);
	public static AffineTransformation2D REFLECT_Y = new ImmutableAffineTransformation2D(1, 0, 0, 0, -1, 1);
	public static AffineTransformation2D REFLECT_BOTH = new ImmutableAffineTransformation2D(-1, 0, 1, 0, -1, 1);
	public static AffineTransformation2D ROTATE90_CCW = new ImmutableAffineTransformation2D(0, -1, 1, 1, 0, 0);

	public static AffineTransformation2D BOTTOM_LEFT = new ImmutableAffineTransformation2D(.5, 0, 0, 0, .5, 0);
	public static AffineTransformation2D BOTTOM_RIGHT = new ImmutableAffineTransformation2D(.5, 0, .5, 0, .5, 0);
	public static AffineTransformation2D TOP_LEFT = new ImmutableAffineTransformation2D(.5, 0, 0, 0, .5, .5);
	public static AffineTransformation2D TOP_RIGHT = new ImmutableAffineTransformation2D(.5, 0, .5, 0, .5, .5);
	
	
	public static AffineTransformation2D getSquareTransform(int rotation90s, boolean reflectX, SquarePosition pos) {
		AffineBuilder2D builder = new AffineBuilder2D();
		if(reflectX)
			builder.postConcat(REFLECT_X);
		for(int i = 0; i < rotation90s % 4; i++)
			builder.postConcat(ROTATE90_CCW);
		builder.postConcat(pos.transformation);
		return new ImmutableAffineTransformation2D(builder);
	}
	
	
	public enum SquarePosition {
		BOTTOM_LEFT(BuildingBlocks.BOTTOM_LEFT),
		BOTTOM_RIGHT(BuildingBlocks.BOTTOM_RIGHT),
		TOP_LEFT(BuildingBlocks.TOP_LEFT),
		TOP_RIGHT(BuildingBlocks.TOP_RIGHT);
		
		private final AffineTransformation2D transformation;
		
		private SquarePosition(AffineTransformation2D transformation) {
			this.transformation = transformation;
		}
	}
	
	private BuildingBlocks() {
	}
}
