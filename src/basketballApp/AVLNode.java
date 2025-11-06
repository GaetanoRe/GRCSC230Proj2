package basketballApp;

public class AVLNode<E extends Comparable<E>> {
			// This will contain the data
			private E data;
			private int height;
			// The two children of the Binary Tree Node
			private AVLNode<E> leftChild;
			private AVLNode<E> rightChild;
			
			/**
			 * <p>AVLNode default constructor</p>
			 * <p>Description: the default constructor for the node class.</p>
			 * @param key
			 * @param data
			 */
			AVLNode(E data){
				this.data = data;
				this.height = 0;
			}
			
			/**
			 * <p>toString method</p>
			 * <p>Description: Returns the string data within the node</p>
			 * @return The data within the node as a string
			 */
			public String toString() {
				return data.toString();
			}
			
			/**
			 * <p>getData accessor method</p>
			 * <p>Description: Returns the data contained with in the node</p>
			 * @return the data contained within the node
			 */
			public E getData() {
				return data;
			}
			
			/**
			 * <p>getRightChild accessor method</p>
			 * <p>Description: returns the node that is the right child of this node</p>
			 * @return the right child of the node
			 */
			public AVLNode<E> getRightChild(){
				return rightChild;
			}
			
			/**
			 * <p>getLeftChild accessor method</p>
			 * <p>Description: returns the left child of the node</p>
			 * @return the left child of the node
			 */
			public AVLNode<E> getLeftChild(){
				return leftChild;
			}
			
			/**
			 * <p>getHeight accessor method</p>
			 * <p>Description: returns the height of the node</p>
			 * @return the height of the node
			 */
			public int getHeight() {
				return height;
			}
			
			/**
			 * <p>setData mutator method</p>
			 * <p>Description: changes the data of the node</p>
			 * @param the new data to replace the data contained within the node
			 */
			public void setData(E data) {
				this.data = data;
			}
			
			/**
			 * <p>setLeftChild mutator method</p>
			 * <p>Description: changes the left child of the node</p>
			 * @param the new node to replace the left child
			 */
			public void setLeftChild(AVLNode<E> node) {
				this.leftChild = node;
			}
			
			/**
			 * <p>setRightChild mutator method</p>
			 * <p>Description: changes the right child of the node</p>
			 * @param the new node to replace the right child
			 */
			public void setRightChild(AVLNode<E> node) {
				this.rightChild = node;
			}
			
			/**
			 * <p>setHeight mutator method</p>
			 * <p>Description: changes the height of the node</p>
			 * @param the new data to replace the height of the node
			 */
			public void setHeight(int newHeight) {
				height = newHeight;
			}
			
			
			/**
			 * <p>hasNoChildren Method</p>
			 * <p>Description: returns whether or not the node has no children</p>
			 * @return true if the node has no children, false if the node has at least one child
			 */
			public boolean hasNoChildren() {
				return leftChild == null && rightChild == null;
			}
}
