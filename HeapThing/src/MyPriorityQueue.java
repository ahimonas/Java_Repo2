import java.util.NoSuchElementException;

public class MyPriorityQueue<T extends Comparable<? super T>> {

	private dHeap<T> myPriQ;

	public MyPriorityQueue(int someS) {
		myPriQ = new dHeap<T>(someS);
	}

	public void add(T someD) throws NullPointerException {
		if (someD == null) {
			throw new NullPointerException();
		}
		myPriQ.add(someD);
	}

	public T poll() {
		try {
			return myPriQ.removeSmallest();
		} catch (NoSuchElementException e) {
			return null;
		}

	}
}
