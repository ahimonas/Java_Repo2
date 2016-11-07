public class WeightsCalc {
	public WeightsCalc() {

	}

	public double findElemntWeight(String element, String[] letters,
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
