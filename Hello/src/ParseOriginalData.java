import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseOriginalData {
	BufferedReader br;
	String[] myElements;
	String[] myLetter;
	double[] myWeights;
	int count;

	public ParseOriginalData() {
		br = null;
		myElements = new String[118];
		myLetter = new String[118];
		myWeights = new double[118];
		count = 0;
	}

	public void fillMethod() {

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
	}
}
