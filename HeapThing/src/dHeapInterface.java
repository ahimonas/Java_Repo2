public interface dHeapInterface<T extends java.lang.Comparable<? super T>> {
	public void add(T o);

	public T removeSmallest();

	public int size();
}
