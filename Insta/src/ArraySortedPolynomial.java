public class ArraySortedPolynomial implements PolynomialInterface {
	public node restAns[] = new node[101];
	public node myCons[] = new node[101];
	public node polyMum2[] = new node[101];
	public node poly_num1[] = new node[101];
	public int myC = 0;

	public ArraySortedPolynomial(String s1) {
		String myV1 = "";
		String myE1 = "";
		boolean trueorfalse = true;
		boolean e1_e1 = false;

		try {
			for (int i = 0; i <= s1.length() + 1; i++) {
				String num = s1.charAt(i) + "";
				if ((isInteger(num) || num.equals("-") || num.equals("+"))
						&& (trueorfalse)) {
					myV1 = myV1 + num;
					e1_e1 = false;
				} else if (num.equals("x")) {
					e1_e1 = true;
				} else if (num.equals("^")) {
					trueorfalse = false;
				} else if (isInteger(num)) {
					myE1 = myE1 + num;
				} else if (num.equals("-") || (num.equals("+"))) {
					trueorfalse = true;
					e1_e1 = false;
					myCons[myC] = new node(Integer.parseInt(myV1),
							Integer.parseInt(myE1));
					myC++;
					myV1 = num;
					myE1 = "";
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			if (e1_e1 == true) {
				if (myE1.equals("")) {
					myCons[myC] = new node(Integer.parseInt(myV1), 1);
					myC++;
				} else {
					myCons[myC] = new node(Integer.parseInt(myV1),
							Integer.parseInt(myE1));
					myC++;
				}
			} else {
				myCons[myC] = new node(Integer.parseInt(myV1), 0);
				myC++;
			}
		}
		myC = 0;

	}

	public boolean isInteger(String passedStr) {
		if (passedStr.equals("0") || passedStr.equals("1")
				|| passedStr.equals("2") || passedStr.equals("3")
				|| passedStr.equals("4") || passedStr.equals("5")
				|| passedStr.equals("6") || passedStr.equals("7")
				|| passedStr.equals("8") || passedStr.equals("9")) {
			return true;
		} else
			return false;
	}

	@Override
	public PolynomialInterface add(PolynomialInterface poly2) {
		setArray(this + "", poly_num1);
		setArray(poly2 + "", polyMum2);
		int myN1 = 0;
		int myN2 = 0;
		int my_var1 = 0;
		int my_var2 = 0;
		int myVar3 = 0;

		while (true) {
			try {
				myN1 = poly_num1[my_var1].getExpo();
			} catch (NullPointerException e) {
				break;
			}
			try {
				myN2 = polyMum2[my_var2].getExpo();
			} catch (NullPointerException e) {
				break;
			}

			if (myN1 > myN2) {
				restAns[myVar3] = poly_num1[my_var1];
				myVar3++;
				my_var1++;
			} else if (myN2 > myN1) {

				restAns[myVar3] = polyMum2[my_var2];
				my_var2++;
				myVar3++;
			} else {

				try {
					if ((poly_num1[my_var1].getValue() + polyMum2[my_var2]
							.getValue()) != 0) {
						restAns[myVar3] = new node(
								(poly_num1[my_var1].getValue() + polyMum2[my_var2]
										.getValue()), myN1);
						myVar3++;
					}
					my_var2++;
					my_var1++;
				} catch (NullPointerException e) {
					break;
				}
			}
		}

		String myStr1 = "";
		String myStr2 = "";
		try {
			for (int i = 0; i <= restAns.length - 1; i++) {
				String value = restAns[i].getValue() + "";
				if (!((value).charAt(0) + "").equals("-")) {
					value = "+" + value;
				}
				if (restAns[i].getValue() != 0) {
					myStr1 = value + "x^" + restAns[i].getExpo();
					myStr2 = myStr2 + myStr1;
				}

			}
		} catch (NullPointerException e) {

		}
		clearArrays(poly_num1, polyMum2, restAns);
		return new ArraySortedPolynomial(myStr2);
	}

	private void clearArrays(node[] p1, node[] p2, node[] myPollyAnswer) {
		for (int i = 0; i <= p1.length - 1; i++) {
			try {
				p1[i].setValue(0);
				p1[i].setExpo(0);
				p2[i].setValue(0);
				p2[i].setExpo(0);
				myPollyAnswer[i].setValue(0);
				myPollyAnswer[i].setExpo(0);
			} catch (NullPointerException e) {

			}

		}
	}

	private void setArray(String passedS1, node[] array) {
		String isV = "";
		String myE = "";
		boolean trueorfalse = true;
		boolean myE3 = false;

		try {
			for (int i = 0; i <= passedS1.length() + 1; i++) {
				String num = passedS1.charAt(i) + "";
				if ((isInteger(num) || num.equals("-") || num.equals("+"))
						&& (trueorfalse)) {
					isV = isV + num;
					myE3 = false;
				} else if (num.equals("x")) {
					myE3 = true;
				} else if (num.equals("^")) {
					trueorfalse = false;
				} else if (isInteger(num)) {
					myE = myE + num;
				} else if (num.equals("-") || (num.equals("+"))) {
					trueorfalse = true;
					myE3 = false;
					array[myC] = new node(Integer.parseInt(isV),
							Integer.parseInt(myE));
					myC++;
					isV = num;
					myE = "";
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			if (myE3 == true) {
				if (myE.equals("")) {
					array[myC] = new node(Integer.parseInt(isV), 1);
					myC++;
				} else {
					array[myC] = new node(Integer.parseInt(isV),
							Integer.parseInt(myE));
					myC++;
				}
			} else {
				array[myC] = new node(Integer.parseInt(isV), 0);
				myC++;
			}
		}
		myC = 0;

	}

	@Override
	public PolynomialInterface subtract(PolynomialInterface polyOther) {
		setArray(this + "", poly_num1);
		setArray(polyOther + "", polyMum2);
		int var1 = 0;
		int var2 = 0;
		int var3 = 0;
		int var4 = 0;
		int var5 = 0;

		while (true) {
			try {
				var1 = poly_num1[var3].getExpo();
			} catch (NullPointerException e) {
				break;
			}
			try {
				var2 = polyMum2[var4].getExpo();
			} catch (NullPointerException e) {
				break;
			}

			if (var1 > var2) {
				restAns[var5] = poly_num1[var3];
				var5++;
				var3++;
			} else if (var2 > var1) {
				restAns[var5] = polyMum2[var4];
				var4++;
				var5++;
			} else {
				try {
					if ((poly_num1[var3].getValue() - polyMum2[var4].getValue()) != 0) {
						restAns[var5] = new node(
								(poly_num1[var3].getValue() - polyMum2[var4]
										.getValue()),
								var1);
						var5++;
					}
					var4++;
					var3++;
				} catch (NullPointerException e) {
					break;
				}
			}
		}

		String my_secondv = "";
		String my_othervar = "";
		try {
			for (int i = 0; i <= restAns.length - 1; i++) {
				String value = restAns[i].getValue() + "";
				if (!((value).charAt(0) + "").equals("-")) {
					value = "+" + value;
				}
				if (restAns[i].getValue() != 0) {
					my_secondv = value + "x^" + restAns[i].getExpo();
					my_othervar = my_othervar + my_secondv;
				}

			}
		} catch (NullPointerException e) {

		}
		clearArrays(poly_num1, polyMum2, restAns);
		return new ArraySortedPolynomial(my_othervar);
	}

	@Override
	public void readPolynomial() {

	}

	public String toString() {
		String my_v1 = "";
		String rrr = "";
		try {
			for (int i = 0; i <= myCons.length - 1; i++) {
				String value = myCons[i].getValue() + "";
				if (!((value).charAt(0) + "").equals("-")) {
					value = "+" + value;
				}
				my_v1 = value + "x^" + myCons[i].getExpo();
				my_v2 = my_v2 + my_v1;
			}
		} catch (NullPointerException e) {

		}

		return my_v2;
	}

}
