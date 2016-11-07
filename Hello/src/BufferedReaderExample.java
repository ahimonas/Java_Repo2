import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BufferedReaderExample {

	public static void main(String[] args) throws IOException {
		WeightsCalc w1 = new WeightsCalc();
		ParseOriginalData d1 = new ParseOriginalData();
		BufferedReader br = null;
		String[] myElements = d1.myElements;

		String[] myLetter = d1.myLetter;
		double[] myWeights = d1.myWeights;
		int count = 0;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(
					"C:/Users/Andrew Himonas/Desktop/elements.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				myElements[count] = sCurrentLine;

				// gets the letter
				int numberStart = sCurrentLine.indexOf("\t");
				String s1 = sCurrentLine.substring(numberStart + 1,
						sCurrentLine.length() - 1);

				int numberStart2 = s1.indexOf("\t");
				String letter = s1.substring(0, numberStart2);
				// System.out.println(letter);
				myLetter[count] = letter;
				// gets the weight
				int lastindex = sCurrentLine.lastIndexOf("\t");
				String number = sCurrentLine.substring(lastindex + 1,
						sCurrentLine.length());
				if (number.equals("Unk"))
					myWeights[count] = -1.00;
				else
					myWeights[count] = Double.parseDouble(number);
				/**
				 * CHECKOUT THIS CASt TO A DOUBLE BECAUSE WE READ THE .txt FILE
				 * IN AS A STRING
				 */

				count++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		/* NOTICED I PRINTED OUT THE TWO ARRAYS */
		/**
		 * myLetter contains the element letters, myWeights contains the weights
		 * as double
		 **/
		for (int i = 0; i < myElements.length; i++) {
			// System.out.println(myLetter[i]);
			// System.out.println(myWeights[i]);
		}

		Scanner scan = new Scanner(System.in);

		StringBuilder myOut = new StringBuilder();

		System.out.println("Hello Scientist please enter your name: ");
		String currName = scan.nextLine();
		myOut.append(currName);
		myOut.append(System.getProperty("line.separator"));
		System.out
				.println("Enter a chemical you would like to weight enter using +, such as H2+Li3 or q to quit");
		String currChemical = scan.nextLine();
		myOut.append(currChemical + "\n");
		while (!currChemical.equals("q")) {

			String[] myW1 = calculateWeight(currChemical);
			CalculateWeight cw12 = new CalculateWeight();
			double val = cw12.returnWeight(myW1, myLetter, myWeights);
			System.out.println(val);
			myOut.append(System.getProperty("line.separator"));
			myOut.append("The weight of these elementes is: " + val);
			myOut.append(System.getProperty("line.separator"));

			System.out
					.println("Please enter another chemical you would like to weight using +, enter q to quit");
			currChemical = scan.nextLine();
			myOut.append(currChemical);
			myOut.append(System.getProperty("line.separator"));

		}
		/*
		 * File file = new File("C:/Users/Andrew Himonas/Desktop/file.txt"); try
		 * (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
		 * writer.write(myOut.toString()); }
		 */
		WriteOut myW = new WriteOut(myOut);
	}

	public static String[] calculateWeight(String str) {
		// int numberStart2 = str.indexOf("+");
		String strNew = str;

		String[] broken = new String[10];
		int index = str.indexOf("+");
		int count = 0;

		while (index != -1) {
			index = strNew.indexOf("+");

			if (index == -1) {
				broken[count] = strNew.substring(1, strNew.length());

			} else {
				String temp = strNew.substring(0, index);
				broken[count] = temp;
				count++;

				strNew = strNew.substring(index + 1, strNew.length());
				index = strNew.indexOf("+");
			}

			if (index == -1) {
				broken[count] = strNew;
				break;
			}
		}

		// for (int i = 0; i < broken.length; i++)
		// System.out.println(broken[i]);

		return broken;
	}

	static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static double returnWeight(String[] str, String[] letters,
			double[] weight) {

		double total = 0.0;
		EvalInteger e1 = new EvalInteger();
		for (int i = 0; i < str.length; i++)
		// if(isInteger(str[i].substring((str[i].length()-1),
		// (str[i].length()))))
		{
			if (str[i] != null)
				if (e1.isInteger(str[i].substring((str[i].length() - 1),
						(str[i].length())))) {

					// System.out.println(str[i].substring(0, (str[i].length() -
					// 1)));

					int number = Integer.parseInt(str[i].substring(
							(str[i].length() - 1), (str[i].length())));

					String lookLetter = str[i].substring(0,
							(str[i].length() - 1));
					double val = findElemntWeight(lookLetter, letters, weight);

					double mnumber = number * val;
					// System.out.println(mnumber);
					// System.out.println(str[i].substring((str[i].length() -
					// 1),
					// (str[i].length())));
					total = total + mnumber;
				} else {
					String lookLetter = str[i];
					WeightsCalc w1 = new WeightsCalc();
					double val = w1.findElemntWeight(lookLetter, letters,
							weight);
					total = total + val;
				}
		}
		// System.out.println(str[i].substring((str[i].length() - 1),
		// (str[i].length())));

		return total;
	}

	public static double findElemntWeight(String element, String[] letters,
			double[] weight) {

		for (int i = 0; i < letters.length; i++) {
			String str = letters[i];
			if (str != null)
				if (element.equals(str)) {
					return weight[i];
				}
		}
		return -1.00;
	}
}