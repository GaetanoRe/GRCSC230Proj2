package basketballApp;

public class Node<E extends Comparable<E>> {
			// This will contain the data
			private E data;
			private int height;
			// The two children of the Binary Tree Node
			private Node<E> leftChild;
			private Node<E> rightChild;
			
			/**
			 * <p>Node default constructor</p>
			 * <p>Description: the default constructor for the node class.</p>
			 * @param key
			 * @param data
			 */
			Node(E data){
				this.data = data;
				this.height = 1;
			}
			
			public String toString() {
				return data.toString();
			}
			
			public E getData() {
				return data;
			}
			
			public Node<E> getRightChild(){
				return rightChild;
			}
			
			public Node<E> getLeftChild(){
				return leftChild;
			}
			
			public int getHeight() {
				return height;
			}
			
			public void setData(E data) {
				this.data = data;
			}
			
			public void setLeftChild(Node<E> node) {
				this.leftChild = node;
			}
			
			public void setRightChild(Node<E> node) {
				this.rightChild = node;
			}
			
			
			
			public boolean hasNoChildren() {
				return leftChild == null && rightChild == null;
			}
}
