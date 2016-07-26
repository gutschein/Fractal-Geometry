package de.lennartmeinhardt.math.fractalgeom;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

import de.lennartmeinhardt.argb.ArgbColors;
import de.lennartmeinhardt.math.argb.dim2.transform.InterpolateArgbPointTransformation2D;
import de.lennartmeinhardt.math.dim2.transform.PointTransformation2D;
import de.lennartmeinhardt.math.dim2.transform.affine.AffineBuilder2D;
import de.lennartmeinhardt.math.dim2.transform.affine.AffineTransformation2D;
import de.lennartmeinhardt.math.dim2.transform.affine.ImmutableAffineTransformation2D;
import de.lennartmeinhardt.math.discrete.Discretizer2D;
import de.lennartmeinhardt.math.discrete2d.DiscreteTransformation2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.DiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.doublemap.OperableDiscreteDoubleMap2D;
import de.lennartmeinhardt.math.discrete2d.set.OperableDiscreteSet2D;
import de.lennartmeinhardt.math.discrete2d.set.shapes.BaseEllipse;
import de.lennartmeinhardt.math.discrete2d.set.shapes.BaseSquare;
import de.lennartmeinhardt.math.discrete2d.set.shapes.DiscreteRectangle;
import de.lennartmeinhardt.math.ifs.IteratedFunctionSystem;
import de.lennartmeinhardt.math.ifs.ListIteratedFunctionSystem;

/**
 * Contains examples for Barnsley's upper/lower IFS.
 * 
 * @author Lennart Meinhardt
 */
public class UpperLowerExamples {

	// x and y reflections
	public static final AffineTransformation2D reflectX = new ImmutableAffineTransformation2D(-1, 0, 1, 0, 1, 0);
	public static final AffineTransformation2D reflectY = new ImmutableAffineTransformation2D(1, 0, 0, 0, -1, 1);
	// the upper/lower transformations
	public static final AffineTransformation2D upperLeftTransformation = new ImmutableAffineTransformation2D(1/2d, -3/8d, 5/16d, 1/2d, 3/8d, 3/16d);
	public static final AffineTransformation2D upperRightTransformation = new ImmutableAffineTransformation2D(new AffineBuilder2D(reflectX).postConcat(upperLeftTransformation).postConcat(reflectX));
	public static final AffineTransformation2D lowerLeftTransformation = new ImmutableAffineTransformation2D(new AffineBuilder2D(upperLeftTransformation).postConcat(reflectY));
	public static final AffineTransformation2D lowerRightTransformation = new ImmutableAffineTransformation2D(new AffineBuilder2D(upperRightTransformation).postConcat(reflectY));

	// the upper and lower IFSs
	public static final ListIteratedFunctionSystem<AffineTransformation2D> upper = new ListIteratedFunctionSystem<>(upperLeftTransformation, upperRightTransformation);
	public static final ListIteratedFunctionSystem<AffineTransformation2D> lower = new ListIteratedFunctionSystem<>(lowerLeftTransformation, lowerRightTransformation);
	// the left and right IFSs
	public static final ListIteratedFunctionSystem<AffineTransformation2D> left = new ListIteratedFunctionSystem<>(upperLeftTransformation, lowerLeftTransformation);
	public static final ListIteratedFunctionSystem<AffineTransformation2D> right = new ListIteratedFunctionSystem<>(upperRightTransformation, lowerRightTransformation);
	// the diagonal IFSs
	public static final ListIteratedFunctionSystem<AffineTransformation2D> upperLeft_lowerRight = new ListIteratedFunctionSystem<>(upperLeftTransformation, lowerRightTransformation);
	public static final ListIteratedFunctionSystem<AffineTransformation2D> upperRight_lowerLeft = new ListIteratedFunctionSystem<>(upperRightTransformation, lowerLeftTransformation);
	// the different superIFSs
	public static final ListIteratedFunctionSystem<IteratedFunctionSystem<AffineTransformation2D>> upperLowerSuperIfs = new ListIteratedFunctionSystem<>(upper, lower);
	public static final ListIteratedFunctionSystem<IteratedFunctionSystem<AffineTransformation2D>> leftRightSuperIfs = new ListIteratedFunctionSystem<>(left, right);
	public static final ListIteratedFunctionSystem<IteratedFunctionSystem<AffineTransformation2D>> diagonalSuperIfs = new ListIteratedFunctionSystem<>(upperLeft_lowerRight, upperRight_lowerLeft);
	
	// the transformation colors to use for IFS coloring
	public static final int upperLeftColor = Color.RED.getRGB();
	public static final int upperRightColor = Color.ORANGE.getRGB();
	public static final int lowerLeftColor = ArgbColors.interpolate(Color.BLUE.getRGB(), Color.GREEN.getRGB(), .2);
	public static final int lowerRightColor = ArgbColors.interpolate(Color.BLUE.getRGB(), Color.GREEN.getRGB(), .5);

	// the upper/lower transformations as argb transformations
	public static final InterpolateArgbPointTransformation2D upperLeftArgbTransformation = new InterpolateArgbPointTransformation2D(upperLeftTransformation, upperLeftColor);
	public static final InterpolateArgbPointTransformation2D upperRightArgbTransformation = new InterpolateArgbPointTransformation2D(upperRightTransformation, upperRightColor);
	public static final InterpolateArgbPointTransformation2D lowerLeftArgbTransformation = new InterpolateArgbPointTransformation2D(lowerLeftTransformation, lowerLeftColor);
	public static final InterpolateArgbPointTransformation2D lowerRightArgbTransformation = new InterpolateArgbPointTransformation2D(lowerRightTransformation, lowerRightColor);
	
	// the underlying IFS consisting of all the transformations in upper/lower
	public static final IteratedFunctionSystem<AffineTransformation2D> underlyingIfs = new ListIteratedFunctionSystem<>(
			upperLeftTransformation, upperRightTransformation,
			lowerLeftTransformation, lowerRightTransformation
	);

	// the upper and lower IFSs of argb transformations
	public static final IteratedFunctionSystem<InterpolateArgbPointTransformation2D> upperColored = new ListIteratedFunctionSystem<>(upperLeftArgbTransformation, upperRightArgbTransformation);
	public static final IteratedFunctionSystem<InterpolateArgbPointTransformation2D> lowerColored = new ListIteratedFunctionSystem<>(lowerLeftArgbTransformation, lowerRightArgbTransformation);
	
	
	/**
	 * Get the base space circle as discrete set.
	 * 
	 * @param resolution the resolution to use
	 * @return discrete circle with given diameter
	 */
	public static BaseEllipse getDiscreteCircleSet(int resolution) {
		return new BaseEllipse(new BaseSquare(resolution));
	}
	
	/**
	 * Get the discrete double map with normed value on the base circle, i.e. the sum of values equals 1.
	 * It corresponds to a discretized probability measure.
	 * 
	 * @param resolution the resolution to use
	 * @return discrete double map with value sum that equals 1
	 */
	public static OperableDiscreteDoubleMap2D<DiscreteRectangle> getDiscreteCircleDoubleMapNormed(int resolution) {
		OperableDiscreteSet2D unitCircle = getDiscreteCircleSet(resolution);
		long cardinality = unitCircle.getCardinality();
		double scale = 1. / cardinality;
		DiscreteDoubleMap2D startMap = DiscreteDoubleMap2D.ifThenElse(unitCircle::containsPointAt, DiscreteDoubleMap2D.ofConstant(scale), DiscreteDoubleMap2D.ZERO);
		return startMap.asOperable(new BaseSquare(resolution));
	}
	
	
	/**
	 * Convert a superIFS of point transformations to a superIFS of discrete transformations.
	 * 
	 * @param superIfs superIFS of point transformations
	 * @param discretizer the discretizer to use
	 * @return superIFS of discrete transformations
	 */
	public static <T extends PointTransformation2D> IteratedFunctionSystem<? extends IteratedFunctionSystem<DiscreteTransformation2D>> getDiscreteSuperIfs(IteratedFunctionSystem<? extends IteratedFunctionSystem<? extends T>> superIfs, Discretizer2D discretizer) {
		return new ListIteratedFunctionSystem<>(superIfs
				.stream()
				.map(ifs -> getDiscreteIfs(ifs, discretizer))
				.collect(Collectors.toList()));
	}
	
	/**
	 * Create a discrete IFS from a given IFS of point transformations.
	 * 
	 * @param ifs the IFS to convert
	 * @param discretizer the discretizer to use for creating discrete transformations
	 * @return IFS of discrete transformations
	 */
	public static IteratedFunctionSystem<DiscreteTransformation2D> getDiscreteIfs(IteratedFunctionSystem<? extends PointTransformation2D> ifs, Discretizer2D discretizer) {
		List<DiscreteTransformation2D> transformations = ifs
				.stream()
				.map(discretizer::discretize)
				.collect(Collectors.toList());
		return new ListIteratedFunctionSystem<>(transformations);
	}
}
