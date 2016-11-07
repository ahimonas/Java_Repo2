package logic;

public class YesNoTree {

	private TreeNode myCurrRootNode;

	public YesNoTree() {
		myCurrRootNode = new TreeNode();
	}

	public YesNoTree(TreeNode root) {
		myCurrRootNode = root;
	}

	public void insertInfo(TreeNode node, String info) {
		node.setInfo(info);
	}

	public void insertYesNode(TreeNode node, TreeNode yesNode) {
		node.setYesNode(yesNode);
	}

	public void insertNoNode(TreeNode node, TreeNode noNode) {
		node.setNoNode(noNode);
	}

	public String getInfo(TreeNode node) {
		return node.getInfo();
	}

	public String moveYesNode() {
		myCurrRootNode = myCurrRootNode.getYesNode();
		return myCurrRootNode.getInfo();
	}

	public String moveNoNode() {
		myCurrRootNode = myCurrRootNode.getNoNode();
		return myCurrRootNode.getInfo();
	}

	public boolean hasNext() {
		if (this.myCurrRootNode.isLeaf()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isEmpty() {
		if (myCurrRootNode.getInfo() == "") {
			return true;
		} else {
			return false;
		}
	}

	public TreeNode getCurrentNode() {
		return this.myCurrRootNode;
	}

}
