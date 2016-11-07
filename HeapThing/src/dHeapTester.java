
import java.util.NoSuchElementException;

public class dHeapTester {

	private dHeap<Integer> myNHeap;
	private dHeap<String> myStrH;

	/**
	 * setup that initializes dheap before each test
	 */
	public void setUp() {
		myNHeap = new dHeap<Integer>(3, 10);
		myStrH = new dHeap<String>(7);
		myNHeap.add(1);
		myNHeap.add(2);
		myNHeap.add(3);
		myNHeap.add(4);
		myNHeap.add(5);
		myNHeap.add(6);
		myNHeap.add(7);
		myStrH.add("a");
		myStrH.add("b");
		myStrH.add("c");
		myStrH.add("d");
		myStrH.add("e");
	}

	/**
	 * Tester to check add and remove functionality
	 */

	public void testAddAndRemove() {
		myNHeap.add(0);
		myNHeap.add(4);
		myNHeap.add(7);
		myNHeap.add(10);

		myStrH.add("b");
		myStrH.add("d");
		myStrH.add("f");
		myStrH.add("g");
		myStrH.add("h");

	}

	public void testConstructorIllegalArgumentException() {
		try {
			myNHeap = new dHeap<Integer>(0, 10);
		} catch (IllegalArgumentException e) {
			// normal
		}
	}

	public void testAddNullPointerException() {
		try {
			myNHeap.add(null);
		} catch (NullPointerException e) {
			// normal
		}
	}

	public void testRemoveSmallestNoSuchElementException() {
		try {
			myStrH.removeSmallest();
			myStrH.removeSmallest();
			myStrH.removeSmallest();
			myStrH.removeSmallest();
			myStrH.removeSmallest();
			myStrH.removeSmallest();
		} catch (NoSuchElementException e) {
			// normal
		}
	}

}
