package basketballApp;

public interface Tree<E extends Comparable<E>> {
	public void insert(E data);
	
	public void delete(E data);
	
	public void traverse();
	
	public E getMax();
	
	public E getMin();
	
	public boolean isEmpty();

}
