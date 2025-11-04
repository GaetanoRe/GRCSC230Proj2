package basketballApp;

/**
 * <p>Title: BinarySearchTree class</p>
 * <p>Description: This is a generic binary tree class that contains nodes that have an integer key and a generic piece of data</p>
 * @author Gaetano Re 
 * N#: N00918949
 */
public class AVLTree<E extends Comparable<E>> implements Tree<E>{

	private Node<E> root;

	@Override
	public Tree<E> insert(E data) {
		root = insert(data, root);
		return this;
	}

	@Override
	public void delete(E data) {
		root = delete(data, root);
	}

	@Override
	public void traverse() {
		traverseInOrder(root);

	}

	@Override
	public E getMax() {
		if(isEmpty()) {
			return null;
		}
		return getMax(root);
	}

	@Override
	public E getMin() {
		if(isEmpty()) {
			return null;
		}
		return getMin(root);
	}

	@Override
	public boolean isEmpty() {
		return this.root == null;
	}
	
	private void traverseInOrder(Node<E> node) {
		if(node != null) {
			traverseInOrder(node.getLeftChild());
			System.out.println(node);
			traverseInOrder(node.getRightChild());
		}
	}
	
	
	
	private void traversePreOrder(Node<E> node) {
		
	}
	
	private void traversePostOrder(Node<E> node) {
		
	}
	
	private E getMax(Node<E> node){
		if(node.getRightChild() != null) {
			return getMax(node.getRightChild());
		}
		else {
			return node.getData();
		}
	}
	
	private E getMin(Node<E> node) {
		if(node.getLeftChild() != null) {
			return getMin(node.getLeftChild());
		}
		else {
			return node.getData();
		}
	}
	
	private Node<E> insert(E data, Node<E> node) {
		if(node == null) {
			return new Node<E>(data);
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
	
	private Node<E> delete(E data, Node<E> node){
		if(node == null) {
			return null;
		}
		if(data.compareTo(node.getData()) < 0) {
			node.setLeftChild(delete(data, node.getLeftChild()));
		}
		else if(data.compareTo(node.getData()) > 0) {
			node.setRightChild(delete(data, node.getRightChild()));
		}else {
			if(node.getLeftChild() == null) {
				return node.getRightChild();
			}
			else if(node.getRightChild() == null) {
				return node.getLeftChild();
			}
			node.setData(getMax(node.getLeftChild()));
			node.setLeftChild(delete(node.getData(), node.getLeftChild()));
			
		}
		
		return node;
	}
	
	private void updateHeight(Node<E> node) {
		
	}
	
	private Node<E> applyRotation(Node<E> node){
		
	}
	
	private Node<E> rotateLeft(Node<E> node){
		
	}
	
	private Node<E> rotateRight(Node<E> node){
		Node<E> z = node;
		Node<E> y = node.getLeftChild();
		Node<E> x = y.getLeftChild();
	}
	
	
	
	
}



