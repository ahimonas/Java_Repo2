import java.util.Arrays;

import javax.swing.JOptionPane;

public class Program7 {
	public static float getAverage(int x[]) {
		float sum = 0.0f;
		for (int i = 0; i < x.length; i++)
			sum += x[i];
		return sum / x.length;
	}

	public static String putArray(String names[], int x[]) {
		String result = "";
		for (int i = 0; i < x.length; i++)
			result += names[i] + " " + x[i] + " ";
		return result;
	}

	public static int getHighest(int x[]) {
		int hi = x[0];
		for (int i = 1; i < x.length; i++)
			if (x[i] > hi)
				hi = x[i];
		return hi;
	}

	public static int getLowest(int x[]) {
		int lo = x[0];
		for (int i = 1; i < x.length; i++)
			if (x[i] < lo)
				lo = x[i];
		return lo;
	}

	public static int[] getData(String input) {
		String[] data = input.split("\\s+");
		int[] x = new int[data.length];
		for (int i = 0; i < data.length; i++)
			try {
				x[i] = Integer.parseInt(data[i]);
			} catch (NumberFormatException nef) {
				x[i] = 0;
				System.err.println("Input error");
				System.err.println(nef.getMessage());
			}
		return x;
	}

	public static int[] getGrades(String input) {
		String[] data = input.split("\\s+");
		int[] x = new int[data.length / 2];
		for (int i = 0; i < x.length; i++) {
			x[i] = Integer.parseInt(data[2 * i + 1]);
		}
		return x;
	}

	public static String[] getNames(String input) {
		String[] data = input.split("\\s+");
		String[] x = new String[data.length / 2];
		for (int i = 0; i < x.length; i++) {
			x[i] = data[2 * i];
		}
		return x;
	}

	public static int[] sortBubble(int x[]) {
		for (int i = 0; i < x.length - 1; i++) {
			for (int j = 0; j < x.length - 1; j++) {
				if (x[j + 1] > x[j]) {
					int tmp = x[j + 1];
					x[j + 1] = x[j];
					x[j] = tmp;
				}
			}
		}
		return x;
	}

	public static int[] sortSelection(int x[]) {
		for (int i = 0; i < x.length - 1; i++) {
			int largest = i;
			for (int j = largest + 1; j < x.length; j++)
				if (x[j] > x[largest])
					largest = j;
			int tmp = x[largest];
			x[largest] = x[i];
			x[i] = tmp;
		}
		return x;
	}

	public static String arrayStuff(String [] x) {
		Arrays.sort(x);
		String str ="";
		for(int i = 0; i < x.length; i++)
			str.concat(str)
		return null; 
	}

	public static void main(String[] args) {
		String input = JOptionPane
				.showInputDialog("Enter 1 or more students(name grade)");
		String[] names = getNames(input);
		int grades[] = getGrades(input);
		// IO.showMessage(
		System.out.println("Array\n" + putArray(names, grades)
				+ String.format("\nAverage:1.2f", getAverage(grades))
				+ "\nHighest:" + getHighest(grades) + "\nLowest:"
				+ getLowest(grades) + "\nSorted:" + sortBubble(grades)

		);

	}

}
