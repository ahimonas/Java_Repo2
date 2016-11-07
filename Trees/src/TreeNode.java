public class TreeNode<T> {
	protected TreeNode<T> left;
	protected TreeNode<T> right;
	protected TreeNode<T> parent;
	protected T data;
	 
	// Constructor initializes data members.
	// Target Complexity: O(1)
	public TreeNode(T data,TreeNode<T> left,TreeNode<T> right,TreeNode<T> parent) {
		this.left = left;
		this.right = right;
		this.parent = parent;
		this.data = data;
	}
	 
	// Returns a pretty representation of the node.
	// Format: [data]. Example: [3]
	// Target Complexity: O(1)
	public String toString() {
		return "[" + data.toString() + "]";
	}
	 
	// Returns a pretty representation of the node for debugging.
	// Shows the data values of this node and its left, right, and parent 
	// nodes. Format:[D:data,L:left,R:right,P:parent]. 
	// Example:[D:3, L:1, R:2, P:0]
	// Target Complexity: O(1)
	public String debugToString() {
		return "[D:" + data.toString() + ", L:" + this.left.toString() + ", R:" + this.right.toString() + ", P:" + this.parent.toString() + "]";
	}
} 