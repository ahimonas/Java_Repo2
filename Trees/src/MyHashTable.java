import java.util.*;

public class MyHashTable<T> {
  protected static final int DEFAULTTABLESIZE = 101; 
  protected int size;
  protected int tableSize;
  protected Object[] table;
  private boolean[] primes;
  
  // Sieve of Eratosthenes 
  
  private void fillSieve(int n) {
	  primes=new boolean[n];
	  Arrays.fill(primes,true);
	  primes[0]=primes[1]=false;
	  for (int i=2;i<primes.length;i++) {
	      if(primes[i]) {
	          for (int j=2;i*j<primes.length;j++) {
	                primes[i*j]=false;
	          }
	      }
	  }
  } 
  
  // Workhorse constructor. The internal table size is tableSize if
  // tableSize is prime or the next prime number that is
  // greater than tableSize if tableSize is not prime.
  public MyHashTable( int tableSize ) {
	  fillSieve(tableSize + 20);
	  this.tableSize = tableSize;
	  this.size = 0;
	  // primes[i] = true denotes that i is prime number 
	  if (primes[this.tableSize]) {
		  table = new Object[this.tableSize];
	  } else {
		  while ((!primes[this.tableSize]) && (this.tableSize <= tableSize + 20)) {
			  this.tableSize++;
		  }
		  table = new Object[this.tableSize];
	  }
	  // fill the table
	  for (int i = 0; i < this.tableSize; i++) {
		  table[i] = new LinkedArrays<T>();
	  }
  }
 
  // Convenience constructor. DEFAULTTABLESIZE is 101
  public MyHashTable( ) {
	  this(DEFAULTTABLESIZE);
  }
  
  // Make the hash table logically empty.
  // Target Complexity: O(n)
  public void clear( ) {
	  for (int i = 0; i < this.tableSize; i++) {
		  table[i] = null;
	  }
  }
 
  // Insert x into the hash table. If x is already present, then do 
  // nothing.
  // Throws IllegalArgumentException if x is null.
  @SuppressWarnings("unchecked")
  public void insert(T x) {
	  int hashVal;
	  if (x == null) {
		  throw new IllegalArgumentException("Exception! Inserting Null value! ");
	  } else {
		 hashVal = myhash(x);
		 if (!((LinkedArrays<T>) table[hashVal]).contains(x)) {
			 ((LinkedArrays<T>) table[hashVal]).add(x);
			 this.size++;
		 }
	  }
  }
 
  // Remove x from the hash table.
  // Throws IllegalArgumentException if x is null.
  @SuppressWarnings("unchecked")
  public void remove(T x) {
	  int hashVal;
	  if (x == null) {
		  throw new IllegalArgumentException("Exception! Inserting Null value! ");
	  } else {
		  hashVal = myhash(x);
		  if (((LinkedArrays<T>) table[hashVal]).remove(x) != null) this.size--;
	  }
  }
 
  // Return true if x is in the hash table
  // Throws IllegalArgumentException if x is null.
  @SuppressWarnings("unchecked")
  public boolean contains(T x) {
	  int hashVal;
	  if (x == null) {
		  throw new IllegalArgumentException("Exception! Inserting Null value! ");
	  } else {
		  hashVal = myhash(x);
		  return ((LinkedArrays<T>) table[hashVal]).contains(x);
	  }
  }
 
  // Return the first element in the hashed-to LinkedArrays that equals 
  // x, or null if there is no such element.
  // Throws IllegalArgumentException if x is null.
  @SuppressWarnings("unchecked")
  public T getMatch(T x) {
	  int hashVal;
	  if (x == null) {
		  throw new IllegalArgumentException("Exception! Inserting Null value! ");
	  } else {
		  hashVal = myhash(x);
		  return ((LinkedArrays<T>) table[hashVal]).getMatch(x);
	  }
  }
 
  // Returns the number of elements
  // Target Complexity: O(1)
  public int size() {
	  return (int) this.size;
  }
 
  // Returns true if there are no elements.
  // Target Complexity: O(1)
  public boolean isEmpty() {
	  if (this.size == 0) {
		  return true; 
	  } else {
		  return false;
	  }
  }
 
  // Returns a Set containing all of the T elements in the table. (Set is
  // an interface implemented by classes HashSet and TreeSet.)
  @SuppressWarnings("unchecked")
  public Set<T> toSet() {
	  HashSet<T> S = new HashSet<>();
	  for (int i = 0; i < this.tableSize; i++) {
		  S.add((T) table[i]);
	  }
	  return S;
  }
 
  // Returns a pretty representation of the hash table.
  // Uses toString() of LinkedArrays.
  // Example: For a table of size 3
  // Table:
  // 0: | two |
  // 1: | one, four |
  // 2: 
  public String toString() {
	  String S = "";
	  for (int i = 0; i < this.tableSize; i++) {
		  //table[i] = new LinkedArrays<T>();
		  S = S + i + ": " + table[i].toString();  
	  }
	  return S;
  }
 
  // Increases the size of the table by finding a prime number 
  // (nextPrime) at least as large as twice the current table size. 
  // Rehashes the elements of the hash table when size is greater than 
  // tableSize/2.
  protected void rehash( ) {
	  Object[] table1 = Arrays.copyOf(table, nextPrime(this.tableSize * 2));
	  table = table1;
  }
 
  // Internal method for computing the hash value from the hashCode of x.
  protected int myhash(T x) {
    int hashVal = x.hashCode( );
    hashVal %= tableSize;
    if( hashVal < 0 )
      hashVal += tableSize;
    return hashVal;
  }
 
  // Internal method to find a prime number at least as large as n. 
  protected static int nextPrime(int n ){
    if( n % 2 == 0 )
      n++;
    for( ; !isPrime( n ); n += 2 );
    return n;
  }
 
  // Internal method to test if a number is prime. Not an efficient 
  // algorithm. 
  protected static boolean isPrime(int n ) {
    if( n == 2 || n == 3 )
      return true;
    if( n == 1 || n % 2 == 0 )
      return false;
    for( int i = 3; i * i <= n; i += 2 )
      if( n % i == 0 )
        return false;
    return true;
  }
}
