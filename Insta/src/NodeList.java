
public class ListNode {
	private int myCoef;
	private int myE;
	private ListNode myLnode;
	public int myNvar;

	public ListNode() {
		this.myCoef = 0;
		this.myE = 0;
		this.myLnode = null;
		this.myNvar = 0;
	}

	public ListNode(int value, int expo, int next) {
		this.myCoef = value;
		this.myE = expo;
		this.myNvar = next;
	}

	public ListNode(int myPassedV, int myPassedEx, ListNode link) {
		this.myCoef = myPassedV;
		this.myE = myPassedEx;
		this.myLnode = link;
	}

	public int getNext() {
		return myNvar;
	}

	public void setNext(int a) {
		myNvar = a;
	}

	public ListNode getLink() {
		return myLnode;
	}

	public void setLink(ListNode a) {
		myLnode = a;
	}

	public int getCoefficient() {
		return myCoef;
	}

	public int getExponent() {
		return myE;
	}

	public void setCoefficient(int n) {
		myCoef = n;
	}

	public void setExponent(int n) {
		myE = n;
	}

	public void setInArray(int a, int b) {
		myCoef = a;
		myE = b;
	}

	public String toString() {
		String a = "Value= " + getCoefficient() + "  Expo= " + getExponent();
		return a;

	}
}
