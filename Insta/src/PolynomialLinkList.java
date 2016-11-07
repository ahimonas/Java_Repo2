public class LinkListPolynomial implements PolynomialInterface {

	private ListNode myFrontofLisr;
	private ListNode backforList;
	private ListNode tempFront;
	private ListNode tempRear;
	private ListNode myFront_f2;
	private ListNode myRear_r2;
	private ListNode resultFront;
	private ListNode resultRear;
	private int[] currArr = new int[100];

	public LinkListPolynomial() {
		this.myFrontofLisr = null;
		this.backforList = null;
	}

	public LinkListPolynomial(String passedV) {
		passedV = sort(passedV);
		clearArray();
		String myV1 = "";
		String myE1 = "";
		boolean troeorfalse = true;
		boolean currExpo = false;

		try {
			for (int i = 0; i <= passedV.length() + 1; i++) {
				String num = passedV.charAt(i) + "";
				if ((isInteger(num) || num.equals("-") || num.equals("+"))
						&& (troeorfalse)) {
					myV1 = myV1 + num;
					currExpo = false;
				} else if (num.equals("x")) {
					currExpo = true;
				} else if (num.equals("^")) {
					troeorfalse = false;
				} else if (isInteger(num)) {
					myE1 = myE1 + num;
				} else if (num.equals("-") || (num.equals("+"))) {
					troeorfalse = true;
					currExpo = false;
					addLast(Integer.parseInt(myV1), Integer.parseInt(myE1));
					myV1 = num;
					myE1 = "";
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			if (currExpo == true) {
				if (myE1.equals("")) {

					addLast(Integer.parseInt(myV1), 1);
				} else {

					addLast(Integer.parseInt(myV1), Integer.parseInt(myE1));

				}
			} else {

				addLast(Integer.parseInt(myV1), 0);

			}
		}
	}

	public void clearArray() {
		for (int i = 0; i <= currArr.length - 1; i++) {
			currArr[i] = 0;
		}

	}

	public String sort(String passedS1) {
		String myV1 = "";
		String myE1 = "";
		boolean trueorfalse = true;
		boolean retVal = false;
		try {
			for (int i = 0; i <= passedS1.length() + 1; i++) {
				String someN = passedS1.charAt(i) + "";
				if ((isInteger(someN) || someN.equals("-") || someN.equals("+"))
						&& (trueorfalse)) {
					myV1 = myV1 + someN;
					retVal = false;
				} else if (someN.equals("x")) {
					retVal = true;
				} else if (someN.equals("^")) {
					trueorfalse = false;
				} else if (isInteger(someN)) {
					myE1 = myE1 + someN;
				} else if (someN.equals("-") || (someN.equals("+"))) {
					trueorfalse = true;
					retVal = false;
					currArr[Integer.parseInt(myE1)] = Integer.parseInt(myV1);
					myV1 = someN;
					myE1 = "";
				}
			}
		} catch (StringIndexOutOfBoundsException pasedArr) {
			if (retVal == true) {
				if (myE1.equals("")) {
					currArr[1] = Integer.parseInt(myV1);
				} else {
					currArr[Integer.parseInt(myE1)] = Integer.parseInt(myV1);
				}
			} else {
				currArr[0] = Integer.parseInt(myV1);

			}
		}
		String a = "";
		for (int i = 0; i <= currArr.length - 1; i++) {
			String num = currArr[i] + "";
			if (!num.equals("0")) {
				if (!((num).charAt(0) + "").equals("-")) {
					num = "+" + num;
				}
				a = num + "x^" + i + a;
			}
		}

		return a;
	}

	public void showList() {
		ListNode currPost = myFrontofLisr;
		while (currPost != null) {
			System.out.println("Value = " + currPost.getCoefficient()
					+ " Expo = " + currPost.getExponent());
			currPost = currPost.getLink();
		}
	}

	public void addFirst(int v1, int v2) {
		myFrontofLisr = new ListNode(v1, v2, myFrontofLisr);

	}

	public void addLast(int v1, int v2) {
		ListNode currPost = new ListNode(v1, v2, null);
		if (backforList == null) {
			myFrontofLisr = currPost;
			backforList = myFrontofLisr;
		} else {
			backforList.setLink(currPost);
			backforList = currPost;
		}

	}

	public void add1(int v1, int v2) {
		ListNode currPost = new ListNode(v1, v2, null);
		if (tempRear == null) {
			tempFront = currPost;
			tempRear = tempFront;
		} else {
			tempRear.setLink(currPost);
			tempRear = currPost;
		}
	}

	public void add2(int v1, int v2) {
		ListNode myPost = new ListNode(v1, v2, null);
		if (myRear_r2 == null) {
			myFront_f2 = myPost;
			myRear_r2 = myFront_f2;
		} else {
			myRear_r2.setLink(myPost);
			myRear_r2 = myPost;
		}
	}

	public void addFinal(int v1, int v2) {
		ListNode myPost = new ListNode(v1, v2, null);
		if (resultRear == null) {
			resultFront = myPost;
			resultRear = resultFront;
		} else {
			resultRear.setLink(myPost);
			resultRear = myPost;
		}

	}

	public boolean isInteger(String passedString) {
		if (passedString.equals("0") || passedString.equals("1")
				|| passedString.equals("2") || passedString.equals("3")
				|| passedString.equals("4") || passedString.equals("5")
				|| passedString.equals("6") || passedString.equals("7")
				|| passedString.equals("8") || passedString.equals("9")) {
			return true;
		} else
			return false;
	}

	public void setList1(String passsedString) {
		String v_1 = "";
		String e_1 = "";
		boolean trueorf = true;
		boolean currE = false;

		try {
			for (int i = 0; i <= passsedString.length() + 1; i++) {
				String myNumber = passsedString.charAt(i) + "";
				if ((isInteger(myNumber) || myNumber.equals("-") || myNumber
						.equals("+")) && (trueorf)) {
					v_1 = v_1 + myNumber;
					currE = false;
				} else if (myNumber.equals("x")) {
					currE = true;
				} else if (myNumber.equals("^")) {
					trueorf = false;
				} else if (isInteger(myNumber)) {
					e_1 = e_1 + myNumber;
				} else if (myNumber.equals("-") || (myNumber.equals("+"))) {
					trueorf = true;
					currE = false;
					add1(Integer.parseInt(v_1), Integer.parseInt(e_1));
					v_1 = myNumber;
					e_1 = "";
				}
			}
		} catch (StringIndexOutOfBoundsException passedE) {
			if (currE == true) {
				if (e_1.equals("")) {
					add1(Integer.parseInt(v_1), 1);
				} else {
					add1(Integer.parseInt(v_1), Integer.parseInt(e_1));
				}
			} else {
				add1(Integer.parseInt(v_1), 0);
			}
		}
	}

	public void setList2(String passedString) {
		String isV1 = "";
		String isE1 = "";
		boolean trueorfalse = true;
		boolean e1_expo = false;

		try {
			for (int i = 0; i <= passedString.length() + 1; i++) {
				String myN = passedString.charAt(i) + "";
				if ((isInteger(myN) || myN.equals("-") || myN.equals("+"))
						&& (trueorfalse)) {
					isV1 = isV1 + myN;
					e1_expo = false;
				} else if (myN.equals("x")) {
					e1_expo = true;
				} else if (myN.equals("^")) {
					trueorfalse = false;
				} else if (isInteger(myN)) {
					isE1 = isE1 + myN;
				} else if (myN.equals("-") || (myN.equals("+"))) {
					trueorfalse = true;
					e1_expo = false;
					add2(Integer.parseInt(isV1), Integer.parseInt(isE1));
					isV1 = myN;
					isE1 = "";
				}
			}
		} catch (StringIndexOutOfBoundsException passedE) {
			if (e1_expo == true) {
				if (isE1.equals("")) {
					add2(Integer.parseInt(isV1), 1);
				} else {
					add2(Integer.parseInt(isV1), Integer.parseInt(isE1));
				}
			} else {
				add2(Integer.parseInt(isV1), 0);
			}
		}

	}

	public PolynomialInterface add(PolynomialInterface someP) {
		setList1(this + "");
		setList2(someP + "");

		int myN1 = 0;
		int myN2 = 0;

		while (true) {
			try {
				myN1 = tempFront.getExponent();
			} catch (NullPointerException e) {
				break;
			}
			try {
				myN2 = myFront_f2.getExponent();
			} catch (NullPointerException e) {
				break;
			}

			if (myN1 > myN2) {
				addFinal(tempFront.getCoefficient(), tempFront.getExponent());
				tempFront = tempFront.getLink();
			} else if (myN2 > myN1) {
				addFinal(myFront_f2.getCoefficient(), myFront_f2.getExponent());
				myFront_f2 = myFront_f2.getLink();
			} else {
				try {
					if ((tempFront.getCoefficient() + myFront_f2
							.getCoefficient()) != 0) {
						addFinal(
								(tempFront.getCoefficient() + myFront_f2
										.getCoefficient()),
								myN1);
					}
					tempFront = tempFront.getLink();
					myFront_f2 = myFront_f2.getLink();
				} catch (NullPointerException e) {
					break;
				}
			}
		}

		String tempStr = "";
		try {
			ListNode myPos = resultFront;
			while (myPos != null) {
				String value = myPos.getCoefficient() + "";
				if (!((value).charAt(0) + "").equals("-")) {
					value = "+" + value;
				}

				tempStr = tempStr + value + "x^" + myPos.getExponent();
				myPos = myPos.getLink();
			}
		} catch (NullPointerException e) {

		}
		clearAll();
		return new LinkListPolynomial(tempStr);
	}

	public void clearAll() {
		tempFront = null;
		tempRear = null;
		myFront_f2 = null;
		myRear_r2 = null;
		resultFront = null;
		resultRear = null;
		try {
			while (true) {
				ListNode temp = tempFront.getLink();
				tempFront.setLink(null);
				tempFront = temp;

			}

		} catch (NullPointerException passedE) {
		}
		try {
			while (true) {
				ListNode temp = myFront_f2.getLink();
				myFront_f2.setLink(null);
				myFront_f2 = temp;
			}

		} catch (NullPointerException e) {
		}
		try {
			while (true) {
				ListNode temp = resultFront.getLink();
				resultFront.setLink(null);
				resultFront = temp;
			}

		} catch (NullPointerException e) {
		}
	}

	@Override
	public PolynomialInterface subtract(PolynomialInterface otherPoly) {
		setList1(this + "");
		setList2(otherPoly + "");

		int myN1 = 0;
		int myN2 = 0;

		while (true) {
			try {
				myN1 = tempFront.getExponent();
			} catch (NullPointerException e) {
				break;
			}
			try {
				myN2 = myFront_f2.getExponent();
			} catch (NullPointerException e) {
				break;
			}

			if (myN1 > myN2) {
				addFinal(tempFront.getCoefficient(), tempFront.getExponent());
				tempFront = tempFront.getLink();
			} else if (myN2 > myN1) {
				addFinal(myFront_f2.getCoefficient(), myFront_f2.getExponent());
				myFront_f2 = myFront_f2.getLink();
			} else {
				try {
					if ((tempFront.getCoefficient() - myFront_f2
							.getCoefficient()) != 0) {
						addFinal(
								(tempFront.getCoefficient() - myFront_f2
										.getCoefficient()),
								myN1);
					}
					tempFront = tempFront.getLink();
					myFront_f2 = myFront_f2.getLink();
				} catch (NullPointerException e) {
					break;
				}
			}
		}

		String myStr = "";
		try {
			ListNode myPos = resultFront;
			while (myPos != null) {
				String value = myPos.getCoefficient() + "";
				if (!((value).charAt(0) + "").equals("-")) {
					value = "+" + value;
				}

				myStr = myStr + value + "x^" + myPos.getExponent();
				myPos = myPos.getLink();
			}
		} catch (NullPointerException e) {

		}
		clearAll();
		return new LinkListPolynomial(myStr);
	}

	@Override
	public void readPolynomial() {
		// TODO Auto-generated method stub

	}

	public String toString() {
		ListNode myPos = myFrontofLisr;
		String myStr = "";
		while (myPos != null) {
			String value = myPos.getCoefficient() + "";
			if (!((value).charAt(0) + "").equals("-")) {
				value = "+" + value;
			}

			myStr = myStr + value + "x^" + myPos.getExponent();
			myPos = myPos.getLink();
		}
		return myStr;
	}

}
