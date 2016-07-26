package de.lennartmeinhardt.math.dim2.transform.affine;

/**
 * Holds the values of a two-dimensional affine transformation matrix.
 * The transformation maps as follows:
 * [x]     [mxx   mxy] [x]   [tx]
 * [ ] --> [         ] [ ] + [  ]
 * [y]     [myx   myy] [y]   [ty] 
 * 
 * @author Lennart Meinhardt
 */
public interface AffineHolder2D {
	
	/**
	 * Get the mxx linear matrix entry.
	 * 
	 * @return the mxx value
	 */
	double getMxx();

	/**
	 * Get the mxy linear matrix entry.
	 * 
	 * @return the mxy value
	 */
	double getMxy();

	/**
	 * Get the translation in x direction.
	 * 
	 * @return the tx value
	 */
	double getTx();

	/**
	 * Get the myx linear matrix entry.
	 * 
	 * @return the myx value
	 */
	double getMyx();

	/**
	 * Get the myy linear matrix entry.
	 * 
	 * @return the myy value
	 */
	double getMyy();

	/**
	 * Get the translation in y direction.
	 * 
	 * @return the ty value
	 */
	double getTy();
}
