import java.util.Iterator;

public class IteratorVertice<T> implements Iterator<Integer> {
	private Iterator<Vertice<T>> iterator;
	public IteratorVertice(Iterator<Vertice<T>> iterator) {
		this.iterator = iterator;
	}

	public boolean hasNext() {						//O(1)
		return iterator.hasNext();
	}
	
	public Integer next() {								//O(1)
		return iterator.next().getVerticeId();
	}
}
