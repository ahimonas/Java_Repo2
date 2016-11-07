package insta60;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class DNA {

	public static void main(String[] args) {
		String[] protiens = new String[100];
		String[] names = new String[100];
		// The name of the file to open.

		ArrayList<String> list1 = new ArrayList<String>();

		// read in filein string <---- dna.txt
		// Set fileName = string

		// read in fileout string
		// Set fileName = string <---temp.txt

		String fileName = "C:/Users/Andrew Himonas/Desktop/ruler/insta60/src/insta60/dna.txt";

		// StringBuilder myStr = new StringBuilder("");
		// System.out.println("");
		// String[] myTest = { "a", "b", "c", "a" };
		// myTest = removeDuplicates(myTest);
		// printStringArray(myTest);

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int i = 1;
			int index = 0;

			while ((line = bufferedReader.readLine()) != null) {

				if (i % 2 == 0) {
					// System.out.println("should be protien");
					String str = line.replaceAll("-", "");

					str = str.toUpperCase();
					// System.out.println(str);
					protiens[index] = str;
					// myStr.append("Nucleotides: " + protiens[index] + "\n");
					list1.add("Nucleotides: " + protiens[index]);

					// System.out.println("Nucleotides: " + protiens[index]);

					int[] counts = countLetters(str);

					// System.out.println("HELLO WORLD " + countMASSES(counts));
					// myStr.append("Nuc. Counts: " + Arrays.toString(counts) +
					// "\n");
					// System.out.print("Nuc. Counts: " +
					// Arrays.toString(counts));
					String[] test = parseNucs(str);
					// System.out.println(Arrays.toString(test));
					test = removeDuplicates(test);
					// myStr.append(countMASSES(counts) + "\n");
					// System.out.println(countMASSES(counts));
					// myStr.append("Codons List: " + printStringArray(test) +
					// "\n");
					// myStr.append("Is Protein?: " + countNucs(test) + "\n");
					list1.add("Nuc. Counts: " + Arrays.toString(counts));
					list1.add(countMASSES(counts));
					list1.add("Codons List: " + printStringArray(test));
					list1.add("Is Protein?: " + countNucs(test));
					// System.out.println("Codons List: " +
					// printStringArray(test));
					// System.out.println("Is Protein?: " + countNucs(test));

				} else {
					// myStr.append("Region Name: " + line);
					list1.add("Region Name: " + line);
					// System.out.println("Region Name: " + line);
					names[index] = line;
				}
				i++;
				index++;
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		// System.out.println("Hello0");
		// System.out.println(myStr.toString());

		for (String d : list1) {
			// System.out.println(d);
			// prints [Tommy, tiger]
		}

		// The name of the file to open.
		String fileName2 = "C:/Users/Andrew Himonas/Desktop/ruler/insta60/src/insta60/temp.txt";

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(fileName2);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Note that write() does not automatically
			// append a newline character.
			// bufferedWriter.writeline(myStr.toString());
			int count = 0;
			for (String d : list1) {
				bufferedWriter.write(d);
				bufferedWriter.newLine();
				// prints [Tommy, tiger]

				if (count % 5 == 0 && count != 0)
					bufferedWriter.newLine();
				count++;
			}
			// Always close files.
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName2 + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	/*
	 * � Adenine (A): 135.128 � Cytosine (C): 111.103 � Guanine (G): 151.128 �
	 * Thymine (T): 125.107
	 */

	// ATG CCA CTA TGG TAG-TAG-TAG-TAG-TAG
	public static String[] parseNucs(String a) {

		String[] temp = new String[50];
		// String str = "";
		int myCount = 0;

		Math.floor(a.length() % 3);

		int count = 0;
		// "hello" .append("world")

		for (int i = 0; i < a.length() - 2; i = i + 3) {
			StringBuilder myStr = new StringBuilder("");
			myStr.append(a.charAt(i)).append(a.charAt(i + 1))
					.append(a.charAt(i + 2));
			// System.out.print(myStr.toString() + " ");
			temp[count] = myStr.toString();

			count++;

		}
		// System.out.println();

		// temp = new HashSet<String>(Array.asList(array)).toArray(new
		// String[0]);

		temp = removeDuplicates(temp);

		return temp;

	}

	// ATG-CCA-CTA-TGG-TAG
	// [#A = 4, #C.....]
	public static int[] countLetters(String a) {
		// A C T G
		int[] myArr = { 0, 0, 0, 0 };
		for (int i = 0; i < a.length(); i++) {
			if (a.charAt(i) == 'A')
				myArr[0] = myArr[0] + 1;

			else if (a.charAt(i) == 'C')
				myArr[1] = myArr[1] + 1;

			else if (a.charAt(i) == 'G')
				myArr[2] = myArr[2] + 1;

			else if (a.charAt(i) == 'T')
				myArr[3] = myArr[3] + 1;

			else {
			}
		}
		return myArr;

	}

	public static String[] removeDuplicates(String[] s) {
		String result[] = new String[s.length];
		int j = 0;

		for (String i : s) {
			if (!isExists(result, i))
				result[j++] = i;
		}

		return result;
	}

	private static boolean isExists(String[] array, String value) {
		for (String i : array) {
			if (i != null && i.equalsIgnoreCase(value))
				return true;
		}
		return false;
	}

	public static String countMASSES(int[] a) {

		double adenine = 135.128;
		double cytosine = 111.103;
		double guanine = 151.128;
		double thymine = 125.107;

		DecimalFormat decim = new DecimalFormat("#.#");

		double total = adenine * a[0] + cytosine * a[1] + guanine * a[2]
				+ thymine * a[3];

		double d1 = ((adenine * a[0]) / total) * 100;
		double d2 = (cytosine * a[1] / total) * 100;
		double d3 = (guanine * a[2] / total) * 100;
		double d4 = (thymine * a[3] / total) * 100;
		double p1 = Double.parseDouble(decim.format(d1));
		double p2 = Double.parseDouble(decim.format(d2));
		double p3 = Double.parseDouble(decim.format(d3));
		double p4 = Double.parseDouble(decim.format(d4));

		double[] masses = { p1, p2, p3, p4 };

		StringBuilder myStr = new StringBuilder("Total Mass%: ");
		// System.out.println("MY MASSES + " + masses.toString());
		myStr.append(Arrays.toString(masses));
		myStr.append("of ");
		myStr.append(Double.parseDouble(decim.format(total)));

		return myStr.toString();

	}

	public static String countNucs(String[] a) {
		int count = 0;
		for (int i = 0; i < a.length; i++)
			if (a[i] != null)
				count++;
		if (count > 4)
			return "YES";
		else
			return "NO";
	}

	public static String printStringArray(String[] a) {

		StringBuilder myStr = new StringBuilder("[");
		for (int i = 0; i < a.length; i++) {

			if (a[i + 1] == null) {
				myStr.append(a[i] + "]");
				break;
			}

			myStr.append(a[i] + "," + ' ');
		}
		return myStr.toString();
	}

	public static void printIntArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
		// System.out.println("");
	}
}
