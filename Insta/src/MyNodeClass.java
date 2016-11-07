
public class node {
	public int myV = 0;
	public int myE = 0;

	public node() {
		myV = 0;
		myE = 0;
	}

	public node(int a, int b) {
		myV = a;
		myE = b;
	}

	public int getValue() {
		return myV;
	}

	public int getExpo() {
		return myE;
	}

	public void setValue(int n) {
		myV = n;
	}

	public void setExpo(int n) {
		myE = n;
	}

}
