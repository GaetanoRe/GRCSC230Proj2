package basketballApp;

/**
 * <p>Title: BinaryTree class</p>
 * <p>Description: This is a generic binary tree class that contains nodes that have an integer key and a generic piece of data</p>
 * @author Gaetano Re
 */
public class BinarySearchTree<E> {
	/**
	 * <p>Title: BinaryTree Node class</p>
	 * <p>Description: A private class that is utilized by the binary tree class above</p>
	 * @author Gaetano Re
	 */
	private class Node<E>{
		// This is the data that will dictate its place in a tree
		int key;
		
		// This is the actual data
		E data;
		
		// The two children of the Binary Tree Node
		Node<E> leftChild;
		Node<E> rightChild;
		
		
		Node(int key, E data){
			this.key = key;
			this.data = data;
		}
		
		
		public String toString() {
			return data.toString();
		}
	}
	
	// Root of the tree
	Node<E> root;
	
	/**
	 * <p> insert method</p>
	 * <p> Inserts a node into the tree dependent on the key</p>
	 * @param key value that dictates it's place in the tree
	 * @param data that the node contains
	 */
	public void insert(int key, E data) {
		
		// Create a new node to insert
		Node<E> newNode = new Node<E>(key, data);
		
		// If the root is empty, then insert the node into the root
		if(root == null) {
			root = newNode;
		}
		
		// else, we start the process
		else {
			
			// the focus node is the node we use to get the current node in the tree
			Node<E> focusNode = root;
			
			// this will contain the parent of the focus node
			Node<E> parent;
			
			
			// This is the loop that will loop through the tree.
			while(true) {
				// set the parent to the current focus
				parent = focusNode;
				
				// If the key is less than the focus, the focus will be on the left
				if(key < focusNode.key) {
					focusNode = focusNode.leftChild;
					 // Add to the load balance value of the left
					
					// If the focus is null, the parent's left child is the new node
					if(focusNode == null) {
						parent.leftChild = newNode;
						return;
					}
				}
				
				// If the key is more than the focus, the focus will be on the right
				else if(key > focusNode.key) {
					focusNode = focusNode.rightChild;
					 // Add to the load balance value of the right
					
					// If the focus on this time is null, the right child of the parent will be the new node.
					if(focusNode == null) {
						parent.rightChild = newNode;
						return;
					}
				}
				else if(key == focusNode.key) {
					if(focusNode != null) {
						return;
					}
				}
				
			}
		}
	}
	
	/**
	 * <p>inOrderTraverseTree method</p>
	 * <p> A recursive method that will traverse the tree in order. It typically starts at the leftmost node, then explores the parent, 
	 * then the right child</p>
	 * @param focusNode during the recursion
	 */
	public void inOrderTraverseTree(Node<E> focusNode) {
		if(focusNode != null) { // If the node is not null
			inOrderTraverseTree(focusNode.leftChild); // Go to the left child
			System.out.println(focusNode); // Display the contents
			
			inOrderTraverseTree(focusNode.rightChild); // Go to right child
		}
	}
	
	/**
	 * <p> preOrderTraverseTree method</p>
	 * <p> a recursive method that will traverse the tree in preorder. It typically displays the node it is currently focusing on, then visits the left child
	 * then the right</p>
	 * @param focusNode that the method is currently focusing on.
	 */
	public void preOrderTraverseTree(Node<E> focusNode) {
		if(focusNode != null) {
			System.out.println(focusNode); // Display
			
			preOrderTraverseTree(focusNode.leftChild); // Check Left Child
			preOrderTraverseTree(focusNode.rightChild); // Check Right Child
		}
	}
	
	/**
	 * <p> postOrderTraverseTree method</p>
	 * <p> a recursive method that will traverse the tree in post order. It typically traverses the left child, then the right child, then displays
	 * it.</p>
	 * @param focusNode
	 */
	public void postOrderTraverseTree(Node<E> focusNode) {
		if(focusNode != null) {
			preOrderTraverseTree(focusNode.leftChild); // Check Left Child
			preOrderTraverseTree(focusNode.rightChild);// Check Right Child
			
			System.out.println(focusNode); // Display
		}
	}
	
	/**
	 * <p> findNode method</p>
	 * <p> utilizes a given key to find the node associated with it.</p>
	 * @param key to find the node
	 * @return null if the node does not exist, or the node if it does.
	 */
	public Node<E> findNode(int key) {
		Node<E> focusNode = root; // The focus starts at the root
		
		while(focusNode.key != key) { // While the focus Node's key does not equal the given key
			if(key < focusNode.key) { // If the key is less than the focus'
				focusNode = focusNode.leftChild; // the focus is now in the left child
			}
			
			else if(key > focusNode.key) { // If the key is more than the focus'
				focusNode = focusNode.rightChild; // The focus is now in the right child
			}
			
			if(focusNode == null) { // If the focus is null, the search failed
				return null;
			}
		}
		
		return focusNode; // return the node that matches the key
	}
	
	/**
	 * <p> remove method</p>
	 * <p> removes a node from the tree</p>
	 * @param key of the node to remove
	 * @return true if the node was found and deleted, false if no node of that key was found.
	 */
	public boolean remove(int key) {
		Node<E> focusNode = root; // The node to focus on.
		Node<E> parent = root; // The parent of the focus Node
		
		boolean isItALeft = true; // This will determine different operations based on whether or not the focus node has a left child or not
		
		while(focusNode.key != key) { // While the given key is not equal to the focus Node's key
			parent = focusNode; // The parent node of the next focus is assigned.
			
			if(key < focusNode.key) { // If the key is greater than the current focus node's key
				
				isItALeft = true; // The focus Node has a left child
				focusNode = focusNode.leftChild; // The focus is now on the left child
			}
			else {
				isItALeft = false; // The focus Node does not have a left child
				focusNode = focusNode.rightChild; // The focus Node is on the right child
			}
			
			if(focusNode == null) { // If the focus Node is null, the node to be removed does not exist
				return false;
			}
		}
		
		if(focusNode.leftChild == null && focusNode.rightChild == null) { // If the focus does not have any children
			if(focusNode == root) { // If the focus is the node, that means the tree only has a root
				root = null; // The root is now removed
			}
			else if(isItALeft) { // If the focus is a left child
				parent.leftChild = null; // Remove the left child
				
			}
			else {
				parent.rightChild = null; // Remove the right child
				
			}
		}
		
		else if(focusNode.rightChild == null) { // If the focus has a left child, but no right child
			if(focusNode == root) { // if the focus is the root
				root = focusNode.leftChild; // The root becomes the left child
				
			}
			else if(isItALeft) { // If the focus is a left child
				parent.leftChild = focusNode.leftChild; // the focus' parent's left child is now the focus' left
				
				if(parent.key != root.key) {
					
				}
			}
			else {
				parent.rightChild = focusNode.leftChild; // the focus' parent's right child is now the focus' right
				
				if(parent.key != root.key) {
					
				}
			}
		}
		
		else if(focusNode.leftChild == null) { // If the focus has a right child but no left child
			if(focusNode == root) { // If the focus was the root
				root = focusNode.rightChild; // The root is now the right child
				if(parent.key != root.key) {
					
				}
			}
			else if(isItALeft) { // If the focus is the left child
				parent.leftChild = focusNode.rightChild; // Make the parent's left child the focus' right
				
				if(parent.key != root.key) {
					
				}
			}
			else {
				parent.rightChild = focusNode.rightChild; // Make the parent's right child the focus' right
				
				if(parent.key != root.key) {
					
				}
			}
		}
		else { // If both children exist
			Node<E> replacement = getReplacementNode(focusNode); // get the replacement node for the focus
			
			if(focusNode == root) { // If the focus is in the root
				root = replacement; // the replacement node is now the root
			}
			
			else if(isItALeft) { // If the focus is a left child
				parent.leftChild = replacement; // The parent's left child is the replacement
				
			}
			else { // If the focus is a right child
				parent.rightChild = replacement; //The parent's right child is now the replacement.
				
			}
			
			replacement.leftChild = focusNode.leftChild; // Make the focus' left child into the replacement's
			
		}
		
		return true; // Return true if the action was completed successfully
	}
	
	/**
	 * <p> getReplacementNode method</p>
	 * <p> This is a private recursive method that will return a replacement node upon node removal</p>
	 * @param replacementNode
	 * @return
	 */
	private Node<E> getReplacementNode(Node<E> replacementNode) {
		Node<E> replacementParent = replacementNode; // The given node is the parent at first
		Node<E> replacement = replacementNode; // The replacement is the the given node at first
		
		Node<E> focusNode = replacementNode.rightChild; // The focus node starts at the right child of the given node
		
		while(focusNode != null) { // While the focus is not null
			replacementParent = replacement; // The next node's parent is the current focus 
			replacement = focusNode; // The focus is the replacement
			
			focusNode = focusNode.leftChild; // The focus is now to it's left child
		}
		
		if(replacement != replacementNode.rightChild) { // If the replacement is not the right child of the given node
			replacementParent.leftChild = replacement.rightChild; // The parent's left child is the right child of the given node
			replacement.rightChild = replacementNode.rightChild; // the replacement's right child is now the given's right child
		}
		
		return replacement; // return the replacement node
	}
	
	
}



