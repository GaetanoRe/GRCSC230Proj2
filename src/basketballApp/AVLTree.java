package basketballApp;

/**
 * <p>Title: BinarySearchTree class</p>
 * <p>Description: This is a generic binary tree class that contains nodes that have an integer key and a generic piece of data</p>
 * @author Gaetano Re 
 * N#: N00918949
 */
public class AVLTree<E extends Comparable<E>> implements Tree<E>{

	private AVLNode<E> root;

	/**
	 * insert method
	 * Description: inserts the node at the root and initiates the recursive insert method
	 * @param the data to insert into the tree.
	 * @return the resulting tree
	 */
	@Override
	public AVLTree<E> insert(E data) {
		root = insert(data, root);
		return this;
	}
	
	/**
	 * delete method
	 * Description: deletes a node with the given data using a recursive delete method
	 * @param the data to delete from the tree.
	 */
	@Override
	public void delete(E data) {
		root = remove(data, root);
	}
	
	/**
	 * traverse method
	 * Description: displays the the data in the tree using InOrder Traversal
	 */
	@Override
	public void traverse() {
		traverseInOrder(root);
	}
	/**
	 * getMax method
	 * Description: Starts at the root of the node and finds the maximum node of the tree
	 * @return the maximum value of the tree
	 */
	@Override
	public E getMax() {
		if(isEmpty()) {
			return null;
		}
		return getMax(root);
	}

	/**
	 * getMin method
	 * Description: Starts at the root of the node and finds the minimum node of the tree
	 * @return the minimum node of the tree.
	 */
	@Override
	public E getMin() {
		if(isEmpty()) {
			return null;
		}
		return getMin(root);
	}
	
	/**
	 * isEmpty method
	 * Description: returns a boolean dictating whether or not the tree is empty
	 */
	@Override
	public boolean isEmpty() {
		return this.root == null;
	}
	
	/**
	 * This method retrieves the value 
	 * @param data
	 * @return
	 */
	public E get(E data) {
		return get(data, root) != null ? get(data, root).getData() : null;
	}
	
	public AVLNode<E> getRoot() {
		return root;
	}
	
	private void traverseInOrder(AVLNode<E> node) {
		if(node != null) {
			traverseInOrder(node.getLeftChild());
			System.out.println(node);
			traverseInOrder(node.getRightChild());
		}
	}
	
	/**
	 * getMax method
	 * Description: Returns the maximum value in the tree by accessing the right-most child of the current node
	 * @param the current node this method is focused on
	 * @return the maximum node of the current node.
	 */
	private E getMax(AVLNode<E> node){
		if(node.getRightChild() != null) { // If the right child exists on the current node
			return getMax(node.getRightChild()); // traverse to the next right node
		}
		else { // else, the current node is the right-most node
			return node.getData(); // return the maximum data in the tree
		}
	}
	/**
	 * getMin method
	 * Description: Returns the maximum value in the tree by accessing the left-most child of the current node
	 * @param the current node this method is focused on
	 * @return the minimum node of the current node.
	 */
	private E getMin(AVLNode<E> node) { 
		if(node.getLeftChild() != null) { // If the left child exists on the current node
			return getMin(node.getLeftChild()); // traverse to the left node
		}
		else { // else, the current node is the minimum node
			return node.getData(); // return the minimum data in the tree
		}
	}
	
	private AVLNode<E> insert(E data, AVLNode<E> node) {
		if(node == null) {
			return new AVLNode<E>(data);
		}
		else if(data.compareTo(node.getData()) < 0) {
			node.setLeftChild(insert(data, node.getLeftChild()));
		}
		else if(data.compareTo(node.getData()) > 0) {
			node.setRightChild(insert(data, node.getRightChild()));
		}
		else {
			return node;
		}
		
		updateHeight(node);
		return applyRotation(node);
		
	}
	
	/**
	 * remove helper method
	 * 
	 * @param data
	 * @param node
	 * @return
	 */
	private AVLNode<E> remove(E data, AVLNode<E> node){
		if(node == null) {
			return null;
		}
		if(data.compareTo(node.getData()) < 0) {
			node.setLeftChild(remove(data, node.getLeftChild()));
		}
		else if(data.compareTo(node.getData()) > 0) {
			node.setRightChild(remove(data, node.getRightChild()));
		}else {
			if(node.getLeftChild() == null) {
				return node.getRightChild();
			}
			else if(node.getRightChild() == null) {
				return node.getLeftChild();
			}
			node.setData(getMax(node.getLeftChild()));
			node.setLeftChild(remove(node.getData(), node.getLeftChild()));
			
		}
		updateHeight(node);
		return applyRotation(node);
	}
	
	private void updateHeight(AVLNode<E> node) {
		int maxHeight = Math.max(height(node.getLeftChild()), height(node.getRightChild()));
		node.setHeight(maxHeight + 1);
	}
	
	private AVLNode<E> applyRotation(AVLNode<E> node){
		int balanceFactor = getBalanceFactor(node);
		if(balanceFactor > 1) {
			if(getBalanceFactor(node.getLeftChild()) < 0) {
				node.setLeftChild(rotateLeft(node.getLeftChild()));
			}
			return rotateRight(node);
		}
		else if(balanceFactor < -1) {
			if(getBalanceFactor(node.getRightChild()) > 0) {
				node.setRightChild(rotateRight(node.getRightChild()));
			}
			return rotateLeft(node);
		}
		
		return node;
	}
	
	private AVLNode<E> rotateLeft(AVLNode<E> z){
		AVLNode<E> y = z.getRightChild();
		
		AVLNode<E> t2 = y.getLeftChild();
		
		y.setLeftChild(z);
		z.setRightChild(t2);
		
		updateHeight(z);
		updateHeight(t2);
		return y;
	}
	
	private AVLNode<E> rotateRight(AVLNode<E> z){
		AVLNode<E> y = z.getLeftChild();
		
		AVLNode<E> t3 = y.getRightChild();
		
		y.setRightChild(z);
		z.setLeftChild(t3);
		updateHeight(z);
		updateHeight(t3);
		return y;
		
	}
	
	private int getBalanceFactor(AVLNode<E> node) {
		return node != null ? (height(node.getLeftChild()) - height(node.getRightChild())) : 0;
	}
	
	private int height(AVLNode<E> node) {
		return node != null ? node.getHeight() : 0;
	}
	
	/**
	 * get helper method
	 * Description: recursively traverses the tree until it finds the object it is looking for
	 * @param data
	 * @param node
	 * @return
	 */
	private AVLNode<E> get(E data, AVLNode<E> node){
		if(node.getData().compareTo(data) > 0) {
			get(data, node.getRightChild());
		}
		else if(node.getData().compareTo(data) < 0) {
			get(data, node.getRightChild());
		}
		return node != null ? node : null;
	}
	
	
	
	
}



