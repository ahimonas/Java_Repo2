import java.util.*;

public class Entry<KeyType, ValueType> {
  protected KeyType key;
  protected LinkedList<ValueType> valueList;
 
  // Constructor
  // Target Complexity: O(1)               
  public Entry(KeyType k, LinkedList<ValueType> list) {
	  this.key = k;
	  this.valueList = list;
  }
 
  // Returns the hash code value for this map entry. 
  // The hashCode of the Entry is the hashCode of the Entry's key, i.e,
  // the Entry’s value is ignored during hashing.
  public int hashCode() {
	  return key.hashCode();
  }
 
  // Compares the specified object with this entry for equality.
  // The equality of two Entries is based on the equality of their keys.
  @SuppressWarnings("unchecked")
  public boolean equals( Object rhs ) {
	  if (key == ((Entry<KeyType, ValueType>) rhs).getKey()) return true;
	  return false;
  }
 
  // Returns the key corresponding to this entry.          
  public KeyType getKey() {
	  return key;
  }
 
  // Returns the value corresponding to this entry.
  public LinkedList<ValueType> getValue() {
	  return valueList;
  }
 
  // Returns a pretty representation of the Entry.
  // Format: key, valueList. Example: When key = "Carver" and valueList = 
  // [CS310-003, SWE437-001]: “Carver, [CS310-003, SWE437-001]”
  // Note: “[a, b]” is returned by LinkedList.toString for a LinkedList 
  // containing Strings “a” and “b”.
  public String toString() {
	  String S = "";
	  S = S + key + ", [";
	  S = S + valueList.get(0).toString();
	  for (int i = 1; i < valueList.size(); i++) {
          S = S + ", " + valueList.get(i).toString();
      }
	  return S + "]";
  }
}
