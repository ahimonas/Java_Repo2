import java.util.NoSuchElementException;

class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

	private T[] myA;
	private int myCap = 0;
	private int totals = 0;
	private int myD = 0;

	@SuppressWarnings("unchecked")
	public dHeap(int heapSize) {
		this.myA = (T[]) new Comparable[heapSize];
		myCap = heapSize;
		myD = 2;
	}

	@SuppressWarnings("unchecked")
	public dHeap(int passedD, int passedHeap) throws IllegalArgumentException {
		if (passedD < 1) {
			throw new IllegalArgumentException();
		}
		myA = (T[]) new Comparable[passedHeap];
		myD = passedD;
		myCap = passedHeap;
	}

	public int size() {
		return totals;
	}

	@SuppressWarnings("unchecked")
	public void add(T myDa) throws NullPointerException {
		if (myDa == null) {
			throw new NullPointerException();
		}

		if (totals == myCap) {
			myCap *= 2;
			T[] myTArray = (T[]) new Comparable[myCap];
			for (int i = 0; i < totals; i++) {
				myTArray[i] = myA[i];
			}
			myA = myTArray;
		}

		myA[totals] = myDa;
		bubbleUp(totals);
		totals++;
	}

	public T removeSmallest() throws NoSuchElementException {
		if (totals == 0) {
			throw new NoSuchElementException();
		}
		// substitute the smallest element by the last element
		T myT = myA[0];
		myA[0] = myA[totals - 1];
		myA[totals - 1] = null;
		totals--;
		// trickle down the first element
		trickleDown(0);
		return myT;
	}

	private void trickleDown(int passedInd) {
		int myP = passedInd;
		int myC = passedInd * myD + 1;

		while (myC < totals) {

			int myF = myC;
			for (int i = 1; i < myD; i++) {
				if (myF + i <= totals - 1
						&& myA[myF + i].compareTo(myA[myC]) < 0) {
					myC = myF + i;
				}
			}

			if (myA[myC].compareTo(myA[myP]) < 0) {
				swap(myC, myP);
			}

			myP = myC;
			myC = myP * myD + 1;
		}

	}

	private void bubbleUp(int myI) {
		int child = myI;
		int parent = (child - 1) / myD;

		while (parent >= 0 && myA[child].compareTo(myA[parent]) < 0) {
			swap(child, parent);
			child = parent;
			parent = child / myD;
		}
	}

	private void swap(int i, int j) {
		T tmp = myA[i];
		myA[i] = myA[j];
		myA[j] = tmp;
	}

}
