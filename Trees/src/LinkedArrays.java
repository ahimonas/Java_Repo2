public class LinkedArrays<T> {
  protected int size;                 // number of elements
  protected int nodeCount;            // number of LinkedArrayNodes
  protected final int lengthOfArrays; // value initialized in constructor
  protected static final int DEFAULTLENGTHOFARRAYS = 16;
  protected LinkedArrayNode<T> head;        // dummy nodes head and tail
  protected LinkedArrayNode<T> tail;
 
  // Workhorse constructor that initializes variables.
  // Throws IllegalArgumentException if lengthOfArray < 0.
  public LinkedArrays (int lengthOfArrays) throws IllegalArgumentException {
	  if (lengthOfArrays < 0) {
		  throw new IllegalArgumentException("Exception! Length Of Arrays less then 0!: " + lengthOfArrays); 
	  } else {
		  this.lengthOfArrays = lengthOfArrays;
		  this.nodeCount = 2;
		  this.size = 0;
		  this.head = new LinkedArrayNode<T>(null, null);
		  this.tail = new LinkedArrayNode<T>(null, null);
		  head.next = tail;
		  tail.prev = head;
	  }
  }
 
  // Convenience constructor. Calls the workhorse constructor with 
  // DEFAULTLENGTHOFARRAYS as the argument.
  public LinkedArrays() {
	  this(DEFAULTLENGTHOFARRAYS);
  }
 
  // Make this LinkedArrays logically empty.
  // Target Complexity: O(1)
  // Implementation note: It is not necessary to remove() all of the 
  // elements; instead, some data members can be reinitialized.
  // Target Complexity: O(1)
  public void clear(){
	  this.head = null;
	  this.tail = null;
  }
 
  // Returns the number of elements
  // Target Complexity: O(1)
  public int size() {
	  return (int) size;
  }
 
  // Returns the number of LinkedArrayNodes.
  // Target Complexity: O(1)
  public int nodeCount() {
	  return (int) nodeCount;
  }
 
  // Returns true if there are no elements in this LinkedArrays
  // Target Complexity: O(1)
  public boolean isEmpty() {
	  if (nodeCount == 0) {
		  return (boolean) true;
	  } else {
		  return (boolean) false;
	  }		  
  }
  
  // Workhorse private method for searching
  private T search(LinkedArrayNode<T> node, T x) {
	  if (node == null) return null;
	  T y = node.getMatch(x);
	  if (y != null) return y;
	  return search(node.next, x);
  }
  
  // Returns the first element that equals x, or null 
  // if there is no such element.
  // Throws IllegalArgumentException if x is null.
  // Target Complexity: O(n) for n elements
  public T getMatch(T x) throws IllegalArgumentException {
	  if (x == null) {
		  throw new IllegalArgumentException("Exception! searching for Null value! ");
	  } else {
		  return search(head, x);
	  }
  }
  
  
  // Returns true if this LinkedArrays contains the specified element.
  // Throws IllegalArgumentException if x is null. May call getMatch.
  // Target Complexity: O(n)
  public boolean contains (T x) {
	  if (x == null) {
		  throw new IllegalArgumentException("Exception! searching for Null value! ");
	  } else {
		  if (getMatch(x) == null) {
			  return false;
		  } else {
			  return true;
		  }
	  }
  }
  
  //Workhorse private method for adding
  private boolean addVal(LinkedArrayNode<T> nodepr, LinkedArrayNode<T> node, T x) {
	  if (node.equals(null)) {
		  node = new LinkedArrayNode<T>(null, null);
		  nodeCount++;
		  if (!nodepr.equals(null)) {
			  nodepr.next = node;
			  node.prev = nodepr;
		  }
	  }
	  if (node.arraySize < lengthOfArrays) {
		  node.add(x);
		  return true;
	  } else {
		  return addVal(node, node.next, x);
	  }
  }
 
  // Insert x into the first LinkedArrayNode with an available space in 
  // its array. 
  // Returns true if x was added.
  // Throws IllegalArgumentException if x is null.
  // Target Complexity: O(number of nodes)
  public boolean add(T x) {
	  if (x == null) {
		  throw new IllegalArgumentException("Exception! Adding Null value! ");
	  } else {
		  return addVal(null, head, x);
	  }
  }
 
  // Workhorse private method for removing
  private T removeVal(LinkedArrayNode<T> node, T x) {
	  if (node.equals(null)) return null;
	  T y = node.remove(x);
	  if (y.equals(null)) {
		  return removeVal(node.next, x);
	  } else {
		  // Check if current node is empty
		  if (node.arraySize == 0) {
			  // if it is, cut it off
			  if (!node.prev.equals(null)) {
				  node.prev.next = node.next;
				  if (!node.next.equals(null)) {
					  node.next.prev = node.prev;
				  } else {
					  this.tail = node.prev; 
				  }
			  } else {
				  this.head = node.next;
			  }
			  node = null;
		  }
		  return y;
	  }
  }
  // Remove the first occurrence of x if x is present. 
  // Returns a reference to the removed element if it is removed. 
  // When the last data element is removed from (the array in) a
  // LinkedArrayNode, the LinkedArrayNode is removed from the 
  // LinkedArrays.
  // Throws IllegalArgumentException if x is null.
  // Target Complexity: O(n)
  protected T remove(T x) {
	  if (x == null) {
		  throw new IllegalArgumentException("Exception! Removing Null value! ");
	  } else {
		  return removeVal(head, x);
	  }
  }
    
  // Returns a pretty representation of the LinkedArrays.
  // Example: A LinkedArrays with two LinkedArrayNodes that have arrays 
  // of length two. The size of the first array is two and the size of
  // the second array is one: | 4, 2 | 3 |
  public String toString() {
	  String s = "";
	  LinkedArrayNode<T> node = head;
	  while (node != null) {
		  s = s + node.toString() + " | ";
		  node = node.next;
	  }
	  return s;
  }
}
