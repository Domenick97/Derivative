/*
 * Program:	Derivative
 * Version: 1.2
 *
 * Author: Domenick DiBiase 
 * Date Written: 6, February 2016
 * Last Updated: 24, August 2017
 *
 * Finds the derivative of a basic polynomial of any length.
 */
package Derive;

import java.util.*;
import util.Term;

/**
 * Finds the derivative of a basic polynomial
 * 
 * TODO: Create IO class for reading text files or user input, arrayList for
 * each division of the polynomial for clarity, organize and make code more
 * efficient, and create test classes.
 * 
 * @author Domenick
 */
public class Derivative {
	/** Instance of the Derivative */
	private static Derivative singlton = new Derivative();
	
	private String polyFinal = "";

	/**
	 * Gets the instance of the
	 * 
	 * @return the instance of the Derivative
	 */
	public static Derivative getInstance() {
		return singlton;
	}

	/**
	 * Breaks down the polynomial into separate terms
	 * 
	 * @param poly
	 *            String value for the polynomial
	 * @return ArrayList of the terms in the polynomial
	 */
	public ArrayList<Term> breakDown(String poly) {
		if (poly == null || poly.equals(""))
			throw new IllegalArgumentException();

		/** Array list for the terms in the polynomial */
		ArrayList<Term> terms = new ArrayList<Term>();

		if (poly.length() == 1) {
			terms.add(new Term(poly));
			return terms;
		}

		/** The temporary front position of the term */
		int tempFront = 0;

		for (int i = 0; i < poly.length(); i++) {
			if (poly.charAt(i) == '+' || poly.charAt(i) == '-') {
				if (i != 0) {
					terms.add(new Term(poly.substring(tempFront, i)));
					tempFront = i;
				}
			} else {
				if (i == poly.length() - 1) {
					terms.add(new Term(poly.substring(tempFront)));
				}
			}
		}
		return terms;
	}

	/**
	 * Computes the derivative of the polynomial passed in
	 * 
	 * @param poly
	 *            The polynomial as a string
	 * @return derivOut The derivative of the polynomial
	 */
	public String derive(String poly) {
		
		ArrayList<Term> terms = breakDown(poly);

		
		for (int k = 0; k < terms.size(); k++) {
			polyFinal += "" + terms.get(k).getCoef() + terms.get(k).getVar() + " ";
		}
		return polyFinal;
		
		/*
		int front = 0, back = 0;
		// Keeps count of the number of the section. The first sec wont have a
		// symbol in front.
		int count = 0;
		String derivOut = "";
		String derivSec;
		boolean contSec = true;
		int h = 0;
		boolean min = false;

		while (contSec = true) // Reads till the value is + or -
		{
			int indexPlus = 0, indexMinus = 0;
			int exp = 0;
			int xpos = -1;
			double n = 0;
			double o = 0;
			double c = 1;
			boolean min2 = false;
			String expon;
			String cost;
			String section;

			// Finds the location of the next '+' and '-'
			indexPlus = poly.indexOf(("+"), h + 1);
			indexMinus = poly.indexOf(("-"), h + 1);

			if (indexPlus == indexMinus) // If there is no more "+" or "-" int
											// the poly
			{
				break; // Breaks the loop.
			} else {
				if (indexPlus == -1 || indexMinus == -1) // If there is only one
															// of the 2
				{
					if (indexPlus > indexMinus) // If "+" is the only one left.
					{
						h = indexPlus; // Makes the postion that of the next
										// "+".
					} else // If the "-" is the only one left.
					{
						h = indexMinus; // Makes the postion that of the next
										// "+".
						// Indicates that the next section of the poly is
						// negative.
						min2 = true;
					}
				} else // If both a "-" and "+" are left in the poly
				{
					// Finds which position is the closest to the front.
					if (indexPlus < indexMinus) {
						h = indexPlus;
					} else {
						h = indexMinus;
						min2 = true;
					}
				}
			}
			back = h; // Sets "back" to the position of the next "+" or "-".
			derivSec = poly.substring(front, back); // Section from constant to
													// end of the exponent
			derivSec = derivSec.trim();
			xpos = derivSec.indexOf("x"); // Posistion of x
			if (xpos == -1) // If there is no x the derivative is 0;
			{
				front = back + 1; // Allows the code to read past the current
									// "+" or "-"
				count++;
				min = min2; // Sets a negative value to the next section of the
							// poly.
				continue; // Skips the rest of the loop.
			}
			exp = derivSec.indexOf("^"); // Gets the position of the '^'
			if (exp > 0) // As long as the exponent exist.
			{
				expon = derivSec.substring(exp + 1);
				n = Double.parseDouble(expon);

				o = n - 1;
			}
			if (count > 0) { // As long as this is not the first section of the
								// poly
				// If there is no constant nor exponent
				if (xpos == 0 && exp == 0) {
					derivOut += " + 1";
					min = min2;
					count++;
					continue;
				}
			} else {
				// If there is no constant nor exponent
				if (xpos == 0 && exp == 0) {
					derivOut += "1";
					min = min2;
					count++;
					continue;
				}
			}
			if (xpos == 0) {
				c = 1.0;
			} else {
				cost = derivSec.substring(0, xpos); // Gets the string of the
													// constant
				cost = cost.trim(); // Trims the space out of the constant
				c = Double.parseDouble(cost); // The Original Constant as a
												// double
			}
			if (min == true) // If the section is negative.
			{
				c = c * -1.0; // Changes the constant to a negative.
			}
			front = back + 1; // Allows the code to read past the current "+" or
								// "-"
			min = min2; // Sets a negative value to the next section of the
						// poly.

			if (exp == -1) // If the exponent doesnt exist
			{
				String constVal = "";
				if (c - (int) c == 0) // If the constant is an int- converts to
										// int.
				{
					constVal = "" + (int) c;
				} else // Else the double is rounded.
				{
					c = (int) (c * 100 + .5) / 100.0;
					constVal = "" + c;
				}

				if (count > 0) {
					// As long as this is not the first section of the poly.
					if (c < 0) {
						// If the constant is negative.
						derivOut += " - " + (constVal.substring(1));
					} else {
						// If the constant is positive.
						derivOut += " + " + constVal;
					}

					count++;

					continue;
				} else {
					if (c < 0) {
						derivOut += constVal;
					} else {
						derivOut += constVal;
					}

					count++;

					continue;
				}
			}
			double b = c * n;

			// Drops the decimal if there is no decimal value.
			String constVal = "";
			if (b - (int) b == 0) {
				constVal = "" + (int) b;
			} else {
				b = (int) (b * 100 + .5) / 100.0;
				constVal = "" + b;
			}
			String expVal = "";
			if (o - (int) o == 0) {
				expVal = "" + (int) o;
			} else {
				o = (int) (o * 100 + .5) / 100.0;
				expVal = "" + o;
			}
			// If the new exponent is 1 it will not display x^1
			if (o == 1) {
				section = "" + constVal + "x";
			} else {
				section = "" + constVal + "x^" + expVal;
			}
			if (count > 0) {
				// As long as this is not the first section of the poly
				if (c < 0) {
					section = " - " + (section.substring(1));
				} else {
					section = " + " + section;
				}
			}
			// section += " + ";
			derivOut += section;// each section derivative added.
			count++;
		} // End of the While Loop
		int exp = 0;
		int xpos = -1;
		double n = 0;
		double o = 0;
		double c = 1;
		String expon;
		String cost;
		String section;
		derivSec = poly.substring(front); // Find the last section after the
											// last '+'
		derivSec = derivSec.trim(); // Trims the section
		xpos = derivSec.indexOf("x"); // Posistion of x
		if (xpos == -1) {
			// If there is no x in the section
			return derivOut;
		}
		exp = derivSec.indexOf("^"); // Finds where the '^' position is.
		if (exp > 0) {
			// As long as the exponent exist.
			expon = derivSec.substring(exp + 1); // Finds the string after the
													// '^'
			n = Double.parseDouble(expon); // Converter the string to a double

			o = n - 1; // Finds the new exponent.
		}
		if (xpos == 0 && exp == 0) {
			// If there is no constant and no exponent.
			if (count > 0) {
				// As long as this is not the first section of the poly
				if (min == true) {
					// If the section of the poly is negative.
					section = " - 1";
				} else {
					// If the section of the poly is positive.
					derivOut = " + 1";
				}
			} else {
				derivOut += "1"; // The sections derivative is = 1
			}
			return derivOut;
		}
		if (xpos == 0) {
			// If no constant is defined.
			c = 1.0; // Default constant
		} else {
			cost = derivSec.substring(0, xpos); // Gets the string of the
												// constant
			cost = cost.trim();
			c = Double.parseDouble(cost); // The Original Constant as a double
		}
		if (min == true) {
			c = c * -1;
		}
		if (exp == -1) // If the exponent doesnt exist
		{
			String constVal = "";
			// Drops the decimal if there is no decimal value.
			if (c - (int) c == 0) {
				constVal = "" + (int) c;
			} else {
				c = (int) (c * 100 + .5) / 100.0; // Rounding
				constVal = "" + c;
			}
			if (count > 0) // As long as this is not the first section of the
							// poly.
			{
				if (c < 0) // If the constant is negative.
				{
					derivOut += " - " + (constVal.substring(1));
				} else // If the constant is positive.
				{
					derivOut += " + " + constVal;
				}
			} else {
				derivOut += constVal;
			}
			return derivOut;
		}
		double b = c * n; // Find the new constant.

		// Drops the decimal if there is no decimal value.
		String constVal = "";
		if (b - (int) b == 0) {
			constVal = "" + (int) b;
		} else {
			b = (int) (b * 100 + .5) / 100.0;
			constVal = "" + b;
		}
		String expVal = "";
		if (o - (int) o == 0) {
			expVal = "" + (int) o;
		} else {
			o = (int) (o * 100 + .5) / 100.0;
			expVal = "" + o;
		}
		// If the new exponent is 1 it will not display x^1
		if (o == 1) {
			section = "" + constVal + "x";
		} else {
			section = "" + constVal + "x^" + expVal;
		}
		if (count > 0) // As long as this is not the first section of the poly.
		{
			if (c < 0) {
				section = " - " + (section.substring(1));
			} else {
				section = " + " + section;
			}
		}
		derivOut += section;// each section derivative added.
		return derivOut;*/
	}
}