public class VerifyCard {

	private String name;
	private String cc;
	private String expiration;
	int i;
	private int[] ccNum;
	private int[] expNum = new int[4];;

	public VerifyCard() {
	}

	public VerifyCard(String passedname, String passedcc,
			String passedexpiration) {
		name = passedname;
		cc = passedcc;
		expiration = passedexpiration;
		ccArray();
		expArray();
		for (int i = 0; i < ccNum.length; i++) {
			System.out.print(ccNum[i]);
		}

		// setCheck(name, cc, expiration);
	}

	private void expArray() {
		int length3 = expiration.length();
		int[] expArray = new int[4];
		int counter3 = 0;
		expNum[0] = Character.getNumericValue(expiration.charAt(0));
		expNum[1] = Character.getNumericValue(expiration.charAt(1));
		expNum[2] = Character.getNumericValue(expiration.charAt(3));
		expNum[3] = Character.getNumericValue(expiration.charAt(4));

		/*
		 * for (i = 0; i < ; i++) { System.out.println(expiration.charAt(i)); if
		 * (isNumeric(String.valueOf(expiration.charAt(i)))) { expArray[i] =
		 * Character.getNumericValue(expiration.charAt(i)); counter3++; } }
		 * 
		 * expNum = expArray;
		 */

		System.out.println("THIS IS THE EXPERATION DATE");
		for (int i = 0; i < 4; i++) {
			System.out.println(expNum[i]);
		}

	}

	private void ccArray() {
		// String temp = cc.replace("-", "");
		// cc = cc.replace("-", "");
		// String stringCC [] = cc.split(",");
		int length2 = cc.length();
		int[] ccArray = new int[16];
		int counter2 = 0;
		for (i = 0; i < length2; i++) {
			if (isNumeric(String.valueOf(cc.charAt(i)))) {
				ccArray[i] = Character.getNumericValue(cc.charAt(i));
				counter2++;
			}
		}

		// Print it to make sure
		// for (int i = 0; i < ccArray.length; i++) {
		// System.out.println(ccArray[i]);
		// }

		ccNum = ccArray;
	}

	public static boolean isNumeric(String str) {

		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}