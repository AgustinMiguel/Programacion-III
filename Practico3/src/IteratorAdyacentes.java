import java.util.Iterator;

public class IteratorAdyacentes<T> implements Iterator<Integer> {
	private Iterator<Arco<T>> iterator;
	
	public IteratorAdyacentes (Iterator<Arco<T>> iterator) {
		this.iterator = iterator;
	}
	public boolean hasNext() {									//O(1)
		return iterator.hasNext();
	}

	public Integer next() {											//O(1)
		return iterator.next().getVerticeDestino();
	}

}
