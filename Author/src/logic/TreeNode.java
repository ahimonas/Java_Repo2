package logic;

public class TreeNode {

	public TreeNode tn1;
	public TreeNode tn2;
	public String myInf;

	public TreeNode(String myV1, TreeNode myL, TreeNode myR)

	{
		this.tn1 = myL;
		this.tn2 = myR;
		this.myInf = myV1;

	}

	public TreeNode() {
		this.tn1 = null;
		this.tn2 = null;
		this.myInf = "";
	}

	public TreeNode(String value) {
		tn1 = null;
		tn2 = null;
		myInf = value;
	}

	public void setInfo(String newInfo) {
		this.myInf = newInfo;
	}

	public String getInfo() {
		return this.myInf;
	}

	public void setYesNode(TreeNode node) {
		this.tn1 = node;
	}

	public void setNoNode(TreeNode node) {
		this.tn2 = node;
	}

	public TreeNode getYesNode() {
		return this.tn1;
	}

	public TreeNode getNoNode() {
		return this.tn2;
	}

	public boolean isLeaf() {
		if (this.tn1 == null && this.tn2 == null) {
			return true;
		} else {
			return false;
		}
	}

}
