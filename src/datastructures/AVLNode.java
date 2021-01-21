package datastructures;

public class AVLNode {
	protected int value;
	protected int bal;
	
	private AVLNode parent;
	private AVLNode left;
	private AVLNode right;
	
	public AVLNode(int value) {
		this.value = value;
		this.bal = 0;
		this.left = null;
		this.right = null;
	}
	
	public boolean hasFreeLeaf() {
		return this.left == null || this.right == null;
	}
	
	public boolean hasChildren() {
		return this.left != null || this.right != null;
	}
	
	protected AVLNode getParent() {
		return this.parent;
	}
	
	protected boolean isLeft() {
		return this.parent.getLeft() == this;
	}
	
	protected boolean hasLeft() {
		return this.left != null;
	}
	
	protected AVLNode getLeft() {
		return this.left;
	}
	
	protected void setLeft(AVLNode node) {
		this.left = node;
		if (node != null) {
			this.left.parent = this;
		}
	}
	
	protected boolean hasRight() {
		return this.right != null;
	}
	
	protected AVLNode getRight() {
		return this.right;
	}
	
	protected void setRight(AVLNode node) {
		this.right = node;
		if (node != null) {
			this.right.parent = this;
		}
	}
	
	public void updateChildren() {
		if (left != null) {
			left.parent = this;
		}
		
		if (right != null) {
			right.parent = this;
		}
	}

	@Override
	public String toString() {
		return value + getBalString() + ":" + (left != null ? left.value : "null") + "-" + (right != null ? right.value : "null") + " " + (left != null ? left.toString() : "") + " " + (right != null ? right.toString() : "");
	}
	
	private String getBalString() {
		if (bal > 0) {
			return "+".repeat(bal);
		} else if (bal < 0) {
			return "-".repeat(-bal);
		}
		
		return "";
	}
}
