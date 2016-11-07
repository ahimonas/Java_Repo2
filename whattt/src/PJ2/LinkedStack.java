package PJ2;

public class LinkedStack<T> implements StackInterface<T> {

	private Node<T> myTN;
	private int myC;

	public LinkedStack() {
		myTN = null;
		myC = 0;
	}

	public void push(T newData) {
		Node<T> tempN = new Node<T>(newData, myTN);
		myTN = tempN;
		myC++;
	}

	public T peek() {

		T top = null;

		if (myTN != null)
			top = myTN.getData();

		return top;
	}

	public T pop() {
		T top = peek();

		if (myTN != null) {
			myTN = myTN.getNextNode();
			myC--;
		}

		return top;
	}

	public boolean empty() {
		return myTN == null;
	}

	public int size() {

		return myC;
	}

	public void clear() {

		myTN = null;
		myC = 0;
	}

	public String toString() {

		String myR = "[";
		Node<T> nodeC = myTN;
		while (nodeC != null) {
			myR = myR + nodeC.getData() + ",";
			nodeC = nodeC.getNextNode();
		}
		myR = myR + "]";
		return myR;
	}

	public static void main(String args[]) {
		LinkedStack<Integer> mySVAr = new LinkedStack<Integer>();
		if (mySVAr.empty())
			System.out.println("OK: stack is empty");
		else
			System.out.println("Error: stack is not empty");

		mySVAr.push(10);
		mySVAr.push(30);
		mySVAr.push(50);
		System.out.println("Push 3 data: 10, 30, 50");
		System.out.println("Print stack " + mySVAr);

		if (mySVAr.size() == 3)
			System.out.println("OK: stack size is 3");
		else
			System.out.println("Error: stack size is " + mySVAr.size());

		if (mySVAr.peek() == 50)
			System.out.println("OK: peek stack top is 50");
		else
			System.out.println("Error: peek stack top is " + mySVAr.size());

		if (!mySVAr.empty())
			System.out.println("OK: stack is not empty");
		else
			System.out.println("Error: stack is empty");

		System.out.println("Pop 2 data: 50, 30");
		mySVAr.pop();
		System.out.println("Print stack " + mySVAr);
		int data = mySVAr.pop();
		System.out.println("Print stack " + mySVAr);
		if (data == 30)
			System.out.println("OK: stack pop data is 30");
		else
			System.out.println("Error: stack pop data is " + data);

		System.out.println("Clear stack");
		mySVAr.clear();
		System.out.println("Print stack " + mySVAr);
	}

}
