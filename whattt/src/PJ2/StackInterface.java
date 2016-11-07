package PJ2;

public interface StackInterface<T> {

	public int size();

	public void push(T aData);

	public T pop();

	public T peek();

	public boolean empty();

	public void clear();
}
