/**
 * This class provides simple test for the DNA class.
 * 
 * @author reshamelahi
 * @version
 */

public class TestDNA {

	public static void main(String[] args) {

		int numberFailed = 0;

		// ///////////////////////////////////////////////
		// test using ATCG sequence (short valid strand)
		// ///////////////////////////////////////////////

		System.out.println("Testing with ATCG: ");

		DNA dna1 = new DNA("ATCG");
		// System.out.println(dna1.toRNA());
		// System.out.println(dna1.toString());
		// System.out.println("ATCG".compareToIgnoreCase(dna1.toString()));
		if (dna1 != null)
			if (0 != "ATCG".compareToIgnoreCase(dna1.toString())) {
				numberFailed++;
				printError("invalid toString() method");

			}

		// System.out.println("HERE2");

		if (!dna1.isValid()) {
			// System.out.println("HERE");

			numberFailed++;
			printError("invalid isValid() method ");
			// System.out.println("HERE3");

		}

		if (dna1.toRNA() != null)
			if (dna1 != null) {
				if (0 != "AUCG".compareToIgnoreCase(dna1.toRNA())) {
					numberFailed++;
					printError("invalid toRNA() method");

				}

				if (0 != "CGAT".compareToIgnoreCase(dna1.reverseComplement()
						.toString())) {

					numberFailed++;
					printError("invalid reverseComplement() method");

				}

				System.out.println("Testing with ATCG completed.\n "
						+ numberFailed + " tests failed.\n ");

				// ///////////////////////////////////////////////////
				// test using short invalid strands: BCAT, ABGT, ACTB
				// ///////////////////////////////////////////////////

				System.out.println("Testing with short invalid strands: "
						+ "BCAT, ABGT, ACTB: ");
			}

		dna1 = new DNA("BCAT");
		if (dna1.isValid()) {
			printError("BCAT is not a valid DNA strand ");
		}
		dna1 = new DNA("ABGT");
		if (dna1.isValid()) {
			printError("ABGT is not a valid DNA strand ");
		}
		dna1 = new DNA("ACTB");
		if (dna1.isValid()) {
			printError("ACTB is not a valid DNA strand ");
		}

		System.out.println("Testing with short invalid strands: "
				+ "BCAT, ABGT, ACTB completed.\n ");

		// /////////////////////////////////
		// YOU CAN ADD OTHER TESTS HERE
		// /////////////////////////////////

	}

	public static void printError(String error) {

		System.err.printf("ERROR: %s\n\n", error);

	}

}