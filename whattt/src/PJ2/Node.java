package PJ2;

class Node<T> {
	private T myD;
	private Node<T> myN;

	Node(T someD) {
		this(someD, null);
	}

	Node(T someD, Node<T> nnode) {
		myD = someD;
		myN = nnode;
	}

	T getData() {
		return myD;
	}

	void setData(T myDD) {
		myD = myDD;
	}

	Node<T> getNextNode() {
		return myN;
	}

	void setNextNode(Node<T> nexTN) {
		myN = nexTN;
	}
}
