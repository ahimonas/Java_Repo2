import java.util.*;


public class BinarySearchTree<T extends Comparable<? super T>> {
	protected TreeNode<T> root;        // root of tree
	protected int size;                // size of tree
	protected enum PrePostIn {PREORDER, POSTORDER, INORDER};
 
	// Constructor
	// Initialize variables - root is null and size is 0.
	public BinarySearchTree( ) {
		this.root = null;
		this.size = 0;
	}
 
	// Constructor that builds a tree from the values in sorted List L.
	// Initialize variables - root is null and size is 0 - and call 
	// recursive method buildTree() if L.size() > 0.
	// Throws IllegalArgumentException if L is null or any element in L is 
	// null.
	public BinarySearchTree(List<T> L) {
		this();
		if (L == null) {
			  throw new IllegalArgumentException("Exception! Null value! ");
		  } else {
			  if (L.size() > 0) {
				  root = buildTree(0, L.size()-1, L);
			  }
		  }
	}
		
	// Builds a balanced tree from the values in sorted List L.
	// Start and end are the start and end positions of a sub-list of L.
	// Returns the root of the subtree containing the elements in the 
	// sub-list of L.
	// Throws IllegalArgumentException if L or any element in L is null.
	// Called by BinarySearchTree(List<T> L) and balance().
	// This is a recursive method.
	protected TreeNode<T> buildTree(int start, int end, List<T> L) {
		int index = 0;
		if (start > end) return null;
		TreeNode<T> left = buildTree(start, middle(start, end) - 1, L);
		TreeNode<T> parent = new TreeNode<T>(L.get(index), null, null, null);
		parent.left = left;
		index++;
		size++;
		parent.right = buildTree(start, middle(start, end) + 1, L);
		return parent;
	} 
 
	// If x is not already in the tree, inserts x and returns true.
	// If x is already in the tree, does not insert x and returns false.
	// Sets the new nodes left, right, and parent references. 
	// Throws IllegalArgumentException if x is null.
	// This is a non-recursive method.
	public boolean insert(T x) {
		if (x == null) {
			  throw new IllegalArgumentException("Exception! Null value! ");
		  } else {
			  	TreeNode<T> newNode = new TreeNode<T>(x, null, null, null);
			  	TreeNode<T> current = root;
			  	while(current != null) {
					if (current.data.compareTo(x) < 0) {
						if (current.right != null) {
							current = current.right;
						} else {
							newNode.parent = current;
							current.right = newNode;
							size++;
							return true;
						}
					} else if (current.data.compareTo(x) > 0) {
						if (current.left != null) {
							current = current.left;
						} else {
							newNode.parent = current;
							current.left = newNode;
							size++;
							return true;
						}
					}
			  	}			  
		}
		return false;
	}
 
	// Removes x from the tree.
	// Return true if x is removed; otherwise, return false;
	// Throws IllegalArgumentException if x is null.
	// This is a non-recursive method.
	public boolean remove(T x) {
		if (x == null) {
			throw new IllegalArgumentException("Exception! Null value! ");
		} else {
			TreeNode<T> v = search(x);			// search for removing element
			if (v != null) {
				TreeNode<T> p = v.parent;
				if ((v.left == null) && (v.right == null)) { 
					// First time: a removable element - leaf
					if (p.left == v) p.left = null;
					if (p.right == v) p.right = null;
					size--;
					return true;
				} else if ((v.left == null) || (v.right == null)) { 
					// Second case: delete the element has one child
					if (v.left == null) {
						if (p.left == v) {
							p.left = v.right;
						} else {
							p.right = v.right;
							v.right.parent = p;
						}
					} else {
						if (p.left == v) {
							p.left = v.left;
						} else {
							p.right = v.left;
							v.right.parent = p;
						}
					}
					size--;
					return true;
				} else {		
					// Third case: removable element has two children
					TreeNode<T> successor = next(v);
					v.data = successor.data;
					if (successor.parent.left == successor) {
						successor.parent.left = successor.right;
						if (successor.right != null) successor.right.parent = successor.parent;
					} else {
						successor.parent.right = successor.right;
						if (successor.right != null) successor.right.parent = successor.parent;
					}
					size--;
					return true;
				}
			}
		}
		return false;
	}
 
	// Returns an element in the list that equals x, or null if there is no 
	// such element.
	// Throws IllegalArgumentException if x is null.
	// May call method search.
	// This is a non-recursive method.
	public T getMatch(T x) {
		if (x == null) {
			  throw new IllegalArgumentException("Exception! Null value! ");
		  } else {
				TreeNode<T> current = root;
				while(current != null) {
					if (current.data.compareTo(x) == 0) {
						return current.data;
					} else if(current.data.compareTo(x) > 0) {
						current = current.left;
					} else {
						current = current.right;
					}
				}
				return null;
		  }
	}
 
	// Returns true if there is an element in the list that equals x.
	// Throws IllegalArgumentException if x is null.
	// May call method search.
	// This is a non-recursive method.
	public boolean contains(T x) {
		if (x == null) {
			  throw new IllegalArgumentException("Exception! Null value! ");
		  } else {
			  if(search(x) == null) {
				  return false;
			  } else {
				  return true;
			  }		  
		  }
	}
 
	// Return a reference to the node that contains an element that equals 
	// x or null if there is no such node.
	// Any method that calls this method should ensure that x is not null.
	// This is a non-recursive method.
	protected TreeNode<T> search(T x) {
		TreeNode<T> current = root;
		while(current != null) {
			if (current.data.compareTo(x) == 0) {
				return current;
			} else if(current.data.compareTo(x) > 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return null;
	}
 
	// Returns a reference to the node that contains the minimum element in 
	// the subtree rooted at node n.
	// Called by method next(); method next() should ensure that node n is 
	// not null.
	// This is a non-recursive method.
	protected TreeNode<T> getMinNode(TreeNode<T> n) {
		TreeNode<T> current = n;
		while(current.left != null) {
			current = current.left;
		}
		return current;
	}
	
	protected TreeNode<T> getMaxNode(TreeNode<T> n) {
		TreeNode<T> current = n;
		while(current.right != null) {
			current = current.right;
		}
		return current;
	}
 
	// Returns a reference to the node that contains the minimum element in 
	// the subtree rooted at node n; the found node is also removed from the 
	// tree. Note the n may be the node containing the minimum element. 
	// Any method that calls this method should ensure that n is not null
	//   and that n is not the root.
	// This is a non-recursive method.
	protected TreeNode<T> getAndRemoveMinNode(TreeNode<T> n) {
		if ((n != null) && (n != root)) {
			TreeNode<T> m = getMinNode(n);
			if (remove(m.data))	return m;
		}
		return null;
	}
   
	// Returns the node that is the node after node n in sorted order,
	// as determined by an inorder traversal of the tree. The next node is 
	// node with the smallest data element greater than n.data.
	// May be called by remove().
	// This is a non-recursive method.
	protected TreeNode<T> next(TreeNode<T> n) {
		if (n == null) {
			  throw new IllegalArgumentException("Exception! Null value! ");
		  } else {
			  //If a node has the right subtree, then the next element is the minimum element in the subtree. 
			  //If he has no right subtree, then you need to follow up until you encounter a node 
			  //that is a left child node of its parent
			  if (n.right != null) return getMinNode(n.right);
			  TreeNode<T> m = n.parent;
			  while ((m != null) && (n == m.right)) {
				  n = m;
				  m = m.parent;
			  }
			  return m;
		  }
	}
 
	// Returns the node that is the node before n in sorted order, as
	// determined by an inorder traversal of the tree. The previous node is 
	// node with the largest data element smaller than n.data.
	// Methods next() and previous() are symmetric.
	// This is a non-recursive method.
	protected TreeNode<T> previous(TreeNode<T> n) {
		if (n == null) {
			  throw new IllegalArgumentException("Exception! Null value! ");
		  } else {
			  // If a node has a left subtree, then the next element is the maximal element in this subtree. 
			  // If he has no left subtree, then you need to follow up until 
			  // you encounter a node that is a right child node of its parent
			  if (n.left != null) return getMaxNode(n.left);
			  TreeNode<T> m = n.parent;
			  while ((m != null) && (n == m.left)) {
				  n = m;
				  m = m.parent;
			  }
			  return m;
		  }
	}
 
	// Returns the number of elements in the tree
	// Target Complexity: O(1)
	public int size() {
		return this.size;
	}
 
	// Returns true if there are no elements.
	// Target Complexity: O(1)
	public boolean isEmpty() {
		if(size == 0) return false;
		return true;
	}
 
	// Make the tree logically empty.
	// Target Complexity: O(1)
	public void clear() {
		root = null;
	}
 
	// Convenience method that returns a List of elements in sorted order.  
	// Calls getListOfElements(PrePostIn.INORDER);
	public List<T> getSortedListOfElements() {
		return getListOfElements(PrePostIn.INORDER);
	}
 
	// Returns a List of elements in the order specified by parameter order, 
	// which is either PREORDER, POSTORDER, or INORDER.
	// Calls fillListOfElements(root,order)
	public List<T> getListOfElements(PrePostIn order) {
		return fillListOfElements(root, order);
	}
 
	// Returns a List of elements in the order specified by parameter order, 
	// which is either PREORDER, POSTORDER, or INORDER.
	// This is a non-recursive method.
	// Target Complexity: O(n)
	protected List<T> fillListOfElements(TreeNode<T> node, PrePostIn order) {
		List<T> L = new ArrayList<T>();
		if (node != null) {
			switch(order) {
			case PREORDER: {
				Stack<TreeNode<T>> stack = new Stack<> (); 
		        while (node!=null || !stack.empty()){
		             if (!stack.empty()){
		            	 node = stack.pop();
		             }
		             while (node!=null){
		            	 L.add(node.data);
		                 if (node.right!=null) stack.push(node.right);
		                 node=node.left;
		             }
		        }
			}
			break;
			case POSTORDER: {
				Stack<TreeNode<T>> stack = new Stack<> ();
				while (node!=null || !stack.empty()){
		             if (!stack.empty()){
		            	 node = stack.pop();
		                 if (!stack.empty() && node.right==stack.lastElement()){
		                	 node=stack.pop();
		                 }else{
		                	 L.add(node.data);
		                	 node=null;
		                 }
		             }
		             while (node!=null){
		                 stack.push(node);
		                 if (node.right!=null){
		                     stack.push(node.right);
		                     stack.push(node);
		                 }
		                 node = node.left;
		             }
		         }
			}
			break;
			case INORDER: {
				Stack<TreeNode<T>> stack = new Stack<> (); 
		        while (node!=null || !stack.empty()){
		             if (!stack.empty()){
		            	 node=stack.pop();
		            	 L.add(node.data);
		                 if (node.right!=null) node=node.right;
		                 else node=null;
		             }
		             while (node!=null){
		                 stack.push(node);
		                 node=node.left;
		             }
		         }
			}
			break;
			}
		}
		return L;
	}
 
	// Balances the tree.
	// Calls buildTree(int start, int end, List<T> L) where L is a sorted 
	// List of the elements in the tree, and start and end are the positions 
	// at the beginning and end of L.
	public void balance() {
		List<T> L = getSortedListOfElements();
		root = buildTree(0, L.size()-1, L);
	}
 
	// Helper middle to compute the middle position of a sub-list
	protected int middle(int start, int end) {
		return (start + end) / 2;
	}
}