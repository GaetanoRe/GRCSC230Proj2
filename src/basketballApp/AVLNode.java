package basketballApp;

public class AVLNode<E extends Comparable<E>> {
			// This will contain the data
			private E data;
			private int height;
			// The two children of the Binary Tree Node
			private AVLNode<E> leftChild;
			private AVLNode<E> rightChild;
			
			/**
			 * <p>Node default constructor</p>
			 * <p>Description: the default constructor for the node class.</p>
			 * @param key
			 * @param data
			 */
			AVLNode(E data){
				this.data = data;
				this.height = 1;
			}
			
			public String toString() {
				return data.toString();
			}
			
			public E getData() {
				return data;
			}
			
			public AVLNode<E> getRightChild(){
				return rightChild;
			}
			
			public AVLNode<E> getLeftChild(){
				return leftChild;
			}
			
			public int getHeight() {
				return height;
			}
			
			public void setData(E data) {
				this.data = data;
			}
			
			public void setLeftChild(AVLNode<E> node) {
				this.leftChild = node;
			}
			
			public void setRightChild(AVLNode<E> node) {
				this.rightChild = node;
			}
			
			public void setHeight(int newHeight) {
				height = newHeight;
			}
			
			
			
			public boolean hasNoChildren() {
				return leftChild == null && rightChild == null;
			}
}
