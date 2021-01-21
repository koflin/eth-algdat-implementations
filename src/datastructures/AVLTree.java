package datastructures;

import java.util.NoSuchElementException;

public class AVLTree {
	private AVLNode root;
	
	public boolean search(int value) {
		if (root == null) {
			return false;
		}
		
		AVLNode current = root;
		
		while(current.value != value) {
			// No more nodes
			if (!current.hasChildren()) {
				return false;
			}
			
			// Greater
			if (value > current.value) {
				if (current.getRight() == null) {
					return false;
				}
				
				current = current.getRight();
			}
			// Smaller
			else {
				if (current.getLeft() == null) {
					return false;
				}
				
				current = current.getLeft();
			}
		}
		
		return true;
	}
	
	public void insert(int value) {
		// Return if already exists
		if (search(value)) {
			return;
		}
		
		AVLNode node = new AVLNode(value);
		
		if (root == null) {
			root = node;
		} else {
			AVLNode current = root;
			AVLNode parent = null;
			
			// Search until parent found
			while(parent == null) {
				// Is greater
				if (value > current.value) {
					if (current.hasRight()) {
						current = current.getRight();
					} else {
						parent = current;
					}
				} else {
					if (current.hasLeft()) {
						current = current.getLeft();
					} else {
						parent = current;
					}
				}
			}
			
			// Greater
			if (value > parent.value) {
				parent.setRight(node);
				
				if (parent.bal == 0) {
					parent.bal = 1;
					upin(parent, node);
				} else if(parent.bal == -1) {
					parent.bal = 0;
				} else {
					throw new IllegalStateException();
				}
			} else {
				parent.setLeft(node);;
				
				if (parent.bal == 0) {
					parent.bal = -1;
					upin(parent, node);
				} else if(parent.bal == 1) {
					parent.bal = 0;
				} else {
					throw new IllegalStateException();
				}
			}
		}
	}
	
	public void delete(int value) {
		if (!search(value)) {
			throw new NoSuchElementException();
		}
		
		
	}
	
	private void upin(AVLNode p, AVLNode x) {
		AVLNode q = p.getParent();
		
		if (q != null) {
			if (q.getLeft() == p) {
				if (q.bal == 1) {
					q.bal = 0;
				} else if (q.bal == 0) {
					upin(q, x);
				} else {
					// Rebalance left
					if (p.bal == -1) {
						// Simple rotation right
						
						if (q.getParent() == null) {
							this.root = p;
						} else if (q.isLeft()) {
							q.getParent().setLeft(p);
						} else {
							q.getParent().setRight(p);
						}
						
						q.setLeft(p.getRight());
						p.setRight(q);
						
						p.bal = 0;
						q.bal = 0;
						
					} else if (p.bal == 1) {
						// Double rotation left right
						AVLNode r = p.getRight();
						
						if (q.getParent() == null) {
							this.root = r;
						} else if (q.isLeft()) {
							q.getParent().setLeft(r);
						} else {
							q.getParent().setRight(r);
						}
						
						q.setLeft(r.getRight());
						p.setRight(r.getLeft());
						r.setLeft(p);
						r.setRight(q);
						
						r.bal = 0;
						
						if (r == x) {
							q.bal = 0;
							p.bal = 0;
						} else if (r.getLeft() == x) {
							q.bal = 1;
							p.bal = 0;
						} else {
							q.bal = 0;
							p.bal = 1;
						}
						
					} else {
						throw new IllegalStateException();
					}
				}
			} else {
				if (q.bal == -1) {
					q.bal = 0;
				} else if (q.bal == 0) {
					upin(q, x);
				} else {
					// Rebalance right
					if (p.bal == 1) {
						// Simple rotation left
						if (q.getParent() == null) {
							this.root = p;
						} else if (q.isLeft()) {
							q.getParent().setLeft(p);
						} else {
							q.getParent().setRight(p);
						}
						
						q.setRight(p.getLeft());
						p.setLeft(q);
						
						p.bal = 0;
						q.bal = 0;
					} else if (p.bal == -1) {
						// Double rotation right left
						AVLNode r = p.getLeft();
						
						if (q.getParent() == null) {
							this.root = r;
						} else if (q.isLeft()) {
							q.getParent().setLeft(r);
						} else {
							q.getParent().setRight(r);
						}
						
						q.setRight(r.getLeft());
						p.setLeft(r.getRight());
						r.setRight(p);
						r.setLeft(q);
						
						r.bal = 0;
						
						if (r == x) {
							q.bal = 0;
							p.bal = 0;
						} else if (r.getLeft() == x) {
							q.bal = 0;
							p.bal = -1;
						} else {
							q.bal = -1;
							p.bal = 0;
						}
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		return "AVLTree " + (root != null ? root.toString() : "null");
	}
}
