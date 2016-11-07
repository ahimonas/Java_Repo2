import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MyHashMultiMap<KeyType, ValueType> {
 
	// Hash table to hold key/value pairs 
	protected MyHashTable<Entry<KeyType,ValueType>> items;  
	private int size;
	// Constructor
	public MyHashMultiMap() {
		MyHashTable<Entry<KeyType,ValueType>> items = new MyHashTable<>();
		size = 0;
	}
 
	// Associates the specified key with the specified value in this map.
	// If key is already associated with a List of values, then value
	// is added to this list; otherwise, a new List is created, value is 
	// added to this List, and key is associated with this new List.
	@SuppressWarnings("unchecked")
	public void put(KeyType key, ValueType value) {
		LinkedList<ValueType> L = new LinkedList<ValueType>();
		L.add(value);
		Entry<KeyType,ValueType> entry = new Entry<KeyType,ValueType>(key, L);
		if (items.contains(entry)) {
			items.insert(entry);
		} else {
			items.getMatch(entry).getValue().add(value);
		}
		size++;
	}
 
	// Returns the List of values that key is associated with, or null if 
	// there is no mapping for key.
	public List<ValueType> get(KeyType key) {
		LinkedList<ValueType> L = new LinkedList<ValueType>();
		Entry<KeyType,ValueType> entry = new Entry<KeyType,ValueType>(key, L);
		return items.getMatch(entry).getValue();
	}
 
	// Returns the number of elements
	// Target Complexity: O(1)
	public int size() {
		return this.size;
	}
 
	// Returns true if there are no elements.
	// Target Complexity: O(1)
	public boolean isEmpty() {
		if (size == 0) {
			  return (boolean) true;
		  } else {
			  return (boolean) false;
		  }	
	}
 
	// Make the map logically empty.
	// Target Complexity: O(1)
	public void clear() {
		items.clear();
	}
 
	// Returns a Set of the Entries contained in this map.
	public Set<Entry<KeyType, ValueType>> entrySet() {
		return items.toSet();
	}
  
	// Returns the MyHashTable<T> of items
	protected MyHashTable<Entry<KeyType,ValueType>> getItems() {
		return items;
	}
 
	// A helper method that returns an Entry created from the 
	// specified key and List. Called by put and get.
	protected Entry<KeyType,ValueType> makeEntry( KeyType key, LinkedList<ValueType> valueList ) {
		return new Entry<KeyType, ValueType>(key,valueList);
	}
 
	// A helper method for creating an Entry from a key value.
	// Called by put and get.
	protected Entry<KeyType,ValueType> makeEntry( KeyType key ) {
		return makeEntry(key, null);
	}
 
}