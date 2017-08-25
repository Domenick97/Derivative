package util;

/**
 * The Term class is an object for each term in the polynomial
 *
 * @author Domenick
 *
 */
public class Term {
	private String term;
	private int coefficient = 1;
	private boolean isExp = false;
	private int exp;
	private String variable;

	/**
	 * Constructor class for a term
	 * 
	 * @param mono
	 *            String of the term
	 */
	public Term(String term) {
		this.term = term.trim();
		int varFront = 0;
		int expFront = 0;

		for (int w = 0; w < this.term.length(); w++) {
			if (Character.isLetter(this.term.charAt(w)) && varFront == 0) {
				varFront = w;
				if (w == 0) {

				} else {
					coefficient = Integer.parseInt(this.term.substring(0, varFront));
				}

			}
			if (this.term.charAt(w) == '^') {
				expFront = w;
				isExp = true;
				variable = this.term.substring(varFront, w);
				exp = Integer.parseInt(this.term.substring(expFront + 1));
				break;
			}
			if(w == this.term.length() -1 && isExp == false){
				variable = this.term.substring(varFront);
			}
		}
	}

	/**
	 * Returns the term as a string
	 * 
	 * @return term The term as a string
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * Returns the coefficient of the term
	 * 
	 * @return coefficient of the term
	 */
	public int getCoef() {
		return coefficient;
	}

	/**
	 * Returns true if the term has an exponent
	 * 
	 * @return isExp boolean value, true if there is an exponent
	 */
	public boolean hasExp() {
		return isExp;
	}

	/**
	 * Returns the value of exponent for term
	 * 
	 * @return exp The exponent of term
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * Returns the variable used in the term
	 * 
	 * @return variable within the term
	 */
	public String getVar() {
		return variable;
	}

}
