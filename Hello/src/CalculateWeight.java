public class CalculateWeight {
	EvalInteger e1;
	WeightsCalc c1;

	public CalculateWeight() {
		e1 = new EvalInteger();
		c1 = new WeightsCalc();
	}

	public double returnWeight(String[] str, String[] letters, double[] weight) {

		double total = 0.0;
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
					double val = findElemntWeight(lookLetter, letters, weight);
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

	/*
	 * static boolean isInteger(String str) { try { Integer.parseInt(str);
	 * return true; } catch (NumberFormatException e) { return false; } }
	 */

}
