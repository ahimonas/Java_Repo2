/**
 * DNA class represents the DNA strand.
 * 
 * @author reshamelahi
 * @version 04/12/2016
 */

public class DNA {

	// DNA sequence of nucleotide basis
	private String sequence;

	// valid/invalid indicator
	private boolean valid;

	/**
	 * Initializes newly created DNA strand to the provided sequence. The
	 * sequence is validated to contain only legal nucleotides: guanine (G),
	 * adenine (A), thymine (T), or cytosine (C).
	 * 
	 * @param sequence
	 *            the sequence of nucleotide basis to be used in this DNA
	 */

	public DNA(String sequence) {
		this.sequence = sequence;
	}

	/*
	 * Operates on the sequence of this DNA object. It returns true/false
	 * indicating validity of the sequence. The sequence is valid if it contains
	 * only legal nucleotide basis: guanine (G), adenine (A), thymine (T), or
	 * cytosine (C). The valid data field is set accordingly.
	 * 
	 * @return true if the sequence in this DNA object is valid, false otherwise
	 */

	private boolean validate() {
		System.out.println("WHWAT");
		for (int i = 0; i < sequence.length(); i++) {
			// System.out.println("HERE23232323");
			if (sequence.charAt(i) != 'G' || sequence.charAt(i) != 'g'
					|| sequence.charAt(i) != 'A' || sequence.charAt(i) != 'a'
					|| sequence.charAt(i) != 'T' || sequence.charAt(i) != 't'
					|| sequence.charAt(i) != 'C' || sequence.charAt(i) != 'c') {
				return false;
			}
		}

		return true;
	}

	/**
	 * Translates this DNA object to its corresponding RNA sequence. The DNA and
	 * RNA are closely related. The major difference is that the RNA contains
	 * uracil (U) rather than thymine (T). This method replaces every occurrence
	 * of T in the sequence by U and returns the corresponding String object.
	 * 
	 * @return the RNA sequence
	 */

	public String toRNA() {
		if (sequence.length() == 0 || this.validate() == false) {
			return null;
		} else {
			return sequence.replaceAll("T", "U");
		}
	}

	/**
	 * Computes the complementary DNA strand. The DNA usually exists as a double
	 * helix: given the strand stored in this DNA object, this method computes
	 * and returns the second strand from the helix. To compute the
	 * complementary strand all A's need to be swapped with T's (and vice versa)
	 * and all C's need to be swapped with G's (and vice versa), and then the
	 * resulting string is reversed.
	 * 
	 * @return DNA object containing the reverse complement of the sequence
	 *         stored in this DNA object
	 */

	public DNA reverseComplement() {
		if (sequence.length() == 0 || this.validate() == false) {
			return null;
		} else {
			String complement = "";
			for (int i = 0; i < sequence.length(); i++) {
				if (sequence.charAt(i) == 'A' || sequence.charAt(i) == 'a') {
					complement += 'T';
				}
				if (sequence.charAt(i) == 'T' || sequence.charAt(i) == 't') {
					complement += 'A';
				}
				if (sequence.charAt(i) == 'C' || sequence.charAt(i) == 'c') {
					complement += 'G';
				}
				if (sequence.charAt(i) == 'G' || sequence.charAt(i) == 'g') {
					complement += 'C';
				}
			}
			String reverseComplement = "";
			for (int i = complement.length() - 1; i >= 0; i--) {
				reverseComplement += complement.charAt(i);
			}
			DNA reverseComp = new DNA(reverseComplement);
			return reverseComp;
		}
	}

	/**
	 * Returns true if this DNA object contains a valid strand and false
	 * otherwise.
	 * 
	 * @return true if this DNA object contains a valid strand and false
	 *         otherwise
	 */

	public boolean isValid() {
		System.out.println("WHWAT");
		for (int i = 0; i < sequence.length(); i++) {
			System.out.println("HERE23232323");
			if (sequence.charAt(i) != 'G' || sequence.charAt(i) != 'g')
				return true;
			if (sequence.charAt(i) != 'A' || sequence.charAt(i) != 'a')
				return false;
			if (sequence.charAt(i) != 'T' || sequence.charAt(i) != 't')
				return false;
			if (sequence.charAt(i) != 'C' || sequence.charAt(i) != 'c')
				return false;
		}

		return true;
	}

	/**
	 * Returns the String representation of this DNA object which is the
	 * sequence itself.
	 */

	public String toString() {
		return sequence;
	}
}
