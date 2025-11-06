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
	 * <p>insert method</p>
	 * <p>Description: inserts the node at the root and initiates the recursive insert method</p>
	 * @param the data to insert into the tree.
	 */
	@Override
	public void insert(E data) {
		root = insert(data, root); // Insert the data at the root 
	}
	
	/**
	 * <p>delete method</p>
	 * <p>Description: deletes a node with the given data using a recursive delete method</p>
	 * @param the data to delete from the tree.
	 */
	@Override
	public void delete(E data) {
		root = remove(data, root); // Start the removing process at the root
	}
	
	/**
	 * <p>traverse method<p>
	 * <p>Description: displays the the data in the tree using InOrder Traversal</p>
	 */
	@Override
	public void traverse() {
		traverseInOrder(root); // Traverse the tree in order
	}
	
	/**
	 * <p>getMax method</p>
	 * <p>Description: Starts at the root of the node and finds the maximum node of the tree</p>
	 * @return the maximum value of the tree
	 */
	@Override
	public E getMax() {
		if(isEmpty()) { // If the tree is empty
			return null; // return null
		}
		return getMax(root); // Else, utilize the getMax helper method starting at the root
	}

	/**
	 * <p>getMin method</p>
	 * <p>Description: Starts at the root of the node and finds the minimum node of the tree</p>
	 * @return the minimum node of the tree.
	 */
	@Override
	public E getMin() {
		if(isEmpty()) { // If the tree is empty
			return null; // Return null
		}
		return getMin(root); // Otherwise, utilize the getMin helper method starting at the root
	}
	
	/**
	 * <p>isEmpty method</p>
	 * <p>Description: returns a boolean dictating whether or not the tree is empty by checking if the root is null</p>
	 * @return true if the root is null, false if the root is not null
	 */
	@Override
	public boolean isEmpty() {
		return this.root == null; // If the root is null, then the tree is empty
	}
	
	/**
	 * <p>get method</p>
	 * <p>Description: searches for the data supplied as the parameter. It returns null if the data was not found</p>
	 * @param data
	 * @return the data if it exists in the tree
	 */
	public E get(E data) {
		AVLNode<E> accessor = get(data, root); // Create a node containing the data if the data was found using the helper method
		
		return accessor != null ? accessor.getData() : null; // If the accessor node is not null, then return the data, 
															 // else return null
	}
	
	/**
	 * <p>getRoot accessor method</p>
	 * <p>Description: returns the root of the tree</p>
	 * @return the root of the tree
	 */
	public AVLNode<E> getRoot() {
		return root;
	}
	
	/**
	 * <p>traverseInOrder method</p>
	 * <p>Description: traverses the tree and displays the data in order</p>
	 */
	private void traverseInOrder(AVLNode<E> node) {
		if(node != null) {
			traverseInOrder(node.getLeftChild());
			System.out.println(node);
			traverseInOrder(node.getRightChild());
		}
	}
	
	/**
	 * <p>getMax method</p>
	 * <p>Description: Returns the maximum value in the tree by accessing the right-most child of the current node</p>
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
	 * <p>getMin method</p>
	 * <p>Description: Returns the maximum value in the tree by accessing the left-most child of the current node</p>
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
	 * <p> remove helper method </p>
	 * <p> Description: the recursive method that removes a node from the tree if the data matches the current node</p>
	 * @param the data to be deleted
	 * @param the current node in the recursion
	 * @return the rotated tree after the removal
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
	
	/**
	 * <p> updateHeight method </p>
	 * <p> Description: the recursive method that updates the height of the nodes. This is usually used by the insertion
	 * 	   and deletion methods.</p>
	 * @param the current node in the recursion to have an updated height
	 * @return the rotated tree after the removal
	 */
	private void updateHeight(AVLNode<E> node) {
		int maxHeight = Math.max(height(node.getLeftChild()), height(node.getRightChild())); // Retrieve the maximum path of the nodes
		node.setHeight(maxHeight + 1); // Update the heights accordingly
	}
	
	/**
	 * <p> applyRotation method </p>
	 * <p> Description: applies a rotation based on the balance factors of the nodes</p>
	 * @param the node to apply the rotation to
	 * @return the properly rotated node
	 */
	private AVLNode<E> applyRotation(AVLNode<E> node){
		int balanceFactor = getBalanceFactor(node); // Get the balance factor of the current node
		if(balanceFactor > 1) { // If the balance factor is more than 1
			if(getBalanceFactor(node.getLeftChild()) < 0) { // If the balance factor of the left child is less than 0
				node.setLeftChild(rotateLeft(node.getLeftChild())); // Rotate that child to the left first
			}
			return rotateRight(node); // Rotate the node to the right
		}
		else if(balanceFactor < -1) { // If the balance factor is less than -1
			if(getBalanceFactor(node.getRightChild()) > 0) { // If the balance factor of the right child is more than 0
				node.setRightChild(rotateRight(node.getRightChild())); // rotate the right child to the right
			}
			return rotateLeft(node); // Rotate the node to the left
		}
		
		return node; // Return the updated node
	}
	
	/**
	 * <p> applyRotation method </p>
	 * <p> Description: applies a rotation based on the balance factors of the nodes</p>
	 * @param the node to apply the rotation to
	 * @return the properly rotated node
	 */
	private AVLNode<E> rotateLeft(AVLNode<E> z){
	    AVLNode<E> y  = z.getRightChild();
	    AVLNode<E> t2 = y.getLeftChild();

	    y.setLeftChild(z);
	    z.setRightChild(t2);

	    updateHeight(z);
	    updateHeight(y);
	    return y;
	}

	private AVLNode<E> rotateRight(AVLNode<E> z){
	    AVLNode<E> y  = z.getLeftChild();
	    AVLNode<E> t3 = y.getRightChild();

	    y.setRightChild(z);
	    z.setLeftChild(t3);

	    updateHeight(z);
	    updateHeight(y);
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
		if(node == null) {
			return null;
		}
		if(node.getData().compareTo(data) > 0) {
			get(data, node.getRightChild());
		}
		else if(node.getData().compareTo(data) < 0) {
			get(data, node.getRightChild());
		}
		return node;
	}
	
	
	
	
}



